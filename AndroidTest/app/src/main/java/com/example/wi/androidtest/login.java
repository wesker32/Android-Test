package com.example.wi.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import until.Mensagem;

public class login extends Activity {

    private EditText edtUsuario, edtSenha;
    private UsuarioDAO helper;
    private CheckBox ckbConectado;

    private static final String MANTER_CONECTADO = "manter conectado";
    private static final String PREFENCE_NAME = "LoginAcitivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario   = (EditText)findViewById(R.id.login_edt_Usuario);
        edtSenha     = (EditText)findViewById(R.id.login_edt_Senha);
        ckbConectado = (CheckBox)findViewById(R.id.login_ckbConectado);

        helper = new UsuarioDAO(this);

        SharedPreferences preferences = getSharedPreferences(PREFENCE_NAME, MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);

        if(conectado){
            ChamarMainActivity();
        }


    }
    public void logar(View view) {
        String usuario = edtUsuario.getText().toString();
        String senha = edtSenha.getText().toString();

        boolean validacao = true;

        if(usuario == null || usuario.equals("")){
            validacao = false;
            edtUsuario.setError(getString(R.string.login_valUsuario));
        }

        if(senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.login_valSenha));

    }
        if(validacao){
          //Logar
          if(helper.logar(usuario,senha)){
              if(ckbConectado.isChecked()){
                  SharedPreferences sharedPreferences = getSharedPreferences(PREFENCE_NAME, MODE_PRIVATE);
                  SharedPreferences.Editor editor  =  sharedPreferences.edit();

                  editor.putBoolean(MANTER_CONECTADO, true);
                  editor.commit();
              }
              ChamarMainActivity();

          }
            else {
              Mensagem.Msg(this, getString(R.string.msg_login_incorreto));
          }
        }
    }
    private  void  ChamarMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }





}

