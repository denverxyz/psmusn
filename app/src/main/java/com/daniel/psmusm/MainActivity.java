package com.daniel.psmusm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText uname, password ;
    Button loginbtn,usrbtn;
    FirebaseAuth fAuth;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        usrbtn = findViewById(R.id.userbtn);
        progressbar = findViewById(R.id.loginProgressBar);
        fAuth =FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Luname = uname.getText().toString().trim();
                String Lpassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(Luname)){
                    uname.setError("Username is Required");
                    return;
                }

                if (TextUtils.isEmpty(Lpassword)){
                    password.setError("Password is Required");
                    return;

                }

                if (Lpassword.length()<6){
                    password.setError("Password must be more than 6 length");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(Luname,Lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User logged in.",Toast.LENGTH_SHORT).show();startActivity(new Intent(getApplicationContext(),MainInterface.class));

                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error Occured :"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}