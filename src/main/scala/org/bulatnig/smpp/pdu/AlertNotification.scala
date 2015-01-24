package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

class AlertNotification extends PDU(CommandId.alert_notification) {

  var sourceAddrTon: Int = null
  var sourceAddrNpi: Int = null
  var sourceAddr: String = null
  var esmeAddrTon: Int = null
  var esmeAddrNpi: Int = null
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
