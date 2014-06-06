package com.mant.coordinador;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GestionarDestinosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestionar_destinos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gestionar_destinos, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
	}
	
	public void clickCrear(View v){
		//Menu crear
		Intent act = new Intent(this, CrearDestinoActivity.class);
		startActivity(act);
	}
	
	public void clickModificar(View v){
		//Menu modificar
		Intent act = new Intent(this, ModificarDestinosActivity.class);
		startActivity(act);
	}
	public void clickEliminar(View v){
		//Menu eliminar
		Intent act = new Intent(this, EliminarDestinoActivity.class);
		startActivity(act);
	}
	
}
