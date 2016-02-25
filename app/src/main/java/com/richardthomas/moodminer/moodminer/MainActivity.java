package com.richardthomas.moodminer.moodminer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE ="com.richardthomas.moodminer.MESSAGE";
    Button loginButton;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton=(Button)findViewById(R.id.button);

        username= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("root")&&
                        password.getText().toString().equals("root")){
                    //if_body
                    //if the user is valid
                    Toast.makeText(MainActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(MainActivity.this,dashboard.class);
                    i.putExtra(EXTRA_MESSAGE,"root");
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Login unsuccessful.Try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
