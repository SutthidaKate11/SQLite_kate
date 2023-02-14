package com.example.sqlite_kate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {
    private lateinit var loginbtt: Button
    private lateinit var edituser: EditText
    private lateinit var editpword: EditText
    private lateinit var dbh: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbtt = findViewById(R.id.logl)
        edituser = findViewById(R.id.usernamerl)
        editpword = findViewById(R.id.passwordl)
        dbh = DbHelper(this)

        logl.setOnClickListener{
            val useredtx = edituser.text.toString()
            val passdtx = editpword.text.toString()
            if(TextUtils.isEmpty(useredtx) || TextUtils.isEmpty(passdtx)){
                Toast.makeText(this,"Add Username & Password", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkuser = dbh.checkuserpass(useredtx, passdtx)
                if(checkuser==true){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, Finish::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this,"Wrong Username & Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}