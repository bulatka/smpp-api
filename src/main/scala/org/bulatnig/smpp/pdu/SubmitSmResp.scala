package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class SubmitSmResp(override val commandStatus: Int = CommandStatus.ESME_ROK,
                   override val sequenceNumber: Int = 0,
                   messageId: String = null,
                   override val tlvs: List[TLV] = List())
  extends PDU(CommandId.submit_sm_resp, commandStatus, sequenceNumber, tlvs) {

  override protected def getStdParamBytes = {
    val buffer = new Buffer()
    buffer.appendString(messageId)
    buffer
  }

}

object SubmitSmResp {

  def apply(buffer: Buffer) = {
    val header = PDU.parseHeader(buffer)
    new SubmitSmResp(header.commandStatus, header.sequenceNumber, {
      if (buffer.isAvailable) buffer.readString()
      else null
    }, PDU.parseTlvs(buffer))
  }

}
