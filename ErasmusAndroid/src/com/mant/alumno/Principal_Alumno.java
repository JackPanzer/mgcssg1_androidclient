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
		/*
		Intent i = getIntent();
		String nick = i.getStringExtra("nick");
		String pass = i.getStringExtra("pass");
		
		aTaskLogin aTask = new aTaskLogin(this, nick, pass);
		aTask.execute();
		*/
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
		/*
		Intent act = new Intent(this, DestinoAlumnoActivity.class);
		startActivity(act);
		*/
		
		/**
		 * Comprueba que el usuario este logeado
		 * Redirecciona a la pantalla de Login si no es así
		 * */
		session.checkLogin(); 
		
		// get user data from session
       // HashMap<String, String> user = session.getUserDetails();

        //String idUsu = user.get(SessionManager.KEY_ID);
        
		aTaskConsultarDestinos atl = new aTaskConsultarDestinos(this, session);
		atl.execute();
		
	}
}
