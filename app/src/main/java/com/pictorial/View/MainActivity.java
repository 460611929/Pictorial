package com.pictorial.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pictorial.R;
import com.pictorial.app.Constant;
import com.pictorial.net.Exceptions;
import com.pictorial.net.IRequest;
import com.pictorial.net.RequestListener;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) this.findViewById(R.id.result);
        getNewsList();
    }

  private void getNewsList(){
      final ProgressDialog pDialog = new ProgressDialog(this);
      pDialog.setMessage("Loading...");
      pDialog.show();
      IRequest.get(this, Constant.base, new RequestListener() {
          @Override
          public void requestSuccess(String json) {
              tvResult.setText(json);
              pDialog.dismiss();
          }

          @Override
          public void requestError(VolleyError e) {
              Exceptions.errorHandle(MainActivity.this,e);
              pDialog.dismiss();
          }
      });
  }
}
