package org.bulatnig.smpp.pdu

/**
 * Short message allowed states.
 *
 * The MC returns the message_state value to the ESME as part of the query_sm_resp or query_broadcast_sm_resp PDU.
 * Intermediate states are states that can change.
 * Final states are states that represent an end of life state for a message.
 */
object MessageState {

  val SCHEDULED = 0
  val ENROUTE = 1
  val DELIVERED = 2
  val EXPIRED = 3
  val DELETED = 4
  val UNDELIVERABLE = 5
  val ACCEPTED = 6
  val UNKNOWN = 7
  val REJECTED = 8
  val SKIPPED = 9

}
