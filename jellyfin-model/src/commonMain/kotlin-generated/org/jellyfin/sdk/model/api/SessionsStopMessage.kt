// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Sessions stop message.
 */
@Serializable
@SerialName("SessionsStop")
public class SessionsStopMessage : InboundWebSocketMessage {
	/**
	 * The different kinds of messages that are used in the WebSocket api.
	 */
	override val messageType: SessionMessageType = SessionMessageType.SESSIONS_STOP
}