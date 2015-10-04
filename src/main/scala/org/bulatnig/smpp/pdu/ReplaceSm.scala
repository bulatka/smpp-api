package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class ReplaceSm extends PDU(CommandId.replace_sm) {

  var messageId: String = null
  var sourceAddrTon = TON.Unknown
  var sourceAddrNpi = NPI.Unknown
  var sourceAddr: String = null
  var scheduleDeliveryTime: String = null
  var validityPeriod: String = null
  var registeredDelivery = 0
  var smDefaultMsgId = 0
  var shortMessage: Array[Byte] = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    messageId = buffer.readString()
    sourceAddrTon = buffer.readByte()
    sourceAddrNpi = buffer.readByte()
    sourceAddr = buffer.readString()
    scheduleDeliveryTime = buffer.readString()
    validityPeriod = buffer.readString()
    registeredDelivery = buffer.readByte()
    smDefaultMsgId = buffer.readByte()
    val smLength = buffer.readByte()
    if (smLength > 0) {
      shortMessage = buffer.read(smLength)
    }
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(messageId)
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer.appendString(scheduleDeliveryTime)
    buffer.appendString(validityPeriod)
    buffer.appendByte(registeredDelivery)
    buffer.appendByte(smDefaultMsgId)
    if (shortMessage == null) {
      buffer.appendByte(0)
    } else {
      buffer.appendByte(shortMessage.length)
      buffer ++= shortMessage
    }
    buffer
  }

}
