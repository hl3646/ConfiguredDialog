package com.example.configureddialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    LinearLayout LL;
    AlertDialog.Builder adb;
    final String[] colors = {"Red", "Yellow", "Blue"};
    Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        LL = findViewById(R.id.linearlayout);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        String str = item.getTitle().toString();
        if(str.equals("Credits"))
        {
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        return true;
    }

    public void primaryColors(View view)
    {
        int[] color = new int[]{0, 0, 0};
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Button 1 - choose one color");
        adb.setItems(colors, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                color[i] = 255;
                LL.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void primaryColorsMultiple(View view)
    {
        int[] color = new int[]{0, 0, 0};
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Button 2 - choose multiple colors");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b)
            {
                if(b)
                {
                    color[i] = 255;
                }
                else if (color[i] == 255)
                {
                    color[i] = 0;
                }
            }
        });
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                LL.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void getInputShowToast(View view)
    {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Button 3 - EditText");
        final EditText et = new EditText(this);
        et.setHint("Type Text Here");
        adb.setView(et);
        adb.setPositiveButton("OK", null);
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();

        Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                String message = et.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ResetColors(View view)
    {
        LL.setBackgroundColor(Color.rgb(255, 255, 255));
    }
}