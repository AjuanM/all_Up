package com.sistema.allup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
 EditText txt_email, txt_password;
 Button btn_ingresar, btn_registrar;
 private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email =(EditText)findViewById(R.id.edi_txt_email);
        txt_password =(EditText)findViewById(R.id.edit_txt_password);
        btn_ingresar = (Button)findViewById(R.id.btn_ingresar);
        btn_registrar = (Button)findViewById(R.id.btn_registrar);

       firebaseAuth = FirebaseAuth.getInstance();

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString().trim();
                String password = txt_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Profavor ingrese un Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Profavor ingrese un Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Profavor ingrese un Password de 6 carecteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(),Bienvenido.class));

                                }else {
                                    Toast.makeText(MainActivity.this,"Email u/o Password ¡INVALIDOS..!", Toast.LENGTH_SHORT).show();


                                }

                            }
                        });

            }
        });
    }
}
