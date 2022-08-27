// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Collection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.api.ItemFields
import org.jellyfin.sdk.model.api.SortOrder
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Get years.
 */
@Serializable
public data class GetYearsRequest(
	/**
	 * Skips over a given number of items within the results. Use for paging.
	 */
	@SerialName("startIndex")
	public val startIndex: Int? = null,
	/**
	 * Optional. The maximum number of records to return.
	 */
	@SerialName("limit")
	public val limit: Int? = null,
	/**
	 * Sort Order - Ascending,Descending.
	 */
	@SerialName("sortOrder")
	public val sortOrder: Collection<SortOrder>? = null,
	/**
	 * Specify this to localize the search to a specific item or folder. Omit to use the root.
	 */
	@SerialName("parentId")
	public val parentId: UUID? = null,
	/**
	 * Optional. Specify additional fields of information to return in the output.
	 */
	@SerialName("fields")
	public val fields: Collection<ItemFields>? = null,
	/**
	 * Optional. If specified, results will be excluded based on item type. This allows multiple, comma
	 * delimited.
	 */
	@SerialName("excludeItemTypes")
	public val excludeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional. If specified, results will be included based on item type. This allows multiple, comma
	 * delimited.
	 */
	@SerialName("includeItemTypes")
	public val includeItemTypes: Collection<BaseItemKind>? = null,
	/**
	 * Optional. Filter by MediaType. Allows multiple, comma delimited.
	 */
	@SerialName("mediaTypes")
	public val mediaTypes: Collection<String>? = null,
	/**
	 * Optional. Specify one or more sort orders, comma delimited. Options: Album, AlbumArtist, Artist,
	 * Budget, CommunityRating, CriticRating, DateCreated, DatePlayed, PlayCount, PremiereDate,
	 * ProductionYear, SortName, Random, Revenue, Runtime.
	 */
	@SerialName("sortBy")
	public val sortBy: Collection<String>? = null,
	/**
	 * Optional. Include user data.
	 */
	@SerialName("enableUserData")
	public val enableUserData: Boolean? = null,
	/**
	 * Optional. The max number of images to return, per image type.
	 */
	@SerialName("imageTypeLimit")
	public val imageTypeLimit: Int? = null,
	/**
	 * Optional. The image types to include in the output.
	 */
	@SerialName("enableImageTypes")
	public val enableImageTypes: Collection<ImageType>? = null,
	/**
	 * User Id.
	 */
	@SerialName("userId")
	public val userId: UUID? = null,
	/**
	 * Search recursively.
	 */
	@SerialName("recursive")
	public val recursive: Boolean? = true,
	/**
	 * Optional. Include image information in output.
	 */
	@SerialName("enableImages")
	public val enableImages: Boolean? = true,
)