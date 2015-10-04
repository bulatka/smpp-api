package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class SubmitSm extends PDU(CommandId.submit_sm) {

  var serviceType: String = null
  var sourceAddrTon = TON.Unknown
  var sourceAddrNpi = NPI.Unknown
  var sourceAddr: String = null
  var destAddrTon = TON.Unknown
  var destAddrNpi = NPI.Unknown
  var destinationAddr: String = null
  var esmClass = 0
  var protocolId = 0
  var priorityFlag = 0
  var scheduleDeliveryTime: String = null
  var validityPeriod: String = null
  var registeredDelivery = 0
  var replaceIfPresentFlag = 0
  var dataCoding = 0
  var smDefaultMsgId = 0
  var shortMessage: Array[Byte] = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    serviceType = buffer.readString()
    sourceAddrTon = buffer.readByte()
    sourceAddrNpi = buffer.readByte()
    sourceAddr = buffer.readString()
    destAddrTon= buffer.readByte()
    destAddrNpi = buffer.readByte()
    destinationAddr = buffer.readString()
    esmClass = buffer.readByte()
    protocolId = buffer.readByte()
    priorityFlag = buffer.readByte()
    scheduleDeliveryTime = buffer.readString()
    validityPeriod = buffer.readString()
    registeredDelivery = buffer.readByte()
    replaceIfPresentFlag = buffer.readByte()
    dataCoding = buffer.readByte()
    smDefaultMsgId = buffer.readByte()
    val smLength = buffer.readByte()
    if (smLength > 0) {
      shortMessage = buffer.read(smLength)
    }
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(serviceType)
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer.appendByte(destAddrTon)
    buffer.appendByte(destAddrNpi)
    buffer.appendString(destinationAddr)
    buffer.appendByte(esmClass)
    buffer.appendByte(protocolId)
    buffer.appendByte(priorityFlag)
    buffer.appendString(scheduleDeliveryTime)
    buffer.appendString(validityPeriod)
    buffer.appendByte(registeredDelivery)
    buffer.appendByte(replaceIfPresentFlag)
    buffer.appendByte(dataCoding)
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
