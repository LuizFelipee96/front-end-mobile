package com.example.front_end_mobile

import android.util.Log
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        auth = FirebaseAuth.getInstance()

//        val nomeEditText = findViewById<EditText>(R.id.edtNome)
////        val emailEditText = findViewById<EditText>(R.id.edtEmail)
//        val dataNascimentoEditText = findViewById<EditText>(R.id.edtDataNascimento)
////        val telefoneEditText = findViewById<EditText>(R.id.edtTelefone)
////        val objetivoEditText = findViewById<EditText>(R.id.edtObjetivo)
//        val generoRadioGroup = findViewById<RadioGroup>(R.id.radioGroupGenero)
//        val enviarButton = findViewById<Button>(R.id.btnEnviar)
//
//        enviarButton.setOnClickListener {
////            val email = emailEditText.text.toString().trim()
////            val dataNascimento = dataNascimentoEditText.text.toString().trim()
////            val telefone = telefoneEditText.text.toString().trim()
////            val objetivo = objetivoEditText.text.toString().trim()
//            val generoId = generoRadioGroup.checkedRadioButtonId
//            val genero = when (generoId) {
//                R.id.radioFeminino -> "Feminino"
//                R.id.radioMasculino -> "Masculino"
//                else -> "" // ou algum valor padrão ou tratamento de erro
//            }
//            Log.i("hi lorena", "message")
//        }
    }

    private fun criarConta(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cadastro bem-sucedido!", Toast.LENGTH_SHORT).show()
                    // Redirecionar para tela de login ou principal
                } else {
                    Toast.makeText(this, "Falha no Cadastro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validarDados(email: String, password: String): Boolean {
        // Depois implementar para verificar se o email é válido, se a senha tem no mínimo 6 caracteres, etc.
        return email.isNotEmpty() && password.isNotEmpty()
    }
}
