package com.example.volleyexample.volleygeneric.VolleyConnection;

/**
 * Created by Paulo on 06/05/2016.
 */
import android.app.Activity;
import android.util.Log;
import android.view.View;


public class ReturnResponse {
    public static String login;
    public static String password;

    private static ReturnResponse ourInstance = new ReturnResponse();

    public static ReturnResponse getInstance() {
        return ourInstance;
    }

    private ReturnResponse() {
    }

    public void goTo(String whoCalled, String response, Activity activity){
        switch (whoCalled){
            //esse método é de retorno caso tenha dado certo a requisição
           //redireciona de volta para quem chamou
            //whocalled é importante para identificar quem  fez a requisição

        }

    }


}

