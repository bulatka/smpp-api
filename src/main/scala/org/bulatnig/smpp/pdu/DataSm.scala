package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class DataSm extends PDU(CommandId.data_sm) {

  var serviceType: String = null
  var sourceAddrTon: Int = null
  var sourceAddrNpi: Int = null
  var sourceAddr: String = null
  var destAddrTon: Int = null
  var destAddrNpi: Int = null
  var destinationAddr: String = null
  var esmClass: Int = null
  var registeredDelivery: Int = null
  var dataCoding: Int = null

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
