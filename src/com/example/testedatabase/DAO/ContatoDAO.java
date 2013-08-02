package com.example.testedatabase.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.testedatabase.Model.Contato;
import com.example.testedatabase.Util.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContatoDAO {
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public ContatoDAO(Context context) {
		helper = new DatabaseHelper(context);
	}
	
	private SQLiteDatabase getDb() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		
		return db;
	}
	
	public void close() {
		helper.close();
	}
	
	public void salvar(Contato contato) {
		ContentValues values = contatoToContentValues(contato);
		
		getDb().insert(DatabaseHelper.Contato.TABLE_NAME, null, values);
	}
	
	public Contato update(Contato contato) {
		ContentValues values = contatoToContentValues(contato);
		
		getDb().update(DatabaseHelper.Contato.TABLE_NAME, values, 
				DatabaseHelper.Contato._ID + " = " + contato.getId(), null);
		
		return contato;
	}
	
	public void delete(Contato contato) {
		long id = contato.getId();
		getDb().delete(DatabaseHelper.Contato.TABLE_NAME, 
				DatabaseHelper.Contato._ID + " = " + id, null);
	}
	
	public List<Contato> listar() {
		List<Contato> contatos = new ArrayList<Contato>();
		
		Cursor cursor = getDb().query(DatabaseHelper.Contato.TABLE_NAME, 
				DatabaseHelper.Contato.ALL_COLUMNS, 
				null, null, null, null, null);
		
		while (cursor.moveToNext()) {
			Contato contato = cursorToContato(cursor);
			contatos.add(contato);
		}
		
		cursor.close();
		
		return contatos;
	}
	
	public Contato buscarPorId(long id) {
		Cursor cursor = getDb().query(DatabaseHelper.Contato.TABLE_NAME, 
				DatabaseHelper.Contato.ALL_COLUMNS, 
				DatabaseHelper.Contato._ID + " = " + id, 
				null, null, null, null);
		
		cursor.moveToFirst();
		Contato contato = new Contato();
		contato = cursorToContato(cursor);
		
		cursor.close();
		
		return contato;
	}
	
	private Contato cursorToContato(Cursor cursor) {
		Contato contato = new Contato();
		contato.setId(cursor.getLong(0));
		contato.setNome(cursor.getString(1));
		
		return contato;
	}
	
	private ContentValues contatoToContentValues(Contato contato) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Contato.NOME, contato.getNome());
		
		return values;
	}
		
}
