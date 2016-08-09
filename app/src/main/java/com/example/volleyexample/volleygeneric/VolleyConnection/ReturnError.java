package com.example.volleyexample.volleygeneric.VolleyConnection;

/**
 * Created by Paulo on 06/05/2016.
 */
import android.app.Activity;
import android.util.Log;
import android.view.View;



public class ReturnError {
    public String message;

    private static ReturnError ourInstance = new ReturnError();

    public static ReturnError getInstance() {
        return ourInstance;
    }

    private ReturnError() {
    }

    public void goTo(String whoCalled, Activity activity, String message) {
        switch (whoCalled) {
            //esse método é de retorno caso tenha dado certo a requisição
            //redireciona de volta para quem chamou
            //whocalled é importante para identificar quem  fez a requisição
        }


    }
}

