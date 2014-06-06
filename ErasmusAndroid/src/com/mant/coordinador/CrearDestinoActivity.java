package com.mant.coordinador;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

// En esta clase habría que crear una función que guardará los datos
//que introduce el coordinador en la base de datos

public class CrearDestinoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_destino);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.crear_destino, menu);
		return true;
		
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickFinalizar(View v){
		finish();
		
	}

}
