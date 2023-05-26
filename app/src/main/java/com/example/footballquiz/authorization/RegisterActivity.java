package com.example.footballquiz.authorization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyHaveAnAccount;
    EditText inputEmail,inputPassword,inputConfirmPassword;
    EditText inputUsername;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAccount);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        inputUsername = findViewById(R.id.inputUsername);
        btnRegister = findViewById(R.id.registerButton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void saveToFireStore() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();
        String username = inputUsername.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter correct email");
        }else if(username.isEmpty()){
            inputUsername.setError("Enter valid username");
        }else if(password.isEmpty() || password.length() < 6){
            inputPassword.setError("Enter proper password");
        }else if(!password.equals(confirmPassword)){
            inputConfirmPassword.setError("Passwords does not match");
        }else {

            Map<String, Object> user = new HashMap<>();
            user.put("Email", email);
            user.put("Password", password);
            user.put("Username", username);

            user.put("Who is faster rating",1000);
            user.put("Who is more expensive rating",1000);
            user.put("Guess the price rating", 1000);
            user.put("Guess the player rating", 1000);
            user.put("Who has scored more rating", 1000);
            user.put("Who has assisted more rating", 1000);

            user.put("Who is faster right answers",0);
            user.put("Who is more expensive right answers",0);
            user.put("Guess the price right answers",0);
            user.put("Guess the player right answers",0);
            user.put("Who has scored more right answers",0);
            user.put("Who has assisted more right answers",0);

            user.put("Who is faster wrong answers",0);
            user.put("Who is more expensive wrong answers",0);
            user.put("Guess the price wrong answers",0);
            user.put("Guess the player wrong answers",0);
            user.put("Who has scored more wrong answers",0);
            user.put("Who has assisted more wrong answers",0);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String userId = mAuth.getCurrentUser().getUid();

            db.collection("users")
                    .document(userId)
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RegisterActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Saving failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();
        String username = inputUsername.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter correct email");
        }else if(username.isEmpty()){
            inputUsername.setError("Enter valid username");
        }else if(password.isEmpty() || password.length() < 6){
            inputPassword.setError("Enter proper password");
        }else if(!password.equals(confirmPassword)){
            inputConfirmPassword.setError("Passwords do not match");
        }else {
            progressDialog.setMessage("Please wait while you are being registered...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid(); // Get the authentication ID
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("Email", email);
                        userData.put("Password", password);
                        userData.put("Username", username);
                        userData.put("Who is faster rating",1000);
                        userData.put("Who is more expensive rating",1000);
                        userData.put("Guess the price rating", 1000);
                        userData.put("Guess the player rating", 1000);
                        userData.put("Who has scored more rating", 1000);
                        userData.put("Who has assisted more rating", 1000);

                        userData.put("Who is faster right answers",0);
                        userData.put("Who is more expensive right answers",0);
                        userData.put("Guess the price right answers",0);
                        userData.put("Guess the player right answers",0);
                        userData.put("Who has scored more right answers",0);
                        userData.put("Who has assisted more right answers",0);

                        userData.put("Who is faster wrong answers",0);
                        userData.put("Who is more expensive wrong answers",0);
                        userData.put("Guess the price wrong answers",0);
                        userData.put("Guess the player wrong answers",0);
                        userData.put("Who has scored more wrong answers",0);
                        userData.put("Who has assisted more wrong answers",0);

                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        db.collection("users")
                                .document(userId) // Set the document ID to the authentication ID
                                .set(userData)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Saving failed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Please verify your email. It might take a few minutes", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        progressDialog.dismiss();
                        sendUserToLoginActivity();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void sendUserToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}