package org.bulatnig.smpp.pdu

/**
 * Type of Number
 */
object TON {

  val Unknown = 0x00
  val International = 0x01
  val National = 0x02
  val NationalSpecific = 0x03
  val SubscriberNumber = 0x04
  val AlphaNumeric = 0x05
  val Abbreviated = 0x06

}
