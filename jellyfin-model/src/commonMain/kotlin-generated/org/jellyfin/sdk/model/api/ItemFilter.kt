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

/**
 * Enum ItemFilter.
 */
@Serializable
public enum class ItemFilter(
	public val serialName: String,
) {
	@SerialName("IsFolder")
	IS_FOLDER("IsFolder"),
	@SerialName("IsNotFolder")
	IS_NOT_FOLDER("IsNotFolder"),
	@SerialName("IsUnplayed")
	IS_UNPLAYED("IsUnplayed"),
	@SerialName("IsPlayed")
	IS_PLAYED("IsPlayed"),
	@SerialName("IsFavorite")
	IS_FAVORITE("IsFavorite"),
	@SerialName("IsResumable")
	IS_RESUMABLE("IsResumable"),
	@SerialName("Likes")
	LIKES("Likes"),
	@SerialName("Dislikes")
	DISLIKES("Dislikes"),
	@SerialName("IsFavoriteOrLikes")
	IS_FAVORITE_OR_LIKES("IsFavoriteOrLikes"),
	;

	public override fun toString(): String = serialName

	public companion object {
		public fun fromNameOrNull(serialName: String): ItemFilter? = when (serialName) {
			"IsFolder" -> IS_FOLDER
			"IsNotFolder" -> IS_NOT_FOLDER
			"IsUnplayed" -> IS_UNPLAYED
			"IsPlayed" -> IS_PLAYED
			"IsFavorite" -> IS_FAVORITE
			"IsResumable" -> IS_RESUMABLE
			"Likes" -> LIKES
			"Dislikes" -> DISLIKES
			"IsFavoriteOrLikes" -> IS_FAVORITE_OR_LIKES
			else -> null
		}

		public fun fromName(serialName: String): ItemFilter =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}
