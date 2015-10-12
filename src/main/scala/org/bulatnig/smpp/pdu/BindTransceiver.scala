package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

case class BindTransceiver(override val commandStatus: Int = CommandStatus.ESME_ROK,
                           override val sequenceNumber: Int = 0,
                           systemId: String = null, password: String = null, systemType: String = null, interfaceVersion: Int = 0,
                           addrTon: Int = TON.Unknown, addrNpi: Int = NPI.Unknown, addressRange: String = null,
                           override val tlvs: List[TLV] = List())
  extends PDU(CommandId.bind_transceiver, commandStatus, sequenceNumber, tlvs) with BindRequest {

  override protected def getStdParamBytes = {
    val buffer = new Buffer()
    buffer.appendString(systemId)
    buffer.appendString(password)
    buffer.appendString(systemType)
    buffer.appendByte(interfaceVersion)
    buffer.appendByte(addrTon)
    buffer.appendByte(addrNpi)
    buffer.appendString(addressRange)
    buffer
  }

}

object BindTransceiver {

  def apply(buffer: Buffer) = {
    val header = PDU.parseHeader(buffer)
    new BindTransceiver(header.commandStatus, header.sequenceNumber,
      buffer.readString(), buffer.readString(), buffer.readString(), buffer.readByte(),
      buffer.readByte(), buffer.readByte(), buffer.readString(),
      PDU.parseTlvs(buffer))
  }

}
