package com.dio.controlefinanceiro.ui.activity

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.dio.controlefinanceiro.R
import com.dio.controlefinanceiro.extension.formataMoeda
import com.dio.controlefinanceiro.model.TipoTransacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context, private val view: View) {

    private var receitas = somarReceitas()
    private var despesas = somarDespesas()

    private fun somarReceitas(): BigDecimal {

        val totalReceita = transacoes
                .filter { transacao -> transacao.tipo == TipoTransacao.RECEITA }
                .sumByDouble { transacao -> transacao.valor.toDouble() }

        with(view.resumo_card_receita){
            text = BigDecimal(totalReceita).formataMoeda()
            setTextColor(ContextCompat.getColor(context, R.color.receita))
        }

        return BigDecimal(totalReceita)
    }

    private fun somarDespesas(): BigDecimal {

        val totalDespesa = transacoes
                .filter { transacao -> transacao.tipo == TipoTransacao.DESPESA  }
                .sumByDouble { transacao -> transacao.valor.toDouble()  }

        with(view.resumo_card_despesa){
            text = BigDecimal(totalDespesa).formataMoeda()
            setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }

        return BigDecimal(totalDespesa)
    }

    private fun somarTotal(){
        val total = (receitas - despesas)
        var cor = corResumo(total)

        with(view.resumo_card_total){
            text = total.formataMoeda()
            setTextColor(cor)
        }
    }

    private fun corResumo(valor: BigDecimal) : Int{
        return if (valor.compareTo(BigDecimal.ZERO) >= 0){
            ContextCompat.getColor(context, R.color.receita)
        } else {
            ContextCompat.getColor(context, R.color.despesa)
        }
    }

    fun atualizaResumo(){
        somarReceitas()
        somarDespesas()
        somarTotal()
    }

}