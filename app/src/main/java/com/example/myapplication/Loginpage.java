package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginpage extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView username;
    TextView password;
    Button signin;
    ImageView gotoregister;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            startActivity(new Intent(getApplicationContext(),Listview.class));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        signin=findViewById(R.id.signin);
        gotoregister=findViewById(R.id.gotoRegister);
        mAuth=FirebaseAuth.getInstance();
        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin.setClickable(false);
                System.out.println(username.getText().toString());
                mAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        { Toast.makeText(getApplicationContext(),"hehe",Toast.LENGTH_SHORT);
                            startActivity(new Intent(getApplicationContext(),Listview.class));
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT);
                    }
                });

            }
        });

    }
}
