package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class QuerySmResp extends PDU(CommandId.query_sm_resp) {

  var messageId: String = null
  var finalDate: String = null
  var messageState: Int = null
  var errorCode: Int = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    messageId = buffer.readString()
    finalDate = buffer.readString()
    messageState = buffer.readByte()
    errorCode = buffer.readByte()
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(messageId)
    buffer.appendString(finalDate)
    buffer.appendByte(messageState)
    buffer.appendByte(errorCode)
    buffer
  }

}
