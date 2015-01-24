package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class EnquireLink extends PDU(CommandId.enquire_link) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
