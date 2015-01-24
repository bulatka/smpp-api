package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class CancelSm extends PDU(CommandId.cancel_sm) {

  var serviceType: String = null
  var messageId: String = null
  var sourceAddrTon: Int = null
  var sourceAddrNpi: Int = null
  var sourceAddr: String = null
  var destAddrTon: Int = null
  var destAddrNpi: Int = null
  var destinationAddr: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    serviceType = buffer.readString()
    messageId = buffer.readString()
    sourceAddrTon = buffer.readByte()
    sourceAddrNpi = buffer.readByte()
    sourceAddr = buffer.readString()
    destAddrTon= buffer.readByte()
    destAddrNpi = buffer.readByte()
    destinationAddr = buffer.readString()
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(serviceType)
    buffer.appendString(messageId)
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer.appendByte(destAddrTon)
    buffer.appendByte(destAddrNpi)
    buffer.appendString(destinationAddr)
    buffer
  }

}
