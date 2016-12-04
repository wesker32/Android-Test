package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
    private Database databaseHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context) {
        databaseHelper = new Database(context);

    }


    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();

        }
        return database;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(Database.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(Database.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(Database.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(Database.Usuarios.SENHA))
                );
        return model;
    }
    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDatabase().query(Database.Usuarios.TABELA,
                         Database.Usuarios.COLUNAS, null, null, null, null, null);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);

        }

        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put(Database.Usuarios.NOME, usuario.getNome());
        valores.put(Database.Usuarios.LOGIN, usuario.getLogin());
        valores.put(Database.Usuarios.SENHA, usuario.getSenha());

        if(usuario.get_id() != null){
            return getDatabase().update(Database.Usuarios.TABELA, valores,
                              "_id = ?", new String[]{usuario.get_id().toString()});

        }
        return getDatabase().insert(Database.Usuarios.TABELA, null, valores);



    }
    public boolean removerUsuario(int id){
        return getDatabase().delete(Database.Usuarios.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Usuario buscaUsuarioPorId(int id){
        Cursor cursor = getDatabase().query(Database.Usuarios.TABELA,
                               Database.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(id)},   null, null, null);

        if(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;

        }
        return null;
    }
    public boolean logar (String usuario, String senha) {
        Cursor cursor = getDatabase().query(Database.Usuarios.TABELA,
                null, "LOGIN = ? AND SENHA = ?", new String[]{usuario, senha}, null, null, null);

        if (cursor.moveToFirst()){
            return true;
        }
        return false;

    }

    public void fechar (){
        databaseHelper.close();
        database = null;
    }

}