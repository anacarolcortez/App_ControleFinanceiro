package com.dio.controlefinanceiro.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.dio.controlefinanceiro.R
import com.dio.controlefinanceiro.extension.formataData
import com.dio.controlefinanceiro.extension.formataMoeda
import com.dio.controlefinanceiro.extension.limitaCaracteres
import com.dio.controlefinanceiro.model.TipoTransacao
import com.dio.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(transacoes: List<Transacao>, context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context
    private val limiteCatacteres = 15

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCard = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        if (transacao.tipo == TipoTransacao.DESPESA){
            viewCard.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            viewCard.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        } else {
            viewCard.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            viewCard.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }

        viewCard.transacao_valor.text = transacao.valor.formataMoeda()
        viewCard.transacao_categoria.text = transacao.categoria.limitaCaracteres(limiteCatacteres)
        viewCard.transacao_data.text = transacao.data.formataData()

        return viewCard
    }

    override fun getItem(position: Int): Any {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }


}