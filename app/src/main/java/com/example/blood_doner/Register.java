package com.example.blood_doner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register<phoneNumber> extends AppCompatActivity {
    //variables
    EditText username,password,email,confirm_password,phone;
    Button signup;
    TextView redirectLogin;
    FirebaseAuth fAuth;
    RadioButton male,female;
    Spinner mSpinner;
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Spinner selector= (Spinner) findViewById(R.id.selector);

        ArrayAdapter<String > myAdapter =new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.options));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector.setAdapter(myAdapter);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        email = findViewById(R.id.email);
        signup = findViewById(R.id.sign_up_button);
        phone = findViewById(R.id.phone);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        redirectLogin = findViewById(R.id.redirect_login);
        male = findViewById(R.id.radio_male);
        female = findViewById(R.id.radio_female);
        //attach view with variable ^


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String ConfirmPassword = confirm_password.getText().toString().trim();
                final String UserName =  username.getText().toString();
                final String Phone = phone.getText().toString();
                final String BloodGroup = selector.getSelectedItem().toString();
                final String Gender;
                if(male.isChecked()){
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }

                //validation
                if(TextUtils.isEmpty(UserName)){
                    username.setError("User Name is required");
                }

                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is required");
                }

                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                }

                if(Password.length() < 6){
                    password.setError("Password must >= 6 character long");
                }

                if(Password != ConfirmPassword){
                    password.setError("Password didn't match");
                }
                
                if(!male.isChecked() && !female.isChecked()){
                    male.setError("You must select a Gender");
                }

                //lets register the user
                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT ).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("username", UserName);
                            user.put("email", Email);
                            user.put("phone", Phone);
                            user.put("gender", Gender);
                            user.put("blood_group", BloodGroup);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void aVoid){
                                    Log.d("Register", "onSuccess: user profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener(){
                                @Override
                                public void onFailure(@NonNull Exception e){
                                    Log.d("Register", "onFailure: " + e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        redirectLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),
                        Login.class));
            }
        });

    }


}