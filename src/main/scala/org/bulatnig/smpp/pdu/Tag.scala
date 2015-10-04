package org.bulatnig.smpp.pdu

/**
 * TLV Tag
 */
object Tag {

  val dest_addr_subunit	= 0x0005
  val dest_network_type	= 0x0006
  val dest_bearer_type = 0x0007
  val dest_telematics_id = 0x0008
  val source_addr_subunit = 0x000D
  val source_network_type = 0x000E
  val source_bearer_type = 0x000F
  val source_telematics_id = 0x0010
  val qos_time_to_live = 0x0017
  val payload_type = 0x0019
  val additional_status_info_text = 0x001D
  val receipted_message_id = 0x001E
  val ms_msg_wait_facilities = 0x0030
  val privacy_indicator = 0x0201
  val source_subaddress = 0x0202
  val dest_subaddress = 0x0203
  val user_message_reference = 0x0204
  val user_response_code = 0x0205
  val source_port = 0x020A
  val destination_port = 0x020B
  val sar_msg_ref_num = 0x020C
  val language_indicator = 0x020D
  val sar_total_segments = 0x020E
  val sar_segment_seqnum = 0x020F
  val SC_interface_version = 0x0210
  val callback_num_pres_ind = 0x0302
  val callback_num_atag = 0x0303
  val number_of_messages = 0x0304
  val callback_num = 0x0381
  val dpf_result = 0x0420
  val set_dpf = 0x0421
  val ms_availability_status = 0x0422
  val network_error_code = 0x0423
  val message_payload = 0x0424
  val delivery_failure_reason = 0x0425
  val more_messages_to_send = 0x0426
  val message_state = 0x0427
  val ussd_service_op = 0x0501
  val display_time = 0x1201
  val sms_signal = 0x1203
  val ms_validity = 0x1204
  val alert_on_message_delivery = 0x130C
  val its_reply_type = 0x1380
  val its_session_info = 0x1383

}
