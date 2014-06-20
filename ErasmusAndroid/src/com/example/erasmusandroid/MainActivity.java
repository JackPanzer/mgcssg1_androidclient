package com.example.erasmusandroid;

import com.mant.TareasAsincronas.aTaskLogin;
import com.mant.administrador.BaseDeDatosActivity;
import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.PricipalCoordinadorActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickSalir(View v){
		//Sale de la aplicación
		finish();
		
	}
	
	public void clickLogin(View v){
		
		/* "Empaquetamos" parametros en el intent */
		String nick = ((EditText) findViewById(R.id.am_usuario)).getText().toString();
		String pass = ((EditText)findViewById(R.id.am_pass)).getText().toString();
		
		aTaskLogin atl = new aTaskLogin(nick, pass);
		atl.execute();
	}
	
	/*public void clickAlumnos(View v){
		//Se va a la ventana de alumnos
		Intent act = new Intent(this, Principal_Alumno.class);
		startActivity(act);
		
	}
	
	public void clickCoordinador(View v){
		//Se va a la ventana de Coordinadores
		Intent act = new Intent(this, PricipalCoordinadorActivity.class);
		startActivity(act);
		
	}
	
	public void clickAdministradores(View v){
		//Se va la ventana de Administradores
		Intent act = new Intent(this, BaseDeDatosActivity.class);
		startActivity(act);
		
	}*/
	

}
