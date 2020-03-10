package com.tioago.deeplinktestjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOpenApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenApp = (Button) findViewById(R.id.btnOpenApp);

        btnOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("br.com.cspar.vmcard");

                if (intent == null) {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + "br.com.cspar.vmcard"));
                } else {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://vmcard.unimedceara.com.br/login?u=Xxxxxx&p=xxxxxx&o=xxxx&t=xxxx"));
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (isOnline()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Sem conexão com a Internet", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
