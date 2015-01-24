package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class UnbindResp extends PDU(CommandId.unbind_resp) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
