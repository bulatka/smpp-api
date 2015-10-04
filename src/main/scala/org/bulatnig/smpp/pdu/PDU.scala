package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

import scala.collection.mutable

/**
 * Mutable. Not thread-safe.
 */
abstract class PDU(val commandId: Int) {

  var commandStatus = CommandStatus.ESME_ROK
  var sequenceNumber = 0
  val tlvs = new mutable.HashMap[Int, TLV]()

  def toBuffer = {
    if (CommandStatus.ESME_ROK == commandStatus) {
      val body = getBodyBytes
      val buffer = new Buffer()
      buffer.appendInt(PDU.HeaderLength + body.length)
      buffer.appendInt(commandId)
      buffer.appendInt(commandStatus)
      buffer.appendInt(sequenceNumber)
      buffer ++= body
      buffer
    } else {
      val buffer = new Buffer()
      buffer.appendInt(PDU.HeaderLength)
      buffer.appendInt(commandId)
      buffer.appendInt(commandStatus)
      buffer.appendInt(sequenceNumber)
      buffer
    }
  }

  protected final def getBodyBytes = getStdParamBytes ++ getTlvBytes

  protected def getStdParamBytes = new Buffer()

  protected def getTlvBytes = tlvs.values.foldLeft(new Buffer()) { (buffer, tlv) => buffer ++= tlv.toBuffer}

  def commandLength = toBuffer.length

  protected final def parse(buffer: Buffer) {
    parseHeader(buffer)
    parseStdParams(buffer)
    parseTlvs(buffer)
  }

  protected def parseHeader(buffer: Buffer) {
    buffer.readInt() // length already checked
    buffer.readInt() // command id already known
    commandStatus = buffer.readInt()
    sequenceNumber = buffer.readInt()
  }

  protected def parseStdParams(buffer: Buffer) {}

  protected def parseTlvs(buffer: Buffer) {
    while (buffer.hasRemaining) {
      val tag = buffer.readShort()
      val length = buffer.readShort()
      tlvs(tag) = new TLV(tag, buffer.read(length))
    }
  }

}

object PDU {
  val HeaderLength = 16
}
