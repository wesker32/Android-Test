package com.example.wi.androidtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import until.Mensagem;

public class CadUsuarioActivity extends AppCompatActivity {
    Button button3;
    private EditText edtNome, edtLogin, edtSenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        button3 = (Button) findViewById(R.id.button3);

        usuarioDAO = new UsuarioDAO(this);

        edtNome = (EditText) findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText) findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText) findViewById(R.id.login_edt_Senha);

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadUsuarioActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        });

    }


    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }
    private void cadastrar(){
        boolean validacao = true;

        String nome = edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (nome == null || nome.equals("")){
            validacao = false;
            edtNome.setError(getString(R.string.campo_obrigatorio));

        }
        if (login == null || login.equals("")){
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));
    }
        if (senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));
}
        if (validacao){
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            if(idusuario > 0 ){
                usuario.set_id(idusuario);

            }
            long resultado = usuarioDAO.salvarUsuario(usuario);

            if(resultado != -1) {
                 if(idusuario > 0) {
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else{
                    Mensagem.Msg(this, getString(R.string.mesagem_cadastro));
                }
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else {
                Mensagem.Msg(this, getString(R.string.mesagem_error));


                }
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro, menu);

        if (idusuario > 0){
            menu.findItem(R.id.action_menu_excluir).setVisible(true);

        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_sair:
                startActivity(new Intent(this, entrada_pz.class));
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
