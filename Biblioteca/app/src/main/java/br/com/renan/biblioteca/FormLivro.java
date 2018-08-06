package br.com.renan.biblioteca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.renan.biblioteca.dao.LivroDao;
import br.com.renan.biblioteca.modelo.Livro;

public class FormLivro extends AppCompatActivity {

    EditText autor, titulo, ano, editora, isbn;
    Button btnVariavel;
    Livro livro, altLivro;
    LivroDao livroDao;
    long retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livro);

        Intent intent = getIntent();
        altLivro = (Livro) intent.getSerializableExtra("livro-enviado");
        livro = new Livro();
        livroDao = new LivroDao(FormLivro.this);

        autor = (EditText) findViewById(R.id.editAutor);
        titulo = (EditText) findViewById(R.id.editLivro);
        ano = (EditText) findViewById(R.id.editAno);
        editora = (EditText) findViewById(R.id.editEditora);
        isbn = (EditText) findViewById(R.id.editIsbn);
        btnVariavel = (Button) findViewById(R.id.btnVariavel);

        if(altLivro != null){
            btnVariavel.setText("Alterar");
            autor.setText(altLivro.getNomeAutor());
            titulo.setText(altLivro.getTituloLivro());
            ano.setText(altLivro.getAno()+"");
            editora.setText(altLivro.getEditora());
            isbn.setText(altLivro.getIsbn()+"");

            livro.setId(altLivro.getId());
        }else {
            btnVariavel.setText("Salvar");
        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                livro.setTituloLivro(titulo.getText().toString());
                livro.setNomeAutor(autor.getText().toString());
                livro.setEditora(editora.getText().toString());
                livro.setAno(Integer.parseInt(ano.getText().toString()));
                livro.setIsbn(Integer.parseInt(isbn.getText().toString()));

                if(btnVariavel.getText().toString().equals("Salvar")){
                    retorno = livroDao.salvarLivros(livro);
                    livroDao.close();
                    if(retorno == -1){
                        alert("Erro ao cadastrar");
                    } else {
                        alert("Cadastro realizado com sucesso");
                    }
                }else {
                    retorno = livroDao.alterarLivros(livro);
                    livroDao.close();
                    if(retorno==-1){
                       alert("Erro ao alterar");
                    } else {
                        alert("Atualização realizada com sucesso");
                    }
                }
                finish();
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}

