package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class GenericNack extends PDU(CommandId.generic_nack) {

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

}
