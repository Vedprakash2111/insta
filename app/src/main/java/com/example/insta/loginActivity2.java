package com.example.insta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity2 extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    EditText email,password;
    MaterialCardView loginBtn;
    String userEmail,userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        firebaseAuth=FirebaseAuth.getInstance();
        currentUser=firebaseAuth.getCurrentUser();
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.LoginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail=email.getText().toString().trim();
                userPassword=password.getText().toString().trim();

                if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)){
                    email.setError("Email error");
                    password.setError("password error");
                }
                else{
                    signIn(userEmail,userPassword);
                }

            }
        });

    }
    private void signIn( String userEmail, String userPassword){

        firebaseAuth.signInWithEmailAndPassword("","").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(loginActivity2.this,MainActivity.class);
                    startActivity(main);
                    loginActivity2.this.finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}