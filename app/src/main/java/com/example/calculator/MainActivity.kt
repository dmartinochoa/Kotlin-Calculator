package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTxtName).text.toString()
            if (checkName(username)){
                val intent = Intent(this, CalcActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Introduce A Username", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkName (username : String) : Boolean {
        return username.isNotEmpty()
    }
}