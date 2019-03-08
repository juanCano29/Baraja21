package com.example.juankno4.baraja21;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Integer numero = 0, count = 0;
    String Url = "http://nuevo.rnrsiilge-org.mx/baraja/numero", Url2 = "http://nuevo.rnrsiilge-org.mx/baraja/enviar";
    ImageView imgct;
    TextView text1, text3;
    Button btn1, btn2, btnreiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btnreiniciar = findViewById(R.id.btnreiniciar);
        btnreiniciar.setOnClickListener(this);
        imgct = findViewById(R.id.imgv);
        text1 = findViewById(R.id.text1);
        text3 = findViewById(R.id.text3);
        btn2.setEnabled(false);
        btnreiniciar.setEnabled(false);
        btnreiniciar.setBackgroundColor(Color.parseColor("#638cb5"));
        btn2.setBackgroundColor(Color.parseColor("#638cb5"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.GET,
                        Url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    numero = response.getInt("numero");
                                    count += numero;
                                    if (count <= 21) {
                                        btn2.setEnabled(false);
                                        btnreiniciar.setEnabled(false);
                                        text3.setText(response.toString());
                                    }
                                    if (count >= 21) {
                                        btn1.setEnabled(false);
                                        btn1.setText("Ya no pidas mas");
                                        btn2.setEnabled(true);
                                        btn1.setBackgroundColor(Color.parseColor("#638cb5"));
                                        btn2.setBackgroundColor(Color.parseColor("#1010ff"));
                                    }
                                    if (count > 21) {
                                        text3.setText(response.toString());
                                        btn1.setEnabled(false);
                                        Toast.makeText(MainActivity.this, "Te pasaste del 21", Toast.LENGTH_SHORT).show();
                                    }
                                    switch (numero) {
                                        case 1:
                                            imgct.setImageResource(R.drawable.as);
                                            break;
                                        case 2:
                                            imgct.setImageResource(R.drawable.carta2);
                                            break;
                                        case 3:
                                            imgct.setImageResource(R.drawable.carta3);
                                            break;
                                        case 4:
                                            imgct.setImageResource(R.drawable.carta4);
                                            break;
                                        case 5:
                                            imgct.setImageResource(R.drawable.carta5);
                                            break;
                                        case 6:
                                            imgct.setImageResource(R.drawable.carta6);
                                            break;
                                        case 7:
                                            imgct.setImageResource(R.drawable.carta7);
                                            break;
                                        case 8:
                                            imgct.setImageResource(R.drawable.carta8);
                                            break;
                                        case 9:
                                            imgct.setImageResource(R.drawable.carta9);
                                            break;
                                        case 10:
                                            imgct.setImageResource(R.drawable.carta10);
                                            break;
                                        case 11:
                                            imgct.setImageResource(R.drawable.j);
                                            break;
                                        case 12:
                                            imgct.setImageResource(R.drawable.q);
                                            break;
                                        case 13:
                                            imgct.setImageResource(R.drawable.k);
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                text1.setText(String.valueOf(count));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                VolleyS.getE(getApplicationContext()).getRequestQueue().add(jor);
                break;

            case R.id.btn2:
                btn2.setEnabled(false);
                btnreiniciar.setEnabled(true);
                btn2.setBackgroundColor(Color.parseColor("#638cb5"));
                btnreiniciar.setBackgroundColor(Color.parseColor("#008577"));
                JSONObject data = new JSONObject();
                try {
                    data.put("nombre", "Juan Cano");
                    data.put("numero", text1.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jor1 = new JsonObjectRequest(
                        Request.Method.POST,
                        Url2,
                        data,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(MainActivity.this, response.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Error al Enviar Datos", Toast.LENGTH_SHORT).show();
                            }
                        });
                VolleyS.getE(getApplicationContext()).getRequestQueue().add(jor1);
                break;

            case R.id.btnreiniciar:
                btnreiniciar.setBackgroundColor(Color.parseColor("#638cb5"));
                text1.setText("0");
                text3.setText("0");
                count = 0;
                btn1.setEnabled(true);
                imgct.setImageResource(0);
                btn1.setText("solicitar carta");
                btn1.setBackgroundColor(Color.parseColor("#D81B60"));
                break;
        }

    }
}
