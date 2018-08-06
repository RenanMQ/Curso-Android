package br.com.renan.biblioteca.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.renan.biblioteca.modelo.Livro;

public class LivroDao extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "DBLivro.db";
    private static final int VERSION = 1;
    private static final String TABELA = "livro";

    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String NOME_AUTOR = "autor";
    private static final String EDITORA = "editora";
    private static final String ANO = "ano";
    private static final String ISBN = "isbn";

    public LivroDao(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITULO +
                " TEXT, " + NOME_AUTOR + " TEXT, " + EDITORA + " TEXT, "
                + ANO + " INTEGER, " + ISBN + " INTEGER" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABELA + "'");
        onCreate(db);
    }

    public long salvarLivros(Livro livro){
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME_AUTOR,livro.getNomeAutor());
        values.put(TITULO,livro.getTituloLivro());
        values.put(ANO,livro.getAno());
        values.put(EDITORA,livro.getEditora());
        values.put(ISBN,livro.getIsbn());

        retornoDB = getWritableDatabase().insert(TABELA,null,values);

        return  retornoDB;

    }

    public long alterarLivros(Livro livro){
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME_AUTOR,livro.getNomeAutor());
        values.put(TITULO,livro.getTituloLivro());
        values.put(ANO,livro.getAno());
        values.put(EDITORA,livro.getEditora());
        values.put(ISBN,livro.getIsbn());

        String[] args = {String.valueOf(livro.getId())};
        retornoDB = getWritableDatabase().update(TABELA,values,"id=?",args);

        return  retornoDB;

    }

    public long excluirLivros(Livro livro){
        long retornoDB;

        String[] args = {String.valueOf(livro.getId())};
        retornoDB = getWritableDatabase().delete(TABELA,ID+"=?",args);

        return  retornoDB;

    }

    public ArrayList<Livro> selectAllLivros(){
        String[] colums = {ID,NOME_AUTOR,TITULO,EDITORA,ANO,ISBN};

        Cursor cursor = getWritableDatabase().query(TABELA,colums,null,null,null,null,"titulo DESC",null);

        ArrayList<Livro> listLivro = new ArrayList<>();

        while (cursor.moveToNext()){
            Livro livro = new Livro();

            livro.setId(cursor.getInt(0));
            livro.setNomeAutor(cursor.getString(1));
            livro.setTituloLivro(cursor.getString(2));
            livro.setAno(cursor.getInt(3));
            livro.setEditora(cursor.getString(4));
            livro.setIsbn(cursor.getInt(5));

            listLivro.add(livro);
        }

        return listLivro;
    }
}
