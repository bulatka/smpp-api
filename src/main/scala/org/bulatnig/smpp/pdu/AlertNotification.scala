package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class AlertNotification extends PDU(CommandId.alert_notification) {

  var sourceAddrTon = TON.Unknown
  var sourceAddrNpi = NPI.Unknown
  var sourceAddr: String = null
  var esmeAddrTon = TON.Unknown
  var esmeAddrNpi = NPI.Unknown
  var esmeAddr: String = null

  def this(buffer: Buffer) {
    this()
    parse(buffer)
  }

  override protected def parseStdParams(buffer: Buffer) {
    sourceAddrTon = buffer.readByte()
    sourceAddrNpi = buffer.readByte()
    sourceAddr = buffer.readString()
    esmeAddrTon = buffer.readByte()
    esmeAddrNpi = buffer.readByte()
    esmeAddr = buffer.readString()
  }

  override protected def getStdParamBytes() = {
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
