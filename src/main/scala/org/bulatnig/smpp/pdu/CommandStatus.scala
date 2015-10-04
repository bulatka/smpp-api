package org.bulatnig.smpp.pdu

/**
 * Success or failure indicator of SMPP request.
 *
 * It is relevant only in the SMPP response message and should be set to NULL in SMPP request messages.
 */
object CommandStatus {

  val ESME_ROK = 0x00000000 // No Error
  val ESME_RINVMSGLEN = 0x00000001 // Message Length is invalid
  val ESME_RINVCMDLEN = 0x00000002 // Command Length is invalid
  val ESME_RINVCMDID = 0x00000003 // Invalid Command ID
  val ESME_RINVBNDSTS = 0x00000004 // Incorrect BIND Status for given command
  val ESME_RALYBND = 0x00000005 // ESME Already in Bound State
  val ESME_RINVPRTFLG = 0x00000006 // Invalid Priority Flag
  val ESME_RINVREGDLVFLG = 0x00000007 // Invalid Registered Delivery Flag
  val ESME_RSYSERR = 0x00000008 // System Error
  val ESME_RINVSRCADR = 0x0000000A // Invalid Source Address
  val ESME_RINVDSTADR = 0x0000000B // Invalid Dest Addr
  val ESME_RINVMSGID = 0x0000000C // Message ID is invalid
  val ESME_RBINDFAIL = 0x0000000D // Bind Failed
  val ESME_RINVPASWD = 0x0000000E // Invalid Password
  val ESME_RINVSYSID = 0x0000000F // Invalid System ID
  val ESME_RCANCELFAIL = 0x00000011 // Cancel SM Failed
  val ESME_RREPLACEFAIL = 0x00000013 // Replace SM Failed
  val ESME_RMSGQFUL = 0x00000014 // Message Queue Full
  val ESME_RINVSERTYP = 0x00000015 // Invalid Service Type
  val ESME_RINVNUMDESTS = 0x00000033 // Invalid number of destinations
  val ESME_RINVDLNAME = 0x00000034 // Invalid Distribution List name
  val ESME_RINVDESTFLAG = 0x00000040 // Destination flag is invalid (submit_multi)
  val ESME_RINVSUBREP = 0x00000042 // Invalid â€˜submit with replaceâ€™ request (ie submit_sm with replace_if_present_flag set)
  val ESME_RINVESMCLASS = 0x00000043 // Invalid esm_class field data
  val ESME_RCNTSUBDL = 0x00000044 // Cannot Submit to Distribution List
  val ESME_RSUBMITFAIL = 0x00000045 // submit_sm or submit_multi failed
  val ESME_RINVSRCTON = 0x00000048 // Invalid Source address TON
  val ESME_RINVSRCNPI = 0x00000049 // Invalid Source address NPI
  val ESME_RINVDSTTON = 0x00000050 // Invalid Destination address TON
  val ESME_RINVDSTNPI = 0x00000051 // Invalid Destination address NPI
  val ESME_RINVSYSTYP = 0x00000053 // Invalid system_type field
  val ESME_RINVREPFLAG = 0x00000054 // Invalid replace_if_present flag
  val ESME_RINVNUMMSGS = 0x00000055 // Invalid number of messages
  val ESME_RTHROTTLED = 0x00000058 // Throttling error (ESME has exceeded allowed message limits)
  val ESME_RINVSCHED = 0x00000061 // Invalid Scheduled Delivery Time
  val ESME_RINVEXPIRY = 0x00000062 // Invalid message validity period (Expiry time)
  val ESME_RINVDFTMSGID = 0x00000063 // Predefined Message Invalid or Not Found
  val ESME_RX_T_APPN = 0x00000064 // ESME Receiver Temporary App Error Code
  val ESME_RX_P_APPN = 0x00000065 // ESME Receiver Permanent App Error Code
  val ESME_RX_R_APPN = 0x00000066 // ESME Receiver Reject Message Error Code
  val ESME_RQUERYFAIL = 0x00000067 // query_sm request failed
  val ESME_RINVOPTPARSTREAM = 0x000000C0  // Error in the optional part of the PDU Body
  val ESME_ROPTPARNOTALLWD = 0x000000C1 // Optional Parameter not allowed
  val ESME_RINVPARLEN = 0x000000C2 // Invalid Parameter Length
  val ESME_RMISSINGOPTPARAM = 0x000000C3 // Expected Optional Parameter missing
  val ESME_RINVOPTPARAMVAL = 0x000000C4 // Invalid Optional Parameter Value
  val ESME_RDELIVERYFAILURE = 0x000000FE // Delivery Failure (used for data_sm_resp)
  val ESME_RUNKNOWNERR = 0x000000FF // Unknown Error
  val ESME_RSERTYPUNAUTH = 0x00000100 // ESME Not authorised to use specified service_type
  val ESME_RPROHIBITED = 0x00000101 // ESME Prohibited from using specified operation
  val ESME_RSERTYPUNAVAIL = 0x00000102 // Specified service_type is unavailable
  val ESME_RSERTYPDENIED = 0x00000103 // Specified service_type is denied
  val ESME_RINVDCS = 0x00000104 // Invalid Data Coding Scheme
  val ESME_RINVSRCADDRSUBUNIT = 0x00000105 // Source Address Sub unit is Invalid
  val ESME_RINVDSTADDRSUBUNIT = 0x00000106 // Destination Address Sub unit is Invalid
  val ESME_RINVBCASTFREQINT = 0x00000107 // Broadcast Frequency Interval is invalid
  val ESME_RINVBCASTALIAS_NAME = 0x00000108 // Broadcast Alias Name is invalid
  val ESME_RINVBCASTAREAFMT = 0x00000109 // Broadcast Area Format is invalid
  val ESME_RINVNUMBCAST_AREAS = 0x0000010A // Number of Broadcast Areas is invalid
  val ESME_RINVBCASTCNTTYPE = 0x0000010B // Broadcast Content Type is invalid
  val ESME_RINVBCASTMSGCLASS = 0x0000010C // Broadcast Message Class is invalid
  val ESME_RBCASTFAIL = 0x0000010D // broadcast_sm operation failed
  val ESME_RBCASTQUERYFAIL = 0x0000010E // query_broadcast_sm operation failed.
  val ESME_RBCASTCANCELFAIL = 0x0000010F // cancel_broadcast_sm operation failed.
  val ESME_RINVBCAST_REP = 0x00000110 // Number of Repeated Broadcasts is invalid
  val ESME_RINVBCASTSRVGRP = 0x00000111 // Broadcast Service Group is invalid
  val ESME_RINVBCASTCHANIND = 0x00000112 // Broadcast Channel Indicator is invalid

}
