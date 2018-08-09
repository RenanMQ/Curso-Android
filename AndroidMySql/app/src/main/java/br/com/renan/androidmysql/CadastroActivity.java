package br.com.renan.androidmysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

  Button btnSalvar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
        Log.i("onCreate","entrei no Oncreate");


    }

    public void doRequest(){

        Log.i("doRequest","Entrei Request");
        EditText txtNome = (EditText)findViewById(R.id.editNome);
        EditText txtEmail = (EditText)findViewById(R.id.editEmail);

        try{

            StringBuilder strURL = new StringBuilder();
            strURL.append("http://localhost/inserir.php?");
            strURL.append("nome=");
            strURL.append(URLEncoder.encode(txtNome.getText().toString(),"UTF-8"));
            strURL.append("&email=");
            strURL.append(URLEncoder.encode(txtEmail.getText().toString(),"UTF-8"));

            new HttpRequest().execute(strURL.toString());

        }catch (Exception ex){
            Log.i("doRequest", "Erro Request");
        }


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSalvar:{
                doRequest();
                break;
            }
        }
    }

    public class HttpRequest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            String retorno = null;
            try{
                String urlHttp = strings[0];
                URL url = new URL(urlHttp);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                retorno = new BufferedReader(ips).readLine();
            }catch(Exception ex){
            }
            return retorno;
        }
        @Override
        protected  void onPostExecute(String result){
          /*  if(result.equals("YES")){
                Toast.makeText(getBaseContext(), "Cliente cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), ListarActivity.class));
            } else{
                Toast.makeText(getBaseContext(), "Erro ao cadastrar o cliente!",Toast.LENGTH_SHORT).show();
            }*/
        }

    }


}
