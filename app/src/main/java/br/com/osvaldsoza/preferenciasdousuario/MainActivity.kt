package br.com.osvaldsoza.preferenciasdousuario

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.osvaldsoza.preferenciasdousuario.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var cor = ""

    companion object {
        const val ARQUIVO_PREFERENCIAS = "ArquivoPreferencias"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.cor1.setOnClickListener {
            cor = "#83EFE5"
            salvar(cor)
        }

        binding.cor2.setOnClickListener {
            cor = "#DC93E8"
            salvar(cor)
        }

        binding.cor3.setOnClickListener {
            cor = "#EF805D"
            salvar(cor)
        }

        binding.cor4.setOnClickListener {
            cor = "#51A3E4"
            salvar(cor)
        }
    }

    private fun salvar(cor: String) {
        binding.layoutPrincipal.setBackgroundColor(Color.parseColor(cor))
        binding.trocarCorFundo.setTextColor(Color.parseColor(cor))

        binding.trocarCorFundo.setOnClickListener { view ->
            val preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("cor", cor)
            editor.apply()

            snackbar(view)
        }
    }

    private fun snackbar(view: View) {
        val snackbar = Snackbar.make(view, "Cor de fundo salva com sucesso!", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK") {}
        snackbar.setTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
        val cor = preferencias.getString("cor", "")

        if (cor!!.isNotEmpty()) {
            binding.layoutPrincipal.setBackgroundColor(Color.parseColor(cor))
            binding.trocarCorFundo.setTextColor(Color.parseColor(cor))
        }
    }
}