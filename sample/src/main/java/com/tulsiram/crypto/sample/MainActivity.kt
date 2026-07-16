/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.crypto.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyProperties
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tulsiram.crypto.Algorithm
import com.tulsiram.crypto.AlgorithmSpec
import com.tulsiram.crypto.Crypto

class MainActivity : AppCompatActivity() {

    private lateinit var crypto: Crypto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCrypto()

        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val btnEncrypt = findViewById<Button>(R.id.btnEncrypt)
        val btnDecrypt = findViewById<Button>(R.id.btnDecrypt)
        val tvDecrypted = findViewById<TextView>(R.id.tvDecrypted)

        btnEncrypt.setOnClickListener {
            editText2.setText(crypto.encrypt(editText.text.toString()))
        }

        btnDecrypt.setOnClickListener {
            tvDecrypted.text = crypto.decrypt(editText2.text.toString())
        }
    }

    /**
     * initialize crypto
     */
    private fun initCrypto() {
        val algorithm = Algorithm(
            KeyProperties.KEY_ALGORITHM_AES,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
            KeyProperties.BLOCK_MODE_GCM,
            KeyProperties.ENCRYPTION_PADDING_NONE
        )
        val algorithmSpec = AlgorithmSpec(algorithm, "Alias")

        crypto = Crypto.getInstance(algorithmSpec)
    }
}
