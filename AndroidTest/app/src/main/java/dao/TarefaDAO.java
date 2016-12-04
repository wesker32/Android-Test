package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tarefa;

public class TarefaDAO {
    private Database databaseHelper;
    private SQLiteDatabase database;

    public TarefaDAO(Context context) {
        databaseHelper = new Database(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();

        }
        return database;

    }

    private Tarefa criarTarefa(Cursor cursor) {
        Tarefa model = new Tarefa(
                cursor.getInt(cursor.getColumnIndex(Database.Tarefas._ID)),
                cursor.getString(cursor.getColumnIndex(Database.Tarefas.TAREFA)),
                cursor.getString(cursor.getColumnIndex(Database.Tarefas.DT_CRIAÇAO)),
                cursor.getString(cursor.getColumnIndex(Database.Tarefas.DT_COMPLETADO))
        );
        return model;
    }

    public List<Tarefa> listarTarefas() {
        Cursor cursor = getDatabase().query(Database.Tarefas.TABELA,
                Database.Tarefas.COLUNAS, null, null, null, null, null);
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (cursor.moveToNext()) {
            Tarefa model = criarTarefa(cursor);
            tarefas.add(model);

        }

        cursor.close();
        return tarefas;
    }

    public long salvarTarefa(Tarefa model) {

        ContentValues valores = new ContentValues();
        valores.put(Database.Tarefas.TAREFA, model.getTarefa());


        if (model.get_id() != null) {
            return getDatabase().update(Database.Tarefas.TABELA, valores,
                    "_id = ?", new String[]{model.get_id().toString()});

        }
        return getDatabase().insert(Database.Tarefas.TABELA, null, valores);

    }
    public boolean removerTarefa(int id){
        return getDatabase().delete(Database.Tarefas.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
}

    public Tarefa buscaTarefaPorId(int id){
        Cursor cursor = getDatabase().query(Database.Tarefas.TABELA,
                Database.Tarefas.COLUNAS, "_id = ?", new String[]{Integer.toString(id)},   null, null, null);

        if(cursor.moveToNext()){
            Tarefa model = criarTarefa(cursor);
            cursor.close();
            return model;

        }
        return null;
    }

    public void fechar (){
        databaseHelper.close();
        database = null;
    }
}