package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class DataSm extends PDU(CommandId.data_sm) {

  var serviceType: String = null
  var sourceAddrTon = TON.Unknown
  var sourceAddrNpi = NPI.Unknown
  var sourceAddr: String = null
  var destAddrTon = TON.Unknown
  var destAddrNpi = NPI.Unknown
  var destinationAddr: String = null
  var esmClass = 0
  var registeredDelivery = 0
  var dataCoding = 0

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
    registeredDelivery = buffer.readByte()
    dataCoding = buffer.readByte()
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
    buffer.appendByte(registeredDelivery)
    buffer.appendByte(dataCoding)
    buffer
  }

}
