package br.com.renan.biblioteca;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.renan.biblioteca.dao.LivroDao;
import br.com.renan.biblioteca.modelo.Livro;

public class MainActivity extends AppCompatActivity {

    ListView listVisivel;
    Button btnNovoCadastro;
    Livro livro;
    LivroDao livroDao;
    ArrayList<Livro> arrayListLivro;
    ArrayAdapter<Livro> arrayAdapterLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listVisivel = (ListView) findViewById(R.id.listLivros);
        registerForContextMenu(listVisivel);

        btnNovoCadastro = (Button) findViewById(R.id.btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormLivro.class);
                startActivity(intent);
            }
        });

        listVisivel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Livro livroEnviado = (Livro) arrayAdapterLivro.getItem(position);

                Intent intent = new Intent(MainActivity.this,FormLivro.class);
                intent.putExtra("livro-enviado",livroEnviado);
                startActivity(intent);

            }
        });

        listVisivel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                livro = arrayAdapterLivro.getItem(position);
                return false;
            }
        });
    }

    public void populaLista(){
        livroDao = new LivroDao(MainActivity.this);

        arrayListLivro = livroDao.selectAllLivros();
        livroDao.close();

        if(listVisivel != null){
            arrayAdapterLivro = new ArrayAdapter<Livro>(MainActivity.this,android.R.layout.simple_list_item_1,arrayListLivro);
            listVisivel.setAdapter(arrayAdapterLivro);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add("Delete Registro");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoDB;
                livroDao = new LivroDao(MainActivity.this);
                retornoDB = livroDao.excluirLivros(livro);
                livroDao.close();

                if(retornoDB == -1){
                    alert("Erro na exclusão");
                } else {
                    alert("Resgistro excluido com sucesso!");
                }
                populaLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
