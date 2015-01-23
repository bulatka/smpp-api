package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class TLV (val tag: Int) {

  private var _value: Array[Byte] = new Array[Byte](0)

  def this(tag: Int, bytes: Array[Byte]) {
    this(tag)
    _value = bytes
  }

  def this(tag: Int, length: Int, i: Int) {
    this(tag)
    _value = intToBytes(length, i)
  }

  private def intToBytes(length: Int, i: Int) = {
    val buffer = new Buffer()
    if (length == 1) {
      buffer.appendByte(i)
    } else if (length == 2) {
      buffer.appendShort(i)
    } else {
      buffer.appendInt(i)
    }
    buffer.toArray
  }

  def this(tag: Int, cOctetString: String) {
    this(tag)
    _value = new Buffer().appendString(cOctetString).toArray
  }

  def value = _value
  def length = value.length

  def readInt() = {
    val buffer = new Buffer(value)
    if (length == 1) {
      buffer.readByte()
    } else if (length == 2) {
      buffer.readShort()
    } else {
      buffer.readInt()
    }
  }

  def readString() = {
    new Buffer(value).readString()
  }

  def toBuffer() = {
    val buffer = new Buffer()
    buffer.appendShort(tag)
    buffer.appendShort(length)
    buffer ++= value
    buffer
  }

}
