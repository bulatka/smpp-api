package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class EnquireLinkResp extends PDU(CommandId.enquire_link_resp) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
