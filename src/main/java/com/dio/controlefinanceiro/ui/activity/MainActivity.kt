package com.dio.controlefinanceiro.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.dio.controlefinanceiro.R
import com.dio.controlefinanceiro.delegate.TransacaoDelegate
import com.dio.controlefinanceiro.ui.adapter.ListaTransacoesAdapter
import com.dio.controlefinanceiro.model.TipoTransacao
import com.dio.controlefinanceiro.model.Transacao
import com.dio.controlefinanceiro.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        popularListaTransacoes()

        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                    .configuraDialog(TipoTransacao.RECEITA, object : TransacaoDelegate{
                        override fun delegate(transacao: Transacao) {
                            atualizaTransacoes(transacao)
                            lista_transacoes_adiciona_menu.close(true)
                        }
                    })
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                    .configuraDialog(TipoTransacao.DESPESA, object : TransacaoDelegate{
                        override fun delegate(transacao: Transacao) {
                            atualizaTransacoes(transacao)
                            lista_transacoes_adiciona_menu.close(true)
                        }
                    })
        }
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        popularListaTransacoes()
        popularResumo()
    }

    private fun popularListaTransacoes() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun popularResumo(){
        ResumoView(this, window.decorView).atualizaResumo()
    }



}