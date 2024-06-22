package jva.cloud.jvastore.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.ZERO

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "selected")
    var selected: Boolean = BOOLEAN_FALSE,
    @ColumnInfo(name = "selected_quantity")
    var selectedQuantity: Int = ZERO,
    @ColumnInfo(name = "creation_at")
    val creationAt: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String
)
