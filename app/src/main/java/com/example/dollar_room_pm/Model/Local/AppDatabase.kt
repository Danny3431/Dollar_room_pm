package com.example.dollar_room_pm.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dollar_room_pm.Model.Local.DollarTransactionDao
import com.example.dollar_room_pm.Model.Local.DollarTrasanction

@Database( entities = [DollarTrasanction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


    // DAO para acceder a las operaciones de la tabla

    abstract fun dollarTransactionDao(): DollarTransactionDao


    companion object{


         @Volatile
          private var INSTANCE : AppDatabase? = null


        fun getDataBase( context: Context): AppDatabase{

            // Si ya existe una instancia, se retorna
            return INSTANCE ?: synchronized(this) {

                // Si no existe, se crea la base de datos
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDatabase::class.java,
                     "dollar_database"
                 ).build()

                INSTANCE= instance
                instance
            }




        }






    }



}