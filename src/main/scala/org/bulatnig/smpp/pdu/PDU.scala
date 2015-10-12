package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

abstract class PDU(
                    val commandId: Int,
                    val commandStatus: Int = CommandStatus.ESME_ROK,
                    val sequenceNumber: Int = 0,
                    val tlvs: List[TLV] = List()
                  ) {

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

  protected def getTlvBytes = tlvs.foldLeft(new Buffer()) { (buffer, tlv) => buffer ++= tlv.toBuffer }

  def commandLength = toBuffer.length

}

object PDU {
  val HeaderLength = 16

  def parseHeader(buffer: Buffer) = {
    buffer.readInt() // length already checked
    PDUHeader(buffer.readInt(), buffer.readInt(), buffer.readInt())
  }

  def parseTlvs(buffer: Buffer): List[TLV] = {
    if (buffer.isAvailable) parseTlv(buffer) :: parseTlvs(buffer)
    else Nil
  }

  private def parseTlv(buffer: Buffer) = {
    val tag = buffer.readShort()
    val length = buffer.readShort()
    new TLV(tag, buffer.read(length))
  }

}

case class PDUHeader(commandId: Int, commandStatus: Int, sequenceNumber: Int)
