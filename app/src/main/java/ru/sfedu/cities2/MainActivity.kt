package ru.sfedu.cities2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private val _minPasswordLength = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewInit()
    }

    private fun viewInit() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                etEmail.setTextColor(Color.WHITE)
        }

        etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                etPassword.setTextColor(Color.WHITE)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun validateInput(): Boolean {
        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = resources.getString(R.string.empty_email)
            etEmail.setTextColor(Color.RED)

            return false
        }

        if (etPassword.text.toString().isEmpty()) {
            etPassword.error = resources.getString(R.string.empty_password)
            etPassword.setTextColor(Color.RED)

            return false
        }

        if (etPassword.text.length < _minPasswordLength) {
            etPassword.error =
                resources.getString(R.string.short_password1) + " " + _minPasswordLength + " " + getString(
                    R.string.short_password2
                )
            return false
        }

        val emails = resources.getStringArray(R.array.emails)
        val passwords = resources.getStringArray(R.array.passwords)

        var flag = false

        for (i in 0..4) {
            if ((etEmail.text.toString() == emails[i]) and (etPassword.text.toString() == passwords[i])) {
                flag = true
                break
            }
        }

        if (!flag) return false

        return true
    }

    fun goToNext(v: View) {
        if (validateInput()) {
            val intent = Intent(this, NavigationDrawer::class.java)
            startActivity(intent)
        }
    }
}
