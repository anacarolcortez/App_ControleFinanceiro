package com.dio.controlefinanceiro.model

import java.math.BigDecimal
import java.util.Calendar

data class Transacao (
        val valor: BigDecimal,
        val categoria: String,
        val tipo: TipoTransacao,
        val data: Calendar = Calendar.getInstance()){
}