package com.dio.controlefinanceiro.extension

import java.text.SimpleDateFormat
import java.util.*

//extension function permite criar funções em classes do Kotlin
fun Calendar.formataData(): String {
    val formatoBr = "dd/MM/YYYY"
    val formato = SimpleDateFormat(formatoBr)
    val dataFormatada = formato.format(this.time)
    return dataFormatada
}

fun String.converteParaCalendar(): Calendar {
    val formatoBr = SimpleDateFormat("dd/MM/yyyy")
    val dataFormatada: Date = formatoBr.parse(this)
    val data = Calendar.getInstance()
    data.time = dataFormatada
    return data
}
