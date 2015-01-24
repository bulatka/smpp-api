package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class CancelSmResp extends PDU(CommandId.cancel_sm_resp) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
