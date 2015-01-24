package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class DeliverSmResp extends PDU(CommandId.deliver_sm_resp) {

  var messageId: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    messageId = buffer.readString()
  }

  override protected def getStdParamBytes() = {
    val buffer = new Buffer()
    buffer.appendString(messageId)
    buffer
  }

}
