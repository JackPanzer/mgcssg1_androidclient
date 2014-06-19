package com.mant.alumno;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.TareasAsincronas.aTaskLogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Principal_Alumno extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal__alumno);
		
		Intent i = getIntent();
		String nick = i.getStringExtra("nick");
		String pass = i.getStringExtra("pass");
		
		aTaskLogin aTask = new aTaskLogin(nick, pass);
		aTask.execute();
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
		Intent act = new Intent(this, DestinoAlumnoActivity.class);
		startActivity(act);
	}
}
