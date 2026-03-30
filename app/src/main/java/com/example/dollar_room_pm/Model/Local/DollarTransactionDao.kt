package com.example.dollar_room_pm.Model.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dollar_room_pm.Model.Local.DollarTrasanction

@Dao
interface DollarTransactionDao {



    @Insert
    suspend fun insertTransaction(transaction: DollarTrasanction)

    @Query("SELECT * FROM dolar_transacctions ORDER BY timestamp DESC")
    suspend fun getAllTransactions(): List<DollarTrasanction>
}