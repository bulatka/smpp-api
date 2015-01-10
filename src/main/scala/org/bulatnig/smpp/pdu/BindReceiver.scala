package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class BindReceiver extends PDU(CommandId.bind_receiver) {

  var systemId: String = null
  var password: String = null
  var systemType: String = null
  var interfaceVersion = 0
  var addrTon = 0
  var addrNpi = 0
  var addressRange: String = null

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendCString(systemId)
    buffer.appendCString(password)
    buffer.appendCString(systemType)
    buffer.appendByte(interfaceVersion)
    buffer.appendByte(addrTon)
    buffer.appendByte(addrNpi)
    buffer.appendCString(addressRange)
    buffer
  }
}
