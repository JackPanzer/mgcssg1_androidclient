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


//Esta Activity se mostrará si los datos introducidos por el usuario
//coonciden con el rol coordinador
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
	
	

	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickDestinos(View v){
		//Menu destinos
		Intent act = new Intent(this, GestionarDestinosActivity.class);
		startActivity(act);
		
	}
	
	public void clickPrecontrato(View v){
		//Menu Precontratos
		Intent act = new Intent(this, AceptarPropuestaActivity.class);
		startActivity(act);
		
	}
	
	public void clickCrearCoordinador(View v){
        Intent act = new Intent(this, CrearCoordinadorActivity.class);
        this.startActivity(act);
    }
	
}
