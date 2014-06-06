package com.mant.administrador;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BaseDeDatosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_de_datos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_de_datos, menu);
		return true;
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickFormulario(View v){
		Intent act = new Intent(this, FormularioActivity.class);
		startActivity(act);
		
	}
	
	public void clickSentencia(View v){
		Intent act = new Intent(this, SentenciaActivity.class);
		startActivity(act);
		
	}
}
