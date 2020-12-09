// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The update user password request body.
 */
@Serializable
public data class UpdateUserPassword(
	/**
	 * Gets or sets the current sha1-hashed password.
	 */
	@SerialName("CurrentPassword")
	public val currentPassword: String? = null,
	/**
	 * Gets or sets the current plain text password.
	 */
	@SerialName("CurrentPw")
	public val currentPw: String? = null,
	/**
	 * Gets or sets the new plain text password.
	 */
	@SerialName("NewPw")
	public val newPw: String? = null,
	/**
	 * Gets or sets a value indicating whether to reset the password.
	 */
	@SerialName("ResetPassword")
	public val resetPassword: Boolean
)