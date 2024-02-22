package com.manish.udpconnectionchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manish.udpconnectionchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        EchoServer(4445).start()
        clickHandler()
    }

    private fun clickHandler() {
        binding.btnSend.setOnClickListener {
            EchoClient(4445).sendEcho("Hello My first Message")
        }
    }
}