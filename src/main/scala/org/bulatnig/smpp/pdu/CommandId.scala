package org.bulatnig.smpp.pdu

/**
 * Identifier of SMPP operation.
 *
 * The command_id is encoded as a 4-octet integer value.
 * Command_ids for request PDUs are allocated from a range of numbers; 0x00000000 to 0x000001FF.
 * Command_ids for response PDUs are allocated from a range of numbers; 0x80000000 to 0x800001FF.
 * The relationship between the command_id for a request PDU and its associated response
 * PDU is that bit 31 is cleared for the request and set for the response.
 */
object CommandId {

  val bind_receiver = 0x00000001
  val bind_transmitter = 0x00000002
  val query_sm = 0x00000003
  val submit_sm = 0x00000004
  val deliver_sm = 0x00000005
  val unbind = 0x00000006
  val replace_sm = 0x00000007
  val cancel_sm = 0x00000008
  val bind_transceiver = 0x00000009
  val outbind = 0x0000000B
  val enquire_link = 0x00000015
  val submit_multi = 0x00000021
  val alert_notification = 0x00000102
  val data_sm = 0x00000103
  val broadcast_sm = 0x00000111
  val query_broadcast_sm = 0x00000112
  val cancel_broadcast_sm = 0x00000113
  val generic_nack = 0x80000000
  val bind_receiver_resp = 0x80000001
  val bind_transmitter_resp = 0x80000002
  val query_sm_resp = 0x80000003
  val submit_sm_resp = 0x80000004
  val deliver_sm_resp = 0x80000005
  val unbind_resp = 0x80000006
  val replace_sm_resp = 0x80000007
  val cancel_sm_resp = 0x80000008
  val bind_transceiver_resp = 0x80000009
  val enquire_link_resp = 0x80000015
  val submit_multi_resp = 0x80000021
  val data_sm_resp = 0x80000103
  val broadcast_sm_resp = 0x80000111
  val query_broadcast_sm_resp = 0x80000112
  val cancel_broadcast_sm_resp = 0x80000113

}
