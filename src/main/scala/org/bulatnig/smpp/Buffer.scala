package org.bulatnig.smpp

import java.nio.charset.{Charset, StandardCharsets}

import scala.collection.mutable.ArrayBuffer

class Buffer {

  val buffer = new ArrayBuffer[Byte]()

  private var position = 0

  def this(array: TraversableOnce[Byte]) {
    this()
    buffer ++= array
  }

  def toArray = buffer.toArray

  def length = buffer.length

  def ++(xs: TraversableOnce[Byte]) = {
    val newBuffer = new Buffer(buffer)
    newBuffer ++= xs
    newBuffer
  }

  def ++(xs: Buffer) = {
    val newBuffer = new Buffer(buffer)
    newBuffer ++= xs
    newBuffer
  }

  def ++=(xs: TraversableOnce[Byte]) = {
    buffer ++= xs
    this
  }

  def ++=(xs: Buffer) = {
    buffer ++= xs.toArray
    this
  }

  def appendByte(elem: Int) = {
    buffer += elem.toByte
    this
  }

  def appendShort(elem: Int) = {
    buffer += (elem >> 8).toByte
    buffer += elem.toByte
    this
  }

  def appendInt(elem: Int) = {
    buffer += (elem >> 24).toByte
    buffer += (elem >> 16).toByte
    buffer += (elem >> 8).toByte
    buffer += elem.toByte
    this
  }

  def appendCString(elem: String) = {
    if (elem != null) {
      this ++= elem.getBytes(StandardCharsets.US_ASCII)
    }
    buffer += Buffer.NULL
    this
  }

  def appendString(elem: String, charset: Charset) = {
    if (elem != null) {
      this ++= elem.getBytes(charset)
    }
    this
  }

  def readByte(): Int = {
    val result = buffer(position) & 0xFF
    position += 1
    result
  }

  def readShort(): Int = {
    val result = ((buffer(position) & 0xFF) << 8) | (buffer(position + 1) & 0xFF)
    position += 2
    result
  }

  def readInt(): Int = {
    val result = (buffer(position) << 24) |
      ((buffer(position + 1) & 0xFF) << 16) |
      ((buffer(position + 2) & 0xFF) << 8) |
      (buffer(position + 3) & 0xFF)
    position += 4
    result
  }

  def readCString(): String = {
    val idx = buffer.indexOf(Buffer.NULL, position)
    if (idx == -1) throw new IndexOutOfBoundsException("C-Octet String termination not found")
    var result: String = null
    if (idx > position) {
      val bytes = new Array[Byte](idx - position)
      Array.copy(buffer.toArray, position, bytes, 0, bytes.length)
      result = new String(bytes, StandardCharsets.US_ASCII)
    }
    position = idx + 1
    result
  }

  def readString(length: Int, charset: Charset): String = {
    val bytes = new Array[Byte](length)
    Array.copy(buffer.toArray, position, bytes, 0, bytes.length)
    val result = new String(bytes, charset)
    position += length
    result
  }

  def rewind() = {
    position = 0
    this
  }

  def toHexString = buffer.map("%02X" format _).mkString

}

object Buffer {
  private val NULL: Byte = 0
}
