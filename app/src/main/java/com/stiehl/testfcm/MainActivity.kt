package com.stiehl.testfcm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.stiehl.testfcm.model.Message


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = intent?.extras?.getSerializable("message")
        if (message != null) {
            showMessage(message as Message)
        }
    }

    private fun showMessage(message: Message) {
        val bundle = Bundle()
        bundle.putSerializable("message", message)
        findNavController(R.id.navController).navigate(R.id.showMessageFragment, bundle)
    }
}