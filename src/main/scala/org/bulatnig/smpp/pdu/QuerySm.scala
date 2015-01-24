package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class QuerySm extends PDU(CommandId.query_sm) {

  var messageId: String = null
  var sourceAddrTon: Int = null
  var sourceAddrNpi: Int = null
  var sourceAddr: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    messageId = buffer.readString()
    sourceAddrTon = buffer.readByte()
    sourceAddrNpi = buffer.readByte()
    sourceAddr = buffer.readString()
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(messageId)
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer
  }

}
