package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class BindTransceiverResp extends PDU(CommandId.bind_transceiver_resp) {

  var systemId: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    systemId = buffer.readString()
  }

  override protected def getStdParamBytes() = new Buffer().appendString(systemId)

}
