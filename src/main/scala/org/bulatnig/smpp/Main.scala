package org.bulatnig.smpp

import org.bulatnig.smpp.pdu.CommandId

object Main {

  def main(args: Array[String]) = {
    val buffer = new Buffer()
    val s: Short = -32768
    buffer += s
    println(buffer.toHexString)
  }

}
