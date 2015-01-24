package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class Outbind extends PDU(CommandId.outbind) {

  var systemId: String = null
  var password: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    systemId = buffer.readString()
    password = buffer.readString()
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(systemId)
    buffer.appendString(password)
    buffer
  }

}
