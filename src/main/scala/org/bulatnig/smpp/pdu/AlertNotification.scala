package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

case class AlertNotification(override val commandStatus: Int = CommandStatus.ESME_ROK,
                             override val sequenceNumber: Int = 0,
                             sourceAddrTon: Int = TON.Unknown, sourceAddrNpi: Int = NPI.Unknown, sourceAddr: String = null,
                             esmeAddrTon: Int = TON.Unknown, esmeAddrNpi: Int = NPI.Unknown, esmeAddr: String = null,
                             override val tlvs: List[TLV] = List())
  extends PDU(CommandId.alert_notification, commandStatus, sequenceNumber, tlvs) {

  override protected def getStdParamBytes = {
    val buffer = new Buffer()
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer.appendByte(esmeAddrTon)
    buffer.appendByte(esmeAddrNpi)
    buffer.appendString(esmeAddr)
    buffer
  }

}

object AlertNotification {

  def apply(buffer: Buffer) = {
    val header = PDU.parseHeader(buffer)
    new AlertNotification(header.commandStatus, header.sequenceNumber,
      buffer.readByte(), buffer.readByte(), buffer.readString(),
      buffer.readByte(), buffer.readByte(), buffer.readString(),
      PDU.parseTlvs(buffer))
  }

}
