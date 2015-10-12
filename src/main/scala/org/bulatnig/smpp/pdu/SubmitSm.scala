package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

case class SubmitSm(override val commandStatus: Int = CommandStatus.ESME_ROK,
                    override val sequenceNumber: Int = 0,
                    serviceType: String = null, sourceAddrTon: Int = TON.Unknown, sourceAddrNpi: Int = NPI.Unknown,
                    sourceAddr: String = null, destAddrTon: Int = TON.Unknown, destAddrNpi: Int = NPI.Unknown,
                    destinationAddr: String = null, esmClass: Int = 0, protocolId: Int = 0, priorityFlag: Int = 0,
                    scheduleDeliveryTime: String = null, validityPeriod: String = null, registeredDelivery: Int = 0,
                    replaceIfPresentFlag: Int = 0, dataCoding: Int = 0, smDefaultMsgId: Int = 0,
                    shortMessage: Array[Byte] = null,
                    override val tlvs: List[TLV] = List())
  extends PDU(CommandId.submit_sm, commandStatus, sequenceNumber, tlvs) {

  override protected def getStdParamBytes = {
    val buffer = new Buffer()
    buffer.appendString(serviceType)
    buffer.appendByte(sourceAddrTon)
    buffer.appendByte(sourceAddrNpi)
    buffer.appendString(sourceAddr)
    buffer.appendByte(destAddrTon)
    buffer.appendByte(destAddrNpi)
    buffer.appendString(destinationAddr)
    buffer.appendByte(esmClass)
    buffer.appendByte(protocolId)
    buffer.appendByte(priorityFlag)
    buffer.appendString(scheduleDeliveryTime)
    buffer.appendString(validityPeriod)
    buffer.appendByte(registeredDelivery)
    buffer.appendByte(replaceIfPresentFlag)
    buffer.appendByte(dataCoding)
    buffer.appendByte(smDefaultMsgId)
    if (shortMessage == null) {
      buffer.appendByte(0)
    } else {
      buffer.appendByte(shortMessage.length)
      buffer ++= shortMessage
    }
    buffer
  }

}

object SubmitSm {

  def apply(buffer: Buffer) = {
    val header = PDU.parseHeader(buffer)
    new SubmitSm(header.commandStatus, header.sequenceNumber,
    buffer.readString(),
    buffer.readByte(), buffer.readByte(), buffer.readString(),
    buffer.readByte(), buffer.readByte(), buffer.readString(),
    buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readString(), buffer.readString(),
    buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), {
      val smLength = buffer.readByte()
      if (smLength > 0) buffer.read(smLength)
      else Array.empty
    },
    PDU.parseTlvs(buffer))
  }

}
