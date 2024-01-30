package data.entity

import domain.model.PicturesItemModel
import kotlinx.serialization.Serializable

@Serializable
data class PicturesItemEntity(
    val author: String,
    val category: String,
    val path: String
)

fun PicturesItemEntity.toModel() = PicturesItemModel(author, category, path)