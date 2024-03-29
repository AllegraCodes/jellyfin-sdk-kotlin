// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class GroupStateUpdate.
 */
@Serializable
public data class GroupStateUpdate(
	/**
	 * The state of the group.
	 */
	@SerialName("State")
	public val state: GroupStateType,
	/**
	 * The reason of the state change.
	 */
	@SerialName("Reason")
	public val reason: PlaybackRequestType,
)