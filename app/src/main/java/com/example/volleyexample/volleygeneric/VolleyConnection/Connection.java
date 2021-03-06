package com.example.volleyexample.volleygeneric.VolleyConnection;

/**
 * Created by Paulo on 06/05/2016.
 */
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import pantracktec.panorama.com.pantractec.TecnicosActivity;
import pantracktec.panorama.com.pantractec.Utils.Alerts;
import pantracktec.panorama.com.pantractec.Utils.Constants;
import pantracktec.panorama.com.pantractec.Utils.Preferences;


public class Connection extends AppCompatActivity {

    private String login;
    private String password;
    private Activity activity;
    private RequestQueue rq; // precisa add o volley como modulo, e marca-lo como dependencia, para isso precisa baixa-lo
    private HashMap<String, String> params;
    public String url;
    public View view;
    private int METHOD;
    private String whoCalled;

    public Connection(String url, int METHOD, String whoCalled, Activity activity){
        this.url = url;
        this.METHOD = METHOD;
        this.whoCalled = whoCalled;
        this.activity = activity;

        rq = Volley.newRequestQueue(activity);
    }
    public Connection(String url, int METHOD, String whoCalled, Activity activity, HashMap<String, String> params){
        this.url = url;
        this.METHOD = METHOD;
        this.whoCalled = whoCalled;
        this.activity = activity;
        this.params = params;

        rq = Volley.newRequestQueue(activity);
    }

    public void callByJsonStringRequest(){
        StringRequest request = new StringRequest(METHOD, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ReturnResponse.getInstance().goTo(whoCalled, response, getActivity());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error.networkResponse.statusCode == 400)
                                Alerts.menssage("Erro!", "código 400\n requisição incorreta", getActivity());
                            else if (error.networkResponse.statusCode == 401)
                                Alerts.menssage("Erro!", "código 401\n requisição não autorizada", getActivity());
                            else
                                ReturnError.getInstance().goTo(whoCalled, getActivity(), getMessage(error));
                            //se voce estiver debugando altere essa variável
                            if (Constants.debug) {
                            String statusCode = String.valueOf(error.networkResponse.statusCode);
                                String mensagem = new String(error.networkResponse.data);
                                String e = new String(String.valueOf(error));
                                if (error.getMessage() == null)
                                    Alerts.menssage("Erro", e, getActivity());
                                else
                                    Alerts.menssage(statusCode + " - Erro!", error.getMessage(), getActivity());
                                Alerts.menssage("Erro Mensagem", mensagem, getActivity());
                            }
                        }catch (Exception e){
                            Alerts.menssage("API indisponível", "tente novamente mais tarde", activity);
                        }
                    }
                    public String getMessage(VolleyError error){
                        try{
                            String mensagem = new String(error.networkResponse.data);
                            return mensagem;
                        }catch (Exception e){
                            return "API indisponível, tente novamente mais tarde";
                        }
                    }

                }){


            //parametros mandados no body, normalmente quando usa POST
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }


            //authorizações necessárias
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String base64EncodedCredentials = "Basic " + Base64.encodeToString(((
//                                getLogin() != null ? getLogin() : Preferences.getPreferece("login", getActivity()))
//                                + ":" +
//                                (getPassword() != null ? getPassword() : Preferences.getPreferece("password", getActivity()))).getBytes(),
//                        Base64.NO_WRAP);
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=utf-8");
                //header.put("Content-Type","application/x-www-form-urlencoded");
                header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//                header.put("authorization", base64EncodedCredentials);

                return header;
            }

        };

        //tempo para enviar outra requisição
        //se deixar normal, ele irá enviar várias requisições caso falhe a primeira
        //o problema é ao debugar ou em demora ele tenta fazer a mesma coisa mais de uma vez
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        999999999,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag("tag");
        rq.add(request);

    }

    //impedir multiplas requisições desnecessarias
    public void onStop(){
        super.onStop();
        rq.cancelAll("tag");
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMETHOD() {
        return METHOD;
    }

    public void setMETHOD(int METHOD) {
        this.METHOD = METHOD;
    }

    public String getWhoCalled() {
        return whoCalled;
    }

    public void setWhoCalled(String whoCalled) {
        this.whoCalled = whoCalled;
    }
}
