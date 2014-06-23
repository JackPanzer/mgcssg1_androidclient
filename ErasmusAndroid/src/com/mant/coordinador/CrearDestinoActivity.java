package com.mant.coordinador;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.TareasAsincronas.SessionManager;
import com.mant.TareasAsincronas.aTaskConsultarDestinos;
import com.mant.TareasAsincronas.aTaskCrearDestinos;
import com.mant.TareasAsincronas.aTaskLogin;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

// En esta clase habría que crear una función que guardará los datos
//que introduce el coordinador en la base de datos

public class CrearDestinoActivity extends Activity {
	
	// Session Manager Class
    public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_destino);
		
		session = new SessionManager(getApplicationContext());
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
		String nombre = ((EditText) findViewById(R.id.acd_nombre)).getText().toString();
		int pais = Integer.parseInt(((EditText) findViewById(R.id.acd_pais)).getText().toString());
		int idioma = Integer.parseInt(((EditText) findViewById(R.id.acd_idioma)).getText().toString());
		int disponible = Integer.parseInt(((EditText) findViewById(R.id.acd_disponible)).getText().toString());
		int plazas = Integer.parseInt(((EditText) findViewById(R.id.acd_plazas)).getText().toString());
		int nivel = Integer.parseInt(((EditText) findViewById(R.id.acd_nivel)).getText().toString());
	
		session.checkLogin(); 
		
		aTaskCrearDestinos atl = new aTaskCrearDestinos(this, session,nombre,pais,idioma,disponible,plazas,nivel);
		atl.execute();
		finish();
		
	}

}
