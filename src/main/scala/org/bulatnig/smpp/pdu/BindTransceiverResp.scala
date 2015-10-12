package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

case class BindTransceiverResp(override val commandStatus: Int = CommandStatus.ESME_ROK,
                               override val sequenceNumber: Int = 0,
                               systemId: String = null,
                               override val tlvs: List[TLV] = List())
  extends PDU(CommandId.bind_transceiver_resp, commandStatus, sequenceNumber, tlvs) with BindResponse {

  override protected def getStdParamBytes = new Buffer().appendString(systemId)

}

object BindTransceiverResp {

  def apply(buffer: Buffer) = {
    val header = PDU.parseHeader(buffer)
    new BindTransceiverResp(header.commandStatus, header.sequenceNumber, buffer.readString(), PDU.parseTlvs(buffer))
  }

}
