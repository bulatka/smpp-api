package org.bulatnig.smpp.pdu

abstract class Pdu(val commandId: Int) {

  var commandStatus = CommandStatus.ESME_ROK
  var sequenceNumber = 0

  def toBytes: Array[Byte]
  def commandLength = toBytes.length

}
