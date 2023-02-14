package com.example.sqlite_kate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
private lateinit var uname:EditText
private lateinit var pword:EditText
private lateinit var cpword:EditText
private lateinit var signupbtt: Button
private lateinit var db: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        uname = findViewById(R.id.usernamer)
        pword = findViewById(R.id.passwordr)
        cpword = findViewById(R.id.conr)
        signupbtt = findViewById(R.id.sigr)
        db = DbHelper(this)

        sigr.setOnClickListener {
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val savedata = db.insertdata(unametext, pwordtext)

            if(TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(cpwordtext)){
                Toast.makeText(this,"Add Username Password and Conform Password",Toast.LENGTH_SHORT).show()
            }
            else {
                if(pwordtext.equals(cpwordtext)){
                    if(savedata == true){
                        Toast.makeText(this,"Sign up Successful",Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this,"User Exists",Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this,"Password not Match",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}