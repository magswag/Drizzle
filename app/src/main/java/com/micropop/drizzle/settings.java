package com.micropop.drizzle;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.micropop.drizzle.R;

public class settings extends AppCompatActivity {
    String [] settingarra;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTheme(R.style.materialaa);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary700));
        }
        setContentView(R.layout.activity_settings);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary500)));
        }



        ListView listView = (ListView) findViewById(R.id.listV);
        settingarra = getResources().getStringArray(R.array.settingarray);
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                settingarra);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent LogiIntent = new Intent(settings.this, LogIn.class);
                    startActivity(LogiIntent);
                }
                if (position > 0) {
                    Toast.makeText(getApplicationContext(), "Currently not in use", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

        NavUtils.navigateUpFromSameTask(this);

    }

}
