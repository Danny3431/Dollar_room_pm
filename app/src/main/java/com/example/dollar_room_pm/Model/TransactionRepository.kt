package com.example.dollar_room_pm.Model

import com.example.dollar_room_pm.Model.Local.DollarTransactionDao
import com.example.dollar_room_pm.Model.Local.DollarTrasanction

class TransactionRepository( private val dao : DollarTransactionDao) {




    suspend fun insertTransaction(trasanction: DollarTrasanction){
        dao.insertTransaction(trasanction)
    }

    suspend fun  getAllTransacctions(): List<DollarTrasanction>{

        return dao.getAllTransactions()
    }



}