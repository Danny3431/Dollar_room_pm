package com.example.dollar_room_pm.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dollar_room_pm.Model.Local.AppDatabase
import com.example.dollar_room_pm.Model.TransactionRepository
import com.example.dollar_room_pm.ViewModel.TransactionViewModel
import com.example.dollar_room_pm.ViewModel.TransactionViewModelFactory
import com.example.dollar_room_pm.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // Adapter RecyclerView
    private val adapter = TransactionAdapter()

    // Repository y ViewModel
    private lateinit var repository: TransactionRepository
    private lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Inicializar Repository
        repository = TransactionRepository(
            AppDatabase.getDataBase(requireContext()).dollarTransactionDao()
        )

        // 🔹 Inicializar ViewModel correctamente
        viewModel = ViewModelProvider(
            this,
            TransactionViewModelFactory(repository)
        )[TransactionViewModel::class.java]

        // 🔹 Configurar RecyclerView
        binding.transactionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.transactionRecyclerView.adapter = adapter

        // 🔹 Observar lista de transacciones
        viewModel.transaction.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // 🔹 Observar saldo
        viewModel.balance.observe(viewLifecycleOwner) { saldo ->
            binding.tvBalance.text = "Saldo: $saldo USD"
        }

        // 🔹 Observar mensajes
        viewModel.message.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        // 🔹 Cargar datos iniciales
        viewModel.loadTransactions()

        // 🔹 Botón comprar
        binding.buyButton.setOnClickListener {
            handleTransaction("BUY")
        }

        // 🔹 Botón vender
        binding.sellButton.setOnClickListener {
            handleTransaction("SELL")
        }
    }

    private fun handleTransaction(type: String) {

        val amountText = binding.amountEditText.text.toString()

        if (amountText.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese un monto", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDouble()

        // 🔹 Llamamos al ViewModel (lógica real)
        viewModel.addTransaction(type, amount)

        binding.amountEditText.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
