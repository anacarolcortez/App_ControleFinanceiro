package com.dio.controlefinanceiro.extension

fun String.limitaCaracteres(caracteres: Int): String{
    if (this.length > caracteres){
        return "${this.substring(0, caracteres)}..."
    }
    return this
}