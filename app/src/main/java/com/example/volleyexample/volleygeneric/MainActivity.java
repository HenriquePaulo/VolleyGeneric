package com.example.volleyexample.volleygeneric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.volleyexample.volleygeneric.VolleyConnection.Connection;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exemploConnect();
        exemploConnectPost();
    }

    private void exemploConnectPost() {
        Connection task = new Connection("url", Request.Method.POST, "quemEstaChamando", this, moutData());
        task.callByJsonStringRequest();
    }

    private HashMap<String, String> moutData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("chave", "valor");
        return params;

    }

    private void exemploConnect() {
        Connection task = new Connection("url", Request.Method.GET, "quemEstaChamando", this);
        task.callByJsonStringRequest();
    }
}
