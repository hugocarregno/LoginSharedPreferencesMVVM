package com.example.loginsharedpreferencesmvvm.ui.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginsharedpreferencesmvvm.model.Usuario;
import com.example.loginsharedpreferencesmvvm.request.ApiClient;


public class MainViewModel extends ViewModel {
    private MutableLiveData<Usuario> mldUsuario;
    private ApiClient apiClient;

    public LiveData<Usuario> getMLDUsuario(){
        if(mldUsuario == null){
            mldUsuario = new MutableLiveData<>();
        }
        return mldUsuario;
    }

    public void login(Context context, String email, String password){
        Usuario usuario = apiClient.login(context,email,password);
        mldUsuario.setValue(usuario);
    }
}
