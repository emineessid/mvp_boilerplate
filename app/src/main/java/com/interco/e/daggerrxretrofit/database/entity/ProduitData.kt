package com.interco.e.daggerrxretrofit.database.entity

import androidx.room.*

/**
 * Created by emine on 31/01/2019.
 */
@Entity(tableName = "produitData")
data class ProduitData(@PrimaryKey(autoGenerate = true) var id: Long?
//                       @ColumnInfo(name = "createdDate") var createdDate: String,
//                       @ColumnInfo(name = "updatedDate") var updatedDate: String,
//                       @ColumnInfo(name = "createdBy") var createdBy: String,
//                       @ColumnInfo(name = "modifiedBy") var modifiedBy: String,
//                       @ColumnInfo(name = "barCode") var barCode: String,
//                       @ColumnInfo(name = "title") var title: String,
//                       @ColumnInfo(name = "authors") var authors: List<String>?,
//                       @ColumnInfo(name = "publicationDate") var publicationDate: String,
//                       @ColumnInfo(name = "totalQuantity") var totalQuantity: Int,
//                       @ColumnInfo(name = "availableQuantity") var availableQuantity: Int,
//                       @ColumnInfo(name = "image") var image: String,
//                       @Embedded(prefix = "produi_") var category: Category?


) {

//    constructor() : this(null, Date().toString(), "", "", "", "", "", null
//            , "", 0, 0, "", null)
}



