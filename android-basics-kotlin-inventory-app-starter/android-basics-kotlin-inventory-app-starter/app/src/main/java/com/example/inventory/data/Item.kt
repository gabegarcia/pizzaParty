package com.example.inventory.data

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item")
data class Item (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val itemTitle: String,
    @ColumnInfo(name = "series")
    val itemSeries: Int,
    @ColumnInfo(name = "issue")
    val itemIssue: Int,
    @ColumnInfo(name = "cost")
    val itemCost: Double

)

fun Item.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(itemCost)

