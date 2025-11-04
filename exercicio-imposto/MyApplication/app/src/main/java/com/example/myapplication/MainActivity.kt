package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoCalcular.setOnClickListener(this)
    }

    fun calculaImposto(): Double {
        var salario: Float = 0F
        var gastos: Float = 0F
        var dependentes: Int = 0

        if (!binding.entradaSalario.text.isEmpty()) {
            salario = binding.entradaSalario.text.toString().toFloat()

            if (salario<0) {
                Toast.makeText(applicationContext, "Insira um salário positivo", Toast.LENGTH_SHORT).show()
                return 0.0
            }
        } else {
            Toast.makeText(applicationContext, "Insira seu salário", Toast.LENGTH_SHORT).show()
            return 0.0
        }

        if (!binding.entradaGastos.text.isEmpty()) {
            gastos = binding.entradaGastos.text.toString().toFloat()

            if (gastos<0) {
                Toast.makeText(applicationContext, "Insira um gasto positivo", Toast.LENGTH_SHORT).show()
                return 0.0
            }
        }

        if (!binding.entradaDependentes.text.isEmpty()) {
            dependentes = binding.entradaDependentes.text.toString().toInt()

            if (dependentes<0) {
                Toast.makeText(applicationContext, "Insira uma quantidade positiva de dependentes", Toast.LENGTH_SHORT).show()
                return 0.0
            }
        }

        Toast.makeText(applicationContext, "Calculando...", Toast.LENGTH_SHORT).show()


        val imposto = (salario - gastos - (dependentes*189.59))

        if (imposto<=2428.8) {
            return 0.0
        } else if (imposto<=2826.65) {
            return (imposto*0.075)-182.16
        } else if (imposto<=3751.05) {
            return (imposto*0.15)-394.16
        } else if (imposto<=4664.68) {
            return (imposto*0.225)-675.49
        } else {
            return (imposto*0.275)-908.73
        }

    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onClick(v: View?) {
        if (v?.id == R.id.botaoCalcular){
            val imposto = String.format("%.2f", calculaImposto())
            binding.valorImpostoMensal.text = "R$ $imposto"
        }
    }
}