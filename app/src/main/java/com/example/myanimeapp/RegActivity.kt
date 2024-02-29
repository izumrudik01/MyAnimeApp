package com.example.myanimeapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myanimeapp.databinding.ActivityRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noAccTv.setOnClickListener{
            startActivity(Intent(this, AuthActivity::class.java))
        }


        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Пожалуйста, подождите")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.backBut.setOnClickListener {
           onBackPressed()
        }

        binding.regBut.setOnClickListener {
            validateData()
        }


    }

    private var name = ""
    private var email = ""
    private var password = ""

    private fun validateData() {
        name = binding.nameEt.text.toString().trim()
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        val cPassword = binding.cPasswordEt.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Введите свое имя", Toast.LENGTH_SHORT).show()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Введите свою почту", Toast.LENGTH_SHORT).show()
        }

        else if (password.isEmpty()) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
        }
        else if (cPassword.isEmpty()) {
            Toast.makeText(this, "Подтвердите пароль", Toast.LENGTH_SHORT).show()
        }
        else if (password != cPassword) {
            Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
        }
        else{
            createUserAccount()
        }
    }

    private fun createUserAccount() {


        progressDialog.setMessage("Аккаунт создаётся...")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Не удалось создать аккаунт из-за ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUserInfo() {


        progressDialog.setMessage("Сохранение данных пользователя...")

        val timestamp = System.currentTimeMillis()

       val uid = firebaseAuth.uid
        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["profileImage"] = " "
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Аккаунт создан!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegActivity, DashboardUserActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Не удалось сохранить данные пользователя из-за ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}








