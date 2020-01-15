package com.router.civilization

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.StartActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseApplication.init(this)
        startActivity(Intent(this@MainActivity, StartActivity::class.java))
    }
}
