package com.example.dollar_room_pm.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dollar_room_pm.Model.Local.DollarTrasanction
import com.example.dollar_room_pm.R
import com.example.dollar_room_pm.databinding.ItemDollarBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    // Lista interna de transacciones
    private var transaction = listOf<DollarTrasanction>()

    // Método para actualizar la lista desde fuera del adapter
    fun submitList(newList: List<DollarTrasanction>){

        transaction = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemDollarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        holder.bind(transaction[position])
    }

    override fun getItemCount(): Int = transaction.size


    inner class TransactionViewHolder(private val binding: ItemDollarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: DollarTrasanction) {

            // Mostramos tipo y monto
            val context = binding.root.context

            if (transaction.type == "BUY") {
                binding.textTypeAmount.text = "🟢 Compra +${transaction.amount} USD"
                binding.textTypeAmount.setTextColor(context.getColor(R.color.green))
            } else {
                binding.textTypeAmount.text = "🔴 Venta -${transaction.amount} USD"
                binding.textTypeAmount.setTextColor(context.getColor(R.color.red))
            }
            // Formateamos timestamp a "dd/MM/yyyy HH:mm"

            val formatteDate = SimpleDateFormat(

                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(Date(transaction.timestamp))

            binding.textTimestamp.text = formatteDate

        }
    }
}
