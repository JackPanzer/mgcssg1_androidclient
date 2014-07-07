package pract.mant.coordinador;

import pract.mant.alumno.AceptarPrecontratoActivity;
import pract.mant.erasmusandroid.SessionManager;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * En esta interfaz, se presentan las opciones disponibles para un
 * coordinador: aceptar precontrato, gestionar destinos y crear
 * coordinador
 *
 */
public class PrincipalCoordinadorActivity extends Activity {

	public SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pricipal_coordinador);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pricipal_coordinador, menu);
		return true;
	}

	/**
	 * Funci�n para el bot�n Volver, finaliza la actividad actual para volver
	 * a la anterior
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickVolver(View v){
		finish();
		
	}
	
	/**
	 * Funci�n para el bot�n Gestionar Destinos, avanza
	 * a la actividad siguiente: GestionarDestinosActivity
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickDestinos(View v){
		Intent act = new Intent(this, GestionarDestinosActivity.class);
		startActivity(act);
		
	}

	/**
	 * Funci�n para el bot�n Aceptar Precontrato, avanza
	 * a la actividad siguiente: AceptarPropuestaActivity
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickPrecontrato(View v){
		Intent act = new Intent(this, AceptarPropuestaActivity.class);
		startActivity(act);
		
	}

	/**
	 * Funci�n para el bot�n Crear Coordinador, avanza
	 * a la actividad siguiente: CrearCoordinadorActivity
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickCrearCoordinador(View v){
        Intent act = new Intent(this, CrearCoordinadorActivity.class);
        this.startActivity(act);
    }
	
}
