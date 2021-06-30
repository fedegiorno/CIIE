package com.fedegiorno.ciie_region_6.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.fedegiorno.ciie_region_6.R

class SplashActivity : AppCompatActivity() {

    private val SPLASHTIMEOUT:Long = 2000 // 2 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(

            {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            , SPLASHTIMEOUT)
    }
}