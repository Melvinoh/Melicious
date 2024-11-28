package com.foodie.melicious.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.foodie.melicious.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : BaseActivity(){
    private lateinit var  binding: ActivitySignupBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog :ProgressDialog
    private lateinit var  firebaseAuth: FirebaseAuth
    private var username = ""
    private var email =""
    private var password = ""
    private var cpassword =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)



        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("creating account in...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUp.setOnClickListener{
            validateData()

        }

    }

    private fun validateData() {
        username = binding.uName.text.toString().trim()
        email = binding.emailT.text.toString().trim()
        password = binding.pWord.text.toString().trim()
        cpassword = binding.cpWord.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailT.error = "invalid email formart"
        }else if (TextUtils.isEmpty(username)){
            binding.username.error = "please enter username"
        }else if (TextUtils.isEmpty(email)){
            binding.email.error = "please enter email"
        }else if (TextUtils.isEmpty(password)){
            binding.pWord.error = "please enter password"
        }else if (TextUtils.isEmpty(cpassword)){
            binding.cpWord.error = "please confirm your password"
        }else if (password != cpassword){
            binding.cpWord.error = "password does not match"
        }else if (password.length < 6){
            binding.pWord.error = "password must be ateast 6 characters long"
        }else{
            firebaseSignup()
        }
    }

    private fun firebaseSignup() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email ,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val user = firebaseAuth.currentUser
                val email = user!!.email

                Toast.makeText(this ,"account succefully created with email ${email}",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this ,MainActivity::class.java))

            }
            .addOnFailureListener{ e ->
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "SignUp failed ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}