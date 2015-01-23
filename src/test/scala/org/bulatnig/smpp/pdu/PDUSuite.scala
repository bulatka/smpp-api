package org.bulatnig.smpp.pdu

import org.scalatest.FunSuite

class PDUSuite extends FunSuite {

  test("BindReceiver constructor should create valid PDU") {
    val bind = new BindReceiver()
    bind.systemId = "test1"
    bind.password = "test2"
    bind.systemType = "test3"
    bind.interfaceVersion = 0x34
    bind.addrTon = TON.International
    bind.addrNpi = NPI.Data
    bind.addressRange = "test4"
    assert(bind.toBuffer().toHexString == "0000002B000000010000000000000000746573743100746573743200746573743300340103746573743400")
  }

  test("PDU + TLV should create valid PDU") {
    val bind = new BindReceiver()
    val tlv = new TLV(Tag.dest_addr_subunit, 2, 30000)
    bind.tlvs(Tag.dest_addr_subunit) = tlv
    assert(bind.toBuffer().toHexString == "0000001D00000001000000000000000000000000000000000500027530")
  }

}
