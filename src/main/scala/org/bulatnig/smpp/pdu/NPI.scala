package org.bulatnig.smpp.pdu

/**
 * Numeric Plan Indicator
 */
object NPI {

  val Unknown = 0x00
  val ISDN = 0x01
  val Data = 0x03
  val Telex = 0x04
  val LandMobile = 0x06
  val National = 0x08
  val Private = 0x09
  val ERMES = 0x10
  val Internet = 0x14
  val WapClientId = 0x18

}
