package com.unacmovil.jhwparcialmovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unacmovil.jhwparcialmovil.ui.login.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.loginFragmentContainer, LoginFragment())
                .commit()
        }
    }
}