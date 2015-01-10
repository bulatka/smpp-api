package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

abstract class PDU(val commandId: Int) {

  var commandStatus = CommandStatus.ESME_ROK
  var sequenceNumber = 0

  def toBuffer() = {
    val body = getBodyBytes()
    val buffer = new Buffer()
    buffer.appendInt(PDU.HeaderLength + body.length)
    buffer.appendInt(commandId)
    buffer.appendInt(commandStatus)
    buffer.appendInt(sequenceNumber)
    buffer ++= body
    buffer
  }

  protected def getBodyBytes() = getStdParamBytes() ++ getTlvBytes()

  protected def getStdParamBytes() = new Buffer()

  protected def getTlvBytes() = new Buffer()

  def commandLength() = toBuffer().length

}

object PDU {
  val HeaderLength = 16
}
