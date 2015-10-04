package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

case class TLV(tag: Int, value: Array[Byte]) {

  /**
   * Construct TLV of Integer type value of length 1, 2 or 4
   */
  def this(tag: Int, length: Int, i: Int) {
    this(tag, TLV.intToBytes(length, i))
  }

  def this(tag: Int, cOctetString: String) {
    this(tag, new Buffer().appendString(cOctetString).toArray)
  }

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

  def toBuffer = {
    val buffer = new Buffer()
    buffer.appendShort(tag)
    buffer.appendShort(length)
    buffer ++= value
    buffer
  }

}

private object TLV {

  def intToBytes(length: Int, i: Int) = {
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
}
