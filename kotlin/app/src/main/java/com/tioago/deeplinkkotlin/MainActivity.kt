package com.tioago.deeplinkkotlin

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenApp.setOnClickListener {

            var intent = packageManager.getLaunchIntentForPackage("{{package-app-na-loja}}")

            if (intent == null) { // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=" + "{{package-app-na-loja}}")
            } else {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("{{url-deep-link-configurado-no-app}}/login?u=XXXX&p=XXXX&o=xxxx")
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (isOnline()) {
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Sem conexão com a Internet", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
