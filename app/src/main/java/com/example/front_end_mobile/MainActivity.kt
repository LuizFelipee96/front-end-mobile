package com.example.front_end_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            login(email, password)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Navegação para a tela seguinte
                    val intent = Intent(this, AddTreinoActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Autenticação falhou. Tente novamente",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
