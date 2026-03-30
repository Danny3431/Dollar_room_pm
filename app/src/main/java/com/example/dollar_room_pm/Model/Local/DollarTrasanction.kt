package com.example.dollar_room_pm.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dolar_transacctions")
data class DollarTrasanction(

    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val type : String,// "BUY" O "SELL"
    val amount: Double,
    val timestamp : Long = System.currentTimeMillis()






)