package br.com.renan.androidmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cadastro,lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cadastro = (Button) findViewById(R.id.btnCadastro) ;
        lista = (Button) findViewById(R.id.btnListar) ;

        cadastro.setOnClickListener(this);
        lista.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnCadastro:{
                startActivity(new Intent(MainActivity.this,CadastroActivity.class));
                break;
            }

            case R.id.btnListar:{
                startActivity(new Intent(MainActivity.this,ListarActivity.class));
                break;
            }
        }

    }
}
