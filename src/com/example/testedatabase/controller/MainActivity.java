package com.example.testedatabase.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testedatabase.R;
import com.example.testedatabase.DAO.ContatoDAO;
import com.example.testedatabase.Model.Contato;

public class MainActivity extends Activity implements OnClickListener {

	private ContatoDAO dao;
	
	private EditText input_nome, input_novo_nome, input_id;
	private Button btn_salvar, btn_update;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        input_nome = (EditText) findViewById(R.id.input_nome);
        input_novo_nome = (EditText) findViewById(R.id.input_novo_nome);
        input_id = (EditText) findViewById(R.id.input_id);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_update = (Button) findViewById(R.id.btn_update);
        
        btn_salvar.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        
        dao = new ContatoDAO(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onClick(View view) {
    	Contato contato = new Contato();
    	
    	if (view.getId() == R.id.btn_salvar) {
    		contato.setNome(input_nome.getText().toString());
    		dao.salvar(contato);
    	} else if(view.getId() == R.id.btn_update) {
    		contato.setNome(input_novo_nome.getText().toString());
    		contato.setId(Long.parseLong(input_id.getText().toString()));
    		dao.update(contato);
    	}
    	
    	Toast.makeText(this, dao.listar().toString(), Toast.LENGTH_LONG).show();
    }
    
}
