package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.content.Intent


class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private lateinit var btnStart: Button
    private lateinit var btnQuit: Button
    private lateinit var btnGuide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)

        btnStart = findViewById(R.id.btnStart)
        btnStart.setOnClickListener(){
            val intent = Intent(this, CodeblocksActivity::class.java)
            startActivity(intent)
        }

        btnQuit = findViewById(R.id.btnQuit)
        btnQuit.setOnClickListener {
            finish()
        }

        btnGuide = findViewById(R.id.btnGuide)
    }
}