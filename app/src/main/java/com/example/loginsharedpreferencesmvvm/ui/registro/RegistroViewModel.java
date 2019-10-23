package com.example.loginsharedpreferencesmvvm.ui.registro;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginsharedpreferencesmvvm.model.Usuario;
import com.example.loginsharedpreferencesmvvm.request.ApiClient;

public class RegistroViewModel extends ViewModel {
    private MutableLiveData<Usuario> mldUsuario;
    private ApiClient apiClient;

    public LiveData<Usuario> getMLDUsuario(){
        if(mldUsuario == null){
            mldUsuario = new MutableLiveData<>();
        }
        return mldUsuario;
    }

    public void guardar(Context context, Usuario usuario){
        apiClient.guardar(context, usuario);
    }

    public void leer(Context context){
       Usuario usuario1= apiClient.leer(context);
       mldUsuario.setValue(usuario1);
    }

}
