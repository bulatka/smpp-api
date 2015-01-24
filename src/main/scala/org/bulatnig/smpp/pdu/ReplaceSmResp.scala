package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class ReplaceSmResp extends PDU(CommandId.replace_sm_resp) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
