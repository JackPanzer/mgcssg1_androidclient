package pract.mant.alumno;

import java.util.HashMap;

import pract.mant.erasmusandroid.SessionManager;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * Actividad principal del Alumno solo contiene el botón de volver
 * y el de acceder a sus destinos disponibles de la beca Erasmus.
 * @author Betanzos
 *
 */

public class Principal_Alumno extends Activity {
	
	
    public SessionManager session; // Session Manager Class

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
	
	/**
	 * Una vez que se pulsa el botón volver se vuelve a la actividad
	 * principal
	 * @param v Botón que lanza el evento
	 */
	
	public void clickVolver(View v){
		finish();
	}
	
	/**
	 * Se accede a la actividad DestinoAlumnoActivity en la cual
	 * se listan los destinos disponibles para un alumno.
	 * @param v Botón que lanza el evento
	 */
	
	public void clickDestinos (View v){
		/**
		 * Comprueba que el usuario este logueado
		 * Redirecciona a la pantalla de Login si no es así
		 * */
		
		session.checkLogin(); 
		Intent act = new Intent(this, DestinoAlumnoActivity.class);
		startActivity(act);
		
	}
	
	/**
	 * Envía al usuario a la pantalla para elegir las asignaturas
	 * de las que está matriculado
	 * 
	 * @param v Botón que lanza el evento
	 */
	public void clickAsignaturas (View v){
		/**
		 * Comprueba que el usuario este logueado
		 * Redirecciona a la pantalla de Login si no es así
		 * */
		
		session.checkLogin(); 
		Intent act = new Intent(this, OrigenAsignaturaAlumno.class);
		startActivity(act);
	}
}
