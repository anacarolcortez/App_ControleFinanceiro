package com.dio.controlefinanceiro.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataMoeda(): String{
    val moeda = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    val moedaFormatada = moeda.format(this)
    return moedaFormatada
}