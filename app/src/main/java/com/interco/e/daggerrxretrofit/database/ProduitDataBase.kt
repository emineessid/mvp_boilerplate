package com.interco.e.daggerrxretrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interco.e.daggerrxretrofit.database.entity.ProduitData
import com.interco.e.daggerrxretrofit.utils.Listconverter
import javax.inject.Inject

/**
 * Created by emine on 31/01/2019.
 */
@Database(entities = arrayOf(ProduitData::class), version = 1, exportSchema = false)
@TypeConverters(Listconverter::class)
abstract class ProduitDataBase : RoomDatabase() {


    abstract fun ProduitDataBase(): ProduitDataDao

    companion object {
        private var INSTANCE: ProduitDataBase? = null

        fun getInstance(context: Context): ProduitDataBase? {
            if (INSTANCE == null) {
                synchronized(ProduitDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProduitDataBase::class.java, "produits-soat.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}