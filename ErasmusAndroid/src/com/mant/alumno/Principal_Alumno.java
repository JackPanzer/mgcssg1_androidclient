package com.mant.alumno;

import java.util.HashMap;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.TareasAsincronas.SessionManager;
import com.mant.TareasAsincronas.aTaskConsultarDestinos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Principal_Alumno extends Activity {
	
	// Session Manager Class
    public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal__alumno);
		
		session = new SessionManager(getApplicationContext());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.principal__alumno, menu);
		return true;
	}
	
	public void clickVolver(View v){
		finish();
	}

	
	public void clickDestinos (View v){
		/**
		 * Comprueba que el usuario este logeado
		 * Redirecciona a la pantalla de Login si no es así
		 * */
		
		session.checkLogin(); 
		Intent act = new Intent(this, DestinoAlumnoActivity.class);
		startActivity(act);
		
		
		
		
	}
}
