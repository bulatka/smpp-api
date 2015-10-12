package org.bulatnig.smpp.pdu

trait BindRequest extends PDU {

  def systemId: String
  def password: String
  def systemType: String
  def interfaceVersion: Int
  def addrTon: Int
  def addrNpi: Int
  def addressRange: String

}
