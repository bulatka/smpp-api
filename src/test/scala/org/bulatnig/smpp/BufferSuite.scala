package org.bulatnig.smpp

import java.nio.charset.StandardCharsets

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class BufferSuite extends FunSuite {

  test("No argument constructor should create empty Buffer") {
    assert(new Buffer().toArray === new Array[Byte](0))
  }

  test("No argument constructor should create zero length Buffer") {
    assert(new Buffer().length == 0)
  }

  test("Empty Buffer should generate empty HEX") {
    assert(new Buffer().toHexString == "")
  }

  test("Array[Byte] argument constructor should fill Buffer") {
    val a = Array[Byte](1, 2, 3, 4)
    val buffer = new Buffer(a)
    assert(buffer.length == a.length)
    assert(buffer.toArray === a)
    assert(buffer.toHexString == "01020304")
  }

  test("ArrayBuffer[Byte] argument constructor should fill Buffer") {
    val a = ArrayBuffer[Byte](1, 2, 3, 4)
    val buffer = new Buffer(a)
    assert(buffer.length == a.length)
    assert(buffer.toArray === a)
    assert(buffer.toHexString == "01020304")
  }

  test("Buffer create from buffer and array should work") {
    val b1 = new Buffer(Array[Byte](1, 2, 3, 4))
    val a = Array[Byte](5, 6, 7, 8)
    val b2 = b1 ++ a
    assert(b2.toArray === Array[Byte](1, 2, 3, 4, 5, 6, 7, 8))
  }

  test("Buffer create from buffer and array should not change sources") {
    val b1 = new Buffer(Array[Byte](1, 2, 3, 4))
    val a = Array[Byte](5, 6, 7, 8)
    val b2 = b1 ++ a
    assert(b1.toArray === Array[Byte](1, 2, 3, 4))
    assert(a === Array[Byte](5, 6, 7, 8))
  }

  test("Buffer create from buffer and buffer should work") {
    val b1 = new Buffer(Array[Byte](1, 2, 3, 4))
    val b2 = new Buffer(Array[Byte](5, 6, 7, 8))
    val b3 = b1 ++ b2
    assert(b3.toArray === Array[Byte](1, 2, 3, 4, 5, 6, 7, 8))
  }

  test("Buffer create from buffer and buffer should not change sources") {
    val b1 = new Buffer(Array[Byte](1, 2, 3, 4))
    val b2 = new Buffer(Array[Byte](5, 6, 7, 8))
    val b3 = b1 ++ b2
    assert(b1.toArray === Array[Byte](1, 2, 3, 4))
    assert(b2.toArray === Array[Byte](5, 6, 7, 8))
  }

  test("Byte array append should work") {
    val buffer = new Buffer(Array[Byte](1, 2, 3, 4))
    val a = Array[Byte](5, 6, 7, 8)
    buffer ++= a
    assert(buffer.toArray === Array[Byte](1, 2, 3, 4, 5, 6, 7, 8))
  }

  test("Buffer append should work") {
    val b1 = new Buffer(Array[Byte](1, 2, 3, 4))
    val b2 = new Buffer(Array[Byte](5, 6, 7, 8))
    b1 ++= b2
    assert(b1.toArray === Array[Byte](1, 2, 3, 4, 5, 6, 7, 8))
  }

  test("Byte append should work") {
    val buffer = new Buffer()
    buffer.appendByte(128)
    assert(buffer.toHexString == "80")
  }

  test("Byte read should work") {
    val buffer = new Buffer()
    buffer.appendByte(128)
    assert(buffer.readByte() == 128)
  }

  test("Byte read out of bound should produce IndexOutOfBoundsException") {
    val buffer = new Buffer()
    buffer.appendByte(128)
    buffer.readByte()
    intercept[IndexOutOfBoundsException] {
      new Buffer().readByte()
    }
  }

  test("Unsigned byte max value should work") {
    val buffer = new Buffer()
    buffer.appendByte(255)
    assert(buffer.toHexString == "FF")
    assert(buffer.readByte() == 255)
  }

  test("Short append should work") {
    val buffer = new Buffer()
    buffer.appendShort(32768)
    assert(buffer.toHexString == "8000")
  }

  test("Short read should work") {
    val buffer = new Buffer()
    buffer.appendShort(32768)
    assert(buffer.readShort() == 32768)
  }

  test("Short read out of bound should produce IndexOutOfBoundsException") {
    val buffer = new Buffer()
    buffer.appendShort(50000)
    buffer.readShort()
    intercept[IndexOutOfBoundsException] {
      new Buffer().readShort()
    }
  }

  test("Unsigned short max value should work") {
    val buffer = new Buffer()
    buffer.appendShort(65535)
    assert(buffer.toHexString == "FFFF")
    assert(buffer.readShort() == 65535)
  }

  test("Int append should work") {
    val buffer = new Buffer()
    buffer.appendInt(-2147483648)
    assert(buffer.toHexString == "80000000")
  }

  test("Int read should work") {
    val buffer = new Buffer()
    buffer.appendInt(-2147483648)
    assert(buffer.readInt() == -2147483648)
  }

  test("Int read out of bound should produce IndexOutOfBoundsException") {
    val buffer = new Buffer()
    buffer.appendInt(12345)
    buffer.readInt()
    intercept[IndexOutOfBoundsException] {
      new Buffer().readInt()
    }
  }

  test("C-Octet String append should work") {
    val buffer = new Buffer()
    buffer.appendCString("smpp")
    assert(buffer.toArray === Array[Byte](115, 109, 112, 112, 0))
    assert(buffer.toHexString == "736D707000")
  }

  test("C-Octet String append null should work") {
    val buffer = new Buffer()
    buffer.appendCString(null)
    assert(buffer.toArray === Array[Byte](0))
    assert(buffer.toHexString == "00")
  }

  test("C-Octet String read should work") {
    val s = "hello world"
    val buffer = new Buffer()
    buffer.appendCString(s)
    assert(buffer.readCString() == s)
  }

  test("C-Octet String read null should work") {
    val buffer = new Buffer()
    buffer.appendCString(null)
    assert(buffer.readCString() == null)
  }

  test("C-Octet String  read out of bound should produce IndexOutOfBoundsException") {
    val buffer = new Buffer()
    buffer.appendCString(null)
    buffer.readCString()
    intercept[IndexOutOfBoundsException] {
      new Buffer().readCString()
    }
  }

  test("Octet String append should work") {
    val buffer = new Buffer()
    buffer.appendString("Привет", StandardCharsets.UTF_16BE)
    assert(buffer.toHexString == "041F04400438043204350442")
  }

  test("Octet String append null should work") {
    val buffer = new Buffer()
    buffer.appendString(null, StandardCharsets.UTF_16BE)
    assert(buffer.toHexString == "")
  }

  test("Octet String read should work") {
    val s = "Привет"
    val buffer = new Buffer()
    buffer.appendString(s, StandardCharsets.UTF_16BE)
    assert(buffer.readString(12, StandardCharsets.UTF_16BE) == s)
  }

  test("Octet String read null should work") {
    val buffer = new Buffer()
    buffer.appendString(null, StandardCharsets.UTF_16BE)
    assert(buffer.readString(0, StandardCharsets.UTF_16BE) == "")
  }

  test("Octet String  read out of bound should produce IndexOutOfBoundsException") {
    val buffer = new Buffer()
    buffer.appendString("smpp", StandardCharsets.US_ASCII)
    buffer.readString(4, StandardCharsets.US_ASCII)
    intercept[IndexOutOfBoundsException] {
      new Buffer().readString(1, StandardCharsets.US_ASCII)
    }
  }

  test("Append chaining should work") {
    val buffer = new Buffer()
    buffer.appendByte(1).appendShort(2).appendInt(3).appendCString("hello").appendString("Привет", StandardCharsets.UTF_16BE)
    assert(buffer.length == 25)
    assert(buffer.readByte() == 1)
    assert(buffer.readShort() == 2)
    assert(buffer.readInt() == 3)
    assert(buffer.readCString() == "hello")
    assert(buffer.readString(12, StandardCharsets.UTF_16BE) == "Привет")
  }

  test("rewind should let read buffer from the start") {
    val buffer = new Buffer()
    buffer.appendByte(128)
    buffer.readByte()
    buffer.rewind()
    assert(buffer.readByte() == 128)
  }

}
