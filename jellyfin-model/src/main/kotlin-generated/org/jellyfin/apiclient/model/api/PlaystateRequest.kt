// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PlaystateRequest(
	@SerialName("Command")
	public val command: PlaystateCommand? = null,
	@SerialName("SeekPositionTicks")
	public val seekPositionTicks: Long? = null,
	/**
	 * Gets or sets the controlling user identifier.
	 */
	@SerialName("ControllingUserId")
	public val controllingUserId: String? = null
)