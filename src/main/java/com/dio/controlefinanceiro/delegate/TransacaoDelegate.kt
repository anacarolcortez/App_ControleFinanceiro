package com.dio.controlefinanceiro.delegate

import com.dio.controlefinanceiro.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}