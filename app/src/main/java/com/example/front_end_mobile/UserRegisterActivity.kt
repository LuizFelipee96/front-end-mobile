package com.example.front_end_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

data class User(
    val name: String,
    val email: String
    // não incluso a senha
)

class UserRegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        auth = FirebaseAuth.getInstance()

        val buttonRegister = findViewById<Button>(R.id.buttonRegisterUser)
        buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = findViewById<EditText>(R.id.editTextNameUser).text.toString().trim()
        val email = findViewById<EditText>(R.id.editTextEmailUser).text.toString().trim()
        val password = findViewById<EditText>(R.id.editTextPasswordUser).text.toString().trim()
        val repeatPassword = findViewById<EditText>(R.id.editTextRepeatPasswordUser).text.toString().trim()

        if (password != repeatPassword) {
            Toast.makeText(this, "As senhas não são iguais.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = User(name, email)
                    salvarNoFirebase(user)
                } else {
                    Toast.makeText(this, "Erro ao cadastrar usuário: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun salvarNoFirebase(user: User) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val userId = auth.currentUser?.uid // Usa o UID fornecido pelo Firebase Auth

        userId?.let {
            databaseReference.child(it).setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Informações adicionais salvas com sucesso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Erro ao salvar informações: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
