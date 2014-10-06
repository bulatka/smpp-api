package org.bulatnig.smpp

import org.scalatest.FunSuite

class BufferSuite extends FunSuite {

  test("No argument constructor should create empty Buffer") {
    val buffer = new Buffer()
    assert(buffer.length == 0)
  }

//  test("Byte append should work") {
//    val buffer = new Buffer()
//    val b = 255.toByte
//    buffer += b
//    println(b)
//    println(buffer.toHexString)
//  }

  test("Long append should work") {
    val buffer = new Buffer()
    buffer += 4294967295L
    assert(buffer.toHexString == "FFFFFFFF")
  }

  test("Long append should work 2") {
    val buffer = new Buffer()
    buffer += -1L
    assert(buffer.toHexString == "FFFFFFFF")
  }

  test("read 1") {
    val buffer = new Buffer()
    buffer += 255.toByte
    assert(buffer.toHexString == "FF")
    val result = buffer.readByte()
    println(result)
  }

}
