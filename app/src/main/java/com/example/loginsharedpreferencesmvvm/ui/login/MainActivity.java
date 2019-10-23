package com.example.loginsharedpreferencesmvvm.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsharedpreferencesmvvm.R;
import com.example.loginsharedpreferencesmvvm.model.Usuario;
import com.example.loginsharedpreferencesmvvm.request.ApiClient;
import com.example.loginsharedpreferencesmvvm.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
private EditText etEmail,etPass;
private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarVista();


    }

    public void configurarVista(){
        etEmail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPassword);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMLDUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario != null){
                    Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                    intent.putExtra("login","login");
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"EL usuario no existe",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void login(View v){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();
        if(email.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Completar", Toast.LENGTH_LONG).show();
        }else{
            mainViewModel.login(this,email,password);
        }

    }

    public void registrarse(View v){
        Intent intent = new Intent(this,RegistroActivity.class);
        startActivity(intent);
    }
}
