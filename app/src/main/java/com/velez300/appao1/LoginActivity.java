package com.velez300.appao1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.velez300.appao1.databinding.ActivityLoginBinding;
import com.velez300.appao1.singletone.SessionManager;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    String clave = "1234"; //CLAVE DE LA APP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextInputEditText txtUser = binding.txtUser;
        TextInputEditText txtPass = binding.txtPass;
        Button btnIngresar = binding.btnIngresar;

        btnIngresar.setOnClickListener(v -> {
            //CONTROLA QUE SE INGRESE EL USUARIO Y QUE LA CONTRASEÑA SEA CORRECTA
            if (txtUser.getText().toString().length() > 0 && txtPass.getText().toString().trim().equals(clave)){
                SessionManager.getInstance().login();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(LoginActivity.this, "Reintentar. Inicio de Sesión Incorrecto", Toast.LENGTH_LONG).show();
            }
        });
    }
}