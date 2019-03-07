package com.example.juankno4.baraja21;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {


    private static VolleyS e = null;


    private RequestQueue requestQueue;

    private VolleyS(Context context){

        requestQueue = Volley.newRequestQueue(context);

    }
    public static VolleyS getE(Context context) {
        if (e == null)
        {
            e = new VolleyS(context);
        }
        return e;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }


}
