package com.foodie.melicious.Activity

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.foodie.melicious.Helper.UserDb
import com.foodie.melicious.Model.UserModel

import com.foodie.melicious.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch



class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var actionBar: ActionBar

    private lateinit var  progressDialog: Dialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var profilePic = ""
    private var fname = ""
    private var password = ""
    private var mobile = ""
    private var country = ""
    private var location = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        binding.signupClick.setOnClickListener{
            startActivity(Intent(this , SignupActivity::class.java))
        }

        binding.login.setOnClickListener{
            val userDB: UserDb = UserDb.getDatabase(this)

            email = binding.uName.text.toString().trim()
            password = binding.pWord.text.toString().trim()

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.uName.error ="invalid email format"
            }else if(TextUtils.isEmpty(email)){
                binding.uName.error = "please enter email"
            }else if(TextUtils.isEmpty(password)){
                binding.pWord.error = "please enter password"
            }else{
                firebaseLogin()
                val userProfile = UserModel(1,profilePic,fname,email,mobile,country,location)
                lifecycleScope.launch {
                    userDB.UserDao().insertUser(userProfile)
                }
            }
        }

    }

    private fun firebaseLogin() {

        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                val intent = Intent( this , MainActivity::class.java )
                Toast.makeText(this, "logged in as $email",Toast.LENGTH_SHORT).show()
                startActivity(intent)

            }
            .addOnFailureListener{ e ->
                progressDialog.dismiss()
                Toast.makeText(this, "login failed due to ${e.message}",Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkUser() {

        val firebaseUser = firebaseAuth.currentUser

        val intent = Intent( this , MainActivity::class.java )

        if(firebaseUser != null){
            startActivity(intent)
        }
    }
}