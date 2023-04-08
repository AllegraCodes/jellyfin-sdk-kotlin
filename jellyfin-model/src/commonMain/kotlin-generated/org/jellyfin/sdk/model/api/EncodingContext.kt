// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlin.requireNotNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class EncodingContext(
	public val serialName: String,
) {
	@SerialName("Streaming")
	STREAMING("Streaming"),
	@SerialName("Static")
	STATIC("Static"),
	;

	public override fun toString(): String = serialName

	public companion object {
		public fun fromNameOrNull(serialName: String): EncodingContext? = when (serialName) {
			"Streaming" -> STREAMING
			"Static" -> STATIC
			else -> null
		}

		public fun fromName(serialName: String): EncodingContext =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
