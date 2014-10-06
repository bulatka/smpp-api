package org.bulatnig.smpp

import java.nio.charset.StandardCharsets

import scala.collection.mutable.ArrayBuffer

class Buffer(val buffer: ArrayBuffer[Byte]) {

  var cursor = 0

  def this() {
    this(new ArrayBuffer[Byte]())
  }

  def this(array: Array[Byte]) {
    this(array.to[ArrayBuffer])
  }

  def toArray = buffer.toArray

  def length = buffer.length

  def ++=(xs: Array[Byte]) {
    buffer ++= xs
    this
  }

  def +=(elem: Byte) {
    buffer += elem
    this
  }

  def +=(elem: Short) {
    buffer += (elem >>> 8).toByte
    buffer += elem.toByte
    this
  }

  def +=(elem: Int) {
    buffer += (elem >>> 24).toByte
    buffer += (elem >>> 16).toByte
    buffer += (elem >>> 8).toByte
    buffer += elem.toByte
    this
  }

  def +=(elem: Long) {
    this += elem.toInt
    this
  }

  def addCString(elem: String) {
    if (elem != null) {
      this ++= elem.getBytes(StandardCharsets.US_ASCII)
    }
    this += Buffer.ZERO
    this
  }

  def addString(elem: String) {
    addString(elem, StandardCharsets.US_ASCII.name())
    this
  }

  def addString(elem: String, charsetName: String) {
    if (elem != null) {
      this ++= elem.getBytes(charsetName)
    }
    this
  }

  def readByte(): Byte = {
    val result = buffer(cursor)
    cursor += 1
    result
  }

  def readShort() {
    var result: Short = buffer(cursor)
    cursor += 1
    result <<= 8
    result |= buffer(cursor)
    cursor += 1
    result
  }

  def toHexString = buffer.map("%02X" format _).mkString

}

object Buffer {
  private val ZERO: Byte = 0
}