package com.example.praktekappesjumbo.ui.theme

import androidx.lifecycle.ViewModel
import com.example.praktekappesjumbo.data.ContactUiState
import com.example.praktekappesjumbo.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 3000

class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    private val _namaSTATE = MutableStateFlow(ContactUiState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()
    val namaST: StateFlow<ContactUiState> = _namaSTATE.asStateFlow()

    fun setNama(list: MutableList<String>){
        _namaSTATE.update { stateSaatIni -> stateSaatIni.copy(
            nama = list[0],
            alamat = list[1],
            tlp = list[2]
        )
        }
    }

    fun setJumlah(jmlEsJumbo: Int) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }

    fun setRasa(rasaPilihan: String) {
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(rasa = rasaPilihan) }

    }

    fun resetOrder() {
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(jumlah: Int = _stateUI.value.jumlah): String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getInstance().format(kalkulasiHarga)
    }
}