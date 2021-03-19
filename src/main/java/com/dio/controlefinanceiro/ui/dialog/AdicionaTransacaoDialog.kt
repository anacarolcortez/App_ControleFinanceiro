package com.dio.controlefinanceiro.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.dio.controlefinanceiro.R
import com.dio.controlefinanceiro.delegate.TransacaoDelegate
import com.dio.controlefinanceiro.extension.converteParaCalendar
import com.dio.controlefinanceiro.extension.formataData
import com.dio.controlefinanceiro.model.TipoTransacao
import com.dio.controlefinanceiro.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class AdicionaTransacaoDialog(private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = criaLayout()

    fun configuraDialog(tipoTransacao: TipoTransacao, transacaoDelegate: TransacaoDelegate) {
        configuraCampoData()
        configuraCampoCategoria(tipoTransacao)
        configuraFormulario(tipoTransacao, transacaoDelegate)
    }

    private fun configuraFormulario(tipoTransacao: TipoTransacao, transacaoDelegate: TransacaoDelegate) {

        val titulo = if(tipoTransacao == TipoTransacao.RECEITA){
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }

        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton("Adicionar",
                        DialogInterface.OnClickListener { _: DialogInterface?, _: Int ->
                            val valorString = viewCriada.form_transacao_valor.text.toString()
                            val dataString = viewCriada.form_transacao_data.text.toString()
                            val categoriaString = viewCriada.form_transacao_categoria.selectedItem.toString()

                            if (valorString.isNotEmpty() && valorString.isNotBlank()) {
                                val valor = BigDecimal(valorString)
                                val data = dataString.converteParaCalendar()
                                val transacaoCriada = Transacao(valor, categoriaString, tipoTransacao, data)

                                transacaoDelegate.delegate(transacaoCriada)
                            } else {
                                Toast.makeText(context, "Ops, é preciso informar um valor", Toast.LENGTH_LONG).show()
                            }

                        })
                .setNegativeButton("Cancelar", null)
                .show()
    }

    private fun configuraCampoCategoria(tipoTransacao: TipoTransacao) {
        val categorias = if(tipoTransacao == TipoTransacao.RECEITA){
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }

        val adapter = ArrayAdapter.createFromResource(context, categorias, android.R.layout.simple_spinner_dropdown_item)
        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setText(hoje.formataData())

        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(ano, mes, dia)
                viewCriada.form_transacao_data.setText(dataSelecionada.formataData())
            }, ano, mes, dia).show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)

    }
}