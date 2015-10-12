package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Utils
import org.scalatest.FunSuite

class PDUSuite extends FunSuite {

  test("BindTransceiver should serialize to valid octets") {
    val bind = new BindTransceiver(0, 123, "test1", "test2", "test3", 0x34, TON.International, NPI.Data, "test4")
    assert(bind.toBuffer.toHexString == "0000002B00000009000000000000007B746573743100746573743200746573743300340103746573743400")
  }

  test("BindTransceiver octets should be parsed correctly") {
    val octets = "0000002B00000009000000050000007B746573743100746573743200746573743300340103746573743400"
    val bind = BindTransceiver(Utils.octetStringToBuffer(octets))
    assert(bind.commandStatus == CommandStatus.ESME_RALYBND)
    assert(bind.sequenceNumber == 123)
    assert(bind.systemId == "test1")
    assert(bind.password == "test2")
    assert(bind.systemType == "test3")
    assert(bind.interfaceVersion == 0x34)
    assert(bind.addrTon == TON.International)
    assert(bind.addrNpi == NPI.Data)
    assert(bind.addressRange == "test4")
  }

  test("Parse wrong type of PDU should produce ") {
    val octets = "00000010000000150000000000000000"
    val buffer = Utils.octetStringToBuffer(octets)
    intercept[IndexOutOfBoundsException] {
      BindTransceiver(buffer)
    }
  }

  test("PDU + TLV should create valid PDU") {
    val tlv = new TLV(Tag.dest_addr_subunit, 2, 30000)
    val bind = new BindTransceiver(tlvs = List(tlv))
    assert(bind.toBuffer.toHexString == "0000001D00000009000000000000000000000000000000000500027530")
  }

}
