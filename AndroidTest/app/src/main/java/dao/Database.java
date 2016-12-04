package dao;
import android.database.*;
import android.database.sqlite.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wi on 23/11/2016.
 */

public class Database extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "tasks";
    private static final int VERSAO = 1;
    public Database(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuários
        db.execSQL("create table usuarios(_id integer primary key autoincrement,"
                     +"nome text not null,  login text not null, senha text not null)");
        //Tabela de tarefas
        db.execSQL("create table tarefas(_id integer primary key autoincrement,"
                     +"tarefas text not null, dt_criaçao datetime default current_timestamp, dt_completado datetime )");
        //Casdastrar usuario
        db.execSQL("insert into usuarios (nome, login, senha) values('Admin','admin','123')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";

        public static final String [] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA

        };
    }

    public static class Tarefas{
        public static final String TABELA = "tarefas";
        public static final String _ID = "_id";
        public static final String TAREFA = "tarefa";
        public static final String DT_CRIAÇAO = "dt_criacao";
        public static final String DT_COMPLETADO = "dt_completado";


        public static final String [] COLUNAS = new String[]{
                _ID, TAREFA, DT_CRIAÇAO, DT_COMPLETADO
        };
    }

    }



