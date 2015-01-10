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

}
