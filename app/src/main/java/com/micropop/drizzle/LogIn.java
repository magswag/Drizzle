package com.micropop.drizzle;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.micropop.drizzle.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {
public EditText email;
    public EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTheme(R.style.materialaa);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary700));
        }

        setContentView(R.layout.activity_log_in);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            ActionBar actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary500)));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create) {
            Intent creat = new Intent(Intent.ACTION_VIEW);
            creat.setData(Uri.parse("http://www.netatmo.com/en-US/access/signup"));
            startActivity(creat);
            return true;
        }
        if (id == R.id.action_lost) {
            Intent lost = new Intent(Intent.ACTION_VIEW);
            lost.setData(Uri.parse("http://auth.netatmo.com/en-US/access/lostpassword"));
            startActivity(lost);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void login(View view) {
        EditText email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        if (email.getText().toString().contains("@")) {


            netatmolog();
        }
        else {
            email.setError("Invalid e-mail address!");
        }
    }
    @Override
    public void onBackPressed() {

        NavUtils.navigateUpFromSameTask(this);

    }
    public void netatmolog() {

        String emil = email.getText().toString();
        String passo = password.getText().toString();
        final SampleHttpClient sampleHttpClient = new SampleHttpClient(this);

        sampleHttpClient.login(emil, passo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject= new JSONObject(response);
                            sampleHttpClient.processOAuthResponse(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }


        );

    }


}

