// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DayOfWeek(
	public val serialName: String
) {
	@SerialName("Sunday")
	SUNDAY("Sunday"),
	@SerialName("Monday")
	MONDAY("Monday"),
	@SerialName("Tuesday")
	TUESDAY("Tuesday"),
	@SerialName("Wednesday")
	WEDNESDAY("Wednesday"),
	@SerialName("Thursday")
	THURSDAY("Thursday"),
	@SerialName("Friday")
	FRIDAY("Friday"),
	@SerialName("Saturday")
	SATURDAY("Saturday"),
	;

	public override fun toString(): String = serialName
}