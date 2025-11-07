package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private var indiceAtual = 0

    private val frasesCachorro = listOf(
        "O olfato dos cães é até cem mil vezes mais apurado que o dos humanos.",
        "Cada cachorro tem um padrão de nariz diferente, como se fosse uma impressão digital.",
        "Os cães sonham enquanto dormem e muitas vezes movem as patas ou emitem sons baixos durante o sonho.",
        "Os cachorros transpiram apenas pelas almofadinhas das patas.",
        "Eles conseguem ouvir sons até quatro vezes mais distantes do que os humanos.",
        "O abanar de rabo nem sempre significa felicidade, pois depende da direção e da velocidade do movimento.",
        "Os cães cheiram uns aos outros para obter informações químicas sobre o outro animal.",
        "Eles conseguem perceber rotinas e padrões, o que faz parecer que sabem a hora que o dono chega.",
        "Algumas raças de cachorro têm inteligência comparável à de uma criança de dois a três anos.",
        "Os cães entendem não apenas as palavras, mas também o tom e a emoção da voz humana."
    )

    private val frasesGato = listOf(
        "O ronronar dos gatos também serve para se acalmarem e até ajudar na cura de ferimentos.",
        "Os gatos dormem em média de treze a dezesseis horas por dia.",
        "Os bigodes dos gatos funcionam como sensores e ajudam a medir espaços e detectar mudanças no ar.",
        "Os gatos não sentem o gosto do doce porque não possuem receptores para esse sabor.",
        "Os olhos e ouvidos dos gatos são adaptados para detectar movimentos rápidos e sons agudos, ideais para a caça.",
        "Graças ao reflexo de endireitamento, os gatos quase sempre caem de pé quando caem de algum lugar.",
        "Os gatos miam principalmente para se comunicar com humanos, e entre si usam outros sons e gestos.",
        "Os gatos passam boa parte do dia se lambendo para se limpar e controlar a temperatura corporal.",
        "No Egito Antigo, os gatos eram considerados sagrados e matar um gato era um crime grave.",
        "Quando um gato esfrega o rosto em uma pessoa ou objeto, ele está marcando o território com seu cheiro."
    )

    private var frasesRestantes = frasesCachorro.shuffled().toMutableList()
    private var opcaoSelecionada = 1

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.statusBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        val nome = intent.getStringExtra("nome_usuario")
        binding.nomeUsuario.text = "Olá, $nome"

        val laranja = getColor(android.R.color.holo_orange_light)
        val preto = getColor(android.R.color.black)

        fun atualizaSelecionado(selecionada: Int) {
            if (selecionada == opcaoSelecionada) return
            opcaoSelecionada = selecionada
            if (selecionada == 1) {
                binding.imagemGato.setColorFilter(preto, PorterDuff.Mode.SRC_IN)
                binding.imagemCachorro.setColorFilter(laranja, PorterDuff.Mode.SRC_IN)
                frasesRestantes = frasesCachorro.shuffled().toMutableList()
            } else {
                binding.imagemGato.setColorFilter(laranja, PorterDuff.Mode.SRC_IN)
                binding.imagemCachorro.setColorFilter(preto, PorterDuff.Mode.SRC_IN)
                frasesRestantes = frasesGato.shuffled().toMutableList()
            }
            mostrarProximaFrase()
        }

        binding.imagemCachorro.setOnClickListener {
            atualizaSelecionado(1)
        }

        binding.imagemGato.setOnClickListener {
            atualizaSelecionado(2)
        }

        atualizaSelecionado(1)

        binding.imagemCachorro.setColorFilter(laranja, PorterDuff.Mode.SRC_IN)
        binding.imagemGato.setColorFilter(preto, PorterDuff.Mode.SRC_IN)

        mostrarProximaFrase()

        binding.botaoFrase.setOnClickListener {
            mostrarProximaFrase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun mostrarProximaFrase() {
        if (frasesRestantes.isEmpty()) {
            if (opcaoSelecionada == 1) {
                frasesRestantes = frasesCachorro.shuffled().toMutableList()
            } else {
                frasesRestantes = frasesGato.shuffled().toMutableList()
            }
        }

        val proxima = frasesRestantes.removeFirst()
        binding.frase.animate()
            .alpha(0f)
            .setDuration(150)
            .withEndAction {
                binding.frase.text = proxima
                binding.frase.animate().alpha(1f).setDuration(150).start()
            }.start()
    }

}