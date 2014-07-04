package com.example.erasmusandroid;

import com.mant.TareasAsincronas.SessionManager;
import com.mant.TareasAsincronas.aTaskLogin;
import com.mant.administrador.BaseDeDatosActivity;
import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.CrearAlumnoActivity;
import com.mant.coordinador.PricipalCoordinadorActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Esta es nuestra actividad principal, desde est� actividad
 * despu�s de saber el rol del usuario que se intenta loguear 
 * pasaremos a una actividad u otra.
 * @author Betanzos
 *
 */

public class MainActivity extends Activity {
	
public SessionManager session; //SESSION OBJECT

	
	@Override
	/**
	 * La funcion onCreate llamar� a la clase SessionManager
	 * que contralar� nuestra sesi�n de usuario.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        session = new SessionManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Despu�s de hacer click en el bot�n salir
	 * saldremos de la aplicaci�n.
	 * @param v
	 */
	public void clickSalir(View v){
		finish();
		
	}
	
	/**
	 * Despu�s de hacer click sobre el bot�n Log�n y habiendo a�adido
	 * nuestro respectivo usuario y contrase�a, pasaremos a nuestra actividad
	 * correspondiente que puede ser la de coordinador o la del alumno.
	 * @param v
	 */
	public void clickLogin(View v){
		//Creamos las variables nick y pass que recoger�n los datos de entrada 
		//de la activity y que pasaremos a nuestra tarea as�ncrona aTaskLogin.
		String nick = ((EditText) findViewById(R.id.am_usuario)).getText().toString();
		String pass = ((EditText)findViewById(R.id.am_pass)).getText().toString();
		
		aTaskLogin atl = new aTaskLogin(this, session, nick, pass);
		atl.execute();
	}
	
	public void clickCrearAlumno(View v){
		Intent act = new Intent(this, CrearAlumnoActivity.class);
		this.startActivity(act);
	}
	
	

}
