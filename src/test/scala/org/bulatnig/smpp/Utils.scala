package org.bulatnig.smpp

import scala.collection.mutable.ArrayBuffer

object Utils {

  def octetStringToBuffer(octetString: String) = {
    val byteBuffer = new ArrayBuffer[Byte]()
    for (i <- 0.until(octetString.length, 2)) {
      val octet = "" + octetString(i) + octetString(i + 1)
      byteBuffer += Integer.parseInt(octet, 16).toByte
    }
    new Buffer(byteBuffer)
  }

}
