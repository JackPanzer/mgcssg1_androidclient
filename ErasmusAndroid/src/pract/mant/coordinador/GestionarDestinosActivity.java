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

	public void clickVolver(View v){
		finish();
	}
	
	public void clickCrear(View v){
		//Menu crear
		Intent act = new Intent(this, CrearDestinoActivity.class);
		startActivity(act);
	}
	
	public void clickModificar(View v){
		//Menu modificar
		Intent act = new Intent(this, ModificarDestinosActivity.class);
		startActivity(act);
	}
	public void clickEliminar(View v){
		//Menu eliminar
		Intent act = new Intent(this, EliminarDestinoActivity.class);
		startActivity(act);
	}
	
}
