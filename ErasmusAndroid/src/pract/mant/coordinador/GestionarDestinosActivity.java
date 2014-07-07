package pract.mant.coordinador;

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
 * En esta interfaz, se presentan las opciones disponibles para la
 * gestión de los destinos: crear, modificar y eliminar
 *
 */
public class GestionarDestinosActivity extends Activity {

	public SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestionar_destinos);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gestionar_destinos, menu);
		return true;
	}

	/**
	 * Función para el botón Volver, finaliza la actividad actual para volver
	 * a la anterior
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickVolver(View v){
		finish();
	}
	
	/**
	 * Función para el botón Crear Destinos, avanza
	 * a la actividad siguiente: CrearDestinoActivity
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickCrear(View v){
		Intent act = new Intent(this, CrearDestinoActivity.class);
		startActivity(act);
	}
	
	/**
	 * Función para el botón Modificar Destinos, avanza
	 * a la actividad siguiente: ModificarDestinoActivity
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickModificar(View v){
		Intent act = new Intent(this, ModificarDestinosActivity.class);
		startActivity(act);
	}
	
	/**
	 * Función para el botón Eliminar Destinos, avanza
	 * a la actividad siguiente: EliminarDestinoActivity
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickEliminar(View v){
		Intent act = new Intent(this, EliminarDestinoActivity.class);
		startActivity(act);
	}
	
}
