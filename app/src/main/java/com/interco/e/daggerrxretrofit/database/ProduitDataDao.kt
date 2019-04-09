package com.interco.e.daggerrxretrofit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.interco.e.daggerrxretrofit.database.entity.ProduitData
import io.reactivex.Observable


/**
 * Created by emine on 31/01/2019.
 */
@Dao
interface ProduitDataDao {
    @Query("SELECT * from produitData")
    fun getAll(): Observable<List<ProduitData>>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: ProduitData)

    @Query("DELETE from produitData")
    fun deleteAll()
}