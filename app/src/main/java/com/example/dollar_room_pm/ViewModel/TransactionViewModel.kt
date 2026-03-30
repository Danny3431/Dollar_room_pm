package com.example.dollar_room_pm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dollar_room_pm.Model.Local.DollarTrasanction
import com.example.dollar_room_pm.Model.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository): ViewModel(){

    // LiveData interna para almacenar la lista de transacciones
    // MutableLiveData permite modificar los datos dentro del ViewModel
    private val _transactions = MutableLiveData<List<DollarTrasanction>>()

    // Exposición pública (solo lectura)
    // La UI (Activity/Fragment) puede observar pero NO modificar
    val transaction: LiveData<List<DollarTrasanction>> = _transactions


    // 🟢 NUEVO: saldo actual del usuario
    // Se calcula en base a compras y ventas
    private val _balance = MutableLiveData<Double>(0.0)
    val balance: LiveData<Double> = _balance


    // 🟢 NUEVO: mensajes para mostrar en pantalla (Toast)
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        loadTransactions()
    }

    // 🟢 Función para cargar las transacciones desde el repositorio
    fun loadTransactions() {
        viewModelScope.launch {
            val list = repository.getAllTransacctions()
            _transactions.postValue(list)
            calculateBalance(list)
        }
    }

    fun addTransaction(type: String, amount: Double){

        viewModelScope.launch {

            // 🔹 Obtener lista actual desde BD (fuente real)
            val list = repository.getAllTransacctions()

            // 🔹 Calcular saldo real en ese momento
            var currentBalance = 0.0
            for(t in list){
                if(t.type == "BUY") currentBalance += t.amount
                else if(t.type == "SELL") currentBalance -= t.amount
            }

            // 🔴 Validación REAL
            if(type == "SELL" && amount > currentBalance){
                _message.postValue("❌ Saldo insuficiente")
                return@launch
            }

            // 🔹 Insertar transacción
            val transaction = DollarTrasanction(type = type, amount = amount)
            repository.insertTransaction(transaction)

            // 🔹 Recargar lista
            loadTransactions()

            _message.postValue("✅ Transacción realizada")
        }
    }

    // 🟢 Función privada para calcular el saldo total
    private fun calculateBalance(list: List<DollarTrasanction>){

        var total = 0.0

        // Recorremos todas las transacciones
        for(t in list){

            // Si es compra → suma
            if(t.type == "BUY"){
                total += t.amount
            }

            // Si es venta → resta
            else if(t.type == "SELL"){
                total -= t.amount
            }
        }

        // Actualizamos el saldo
        _balance.postValue(total)
    }
}


// Factory para crear el ViewModel correctamente (inyección del repositorio)
class TransactionViewModelFactory(private val repository: TransactionRepository):
    ViewModelProvider.Factory{

    // Este método crea la instancia del ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Verificamos que sea el ViewModel correcto
        if(modelClass.isAssignableFrom(TransactionViewModel::class.java)){

            @Suppress("UNCHECKED_CAST")

            // Retornamos el ViewModel con el repositorio
            return TransactionViewModel(repository) as T
        }

        // Error si se intenta crear otro ViewModel
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
