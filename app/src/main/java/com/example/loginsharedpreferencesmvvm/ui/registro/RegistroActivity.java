package com.example.loginsharedpreferencesmvvm.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsharedpreferencesmvvm.R;
import com.example.loginsharedpreferencesmvvm.model.Usuario;


public class RegistroActivity extends AppCompatActivity {

    private EditText dni;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText password;
    private RegistroViewModel registroViewModel;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        extras = getIntent().getExtras();
        configurar();
        registroViewModel.getMLDUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni()+"");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getMail());
                password.setText(usuario.getPassword());
            }
        });
            if(extras != null){
                leer();
            }

    }

    public void configurar(){
        dni = findViewById(R.id.dni);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registroViewModel = ViewModelProviders.of(this).get(RegistroViewModel.class);

    }

    public void leer(){
        registroViewModel.leer(getApplicationContext());
    }

    public void guardar(View v){
        Usuario usuario = new Usuario();
        if(!dni.getText().toString().isEmpty()&&!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty()&& !password.getText().toString().isEmpty()){
            usuario.setDni(Long.parseLong(dni.getText().toString()));
            usuario.setNombre(nombre.getText().toString());
            usuario.setApellido(apellido.getText().toString());
            usuario.setMail(email.getText().toString());
            usuario.setPassword(password.getText().toString());
            registroViewModel.guardar(getApplicationContext(), usuario);
        }else{
            Toast.makeText(getApplicationContext(),"Campos incompletos",Toast.LENGTH_LONG).show();
        }
    }
}
