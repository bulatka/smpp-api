package org.bulatnig.smpp.pdu

import org.bulatnig.smpp.Buffer

import scala.collection.mutable

abstract class PDU(val commandId: Int) {

  var commandStatus = CommandStatus.ESME_ROK
  var sequenceNumber = 0
  val tlvs = new mutable.HashMap[Int, TLV]()

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

  protected def getTlvBytes() = tlvs.values.foldLeft(new Buffer()) { (buffer, tlv) => buffer ++= tlv.toBuffer()}

  def commandLength() = toBuffer().length

}

object PDU {
  val HeaderLength = 16
}
