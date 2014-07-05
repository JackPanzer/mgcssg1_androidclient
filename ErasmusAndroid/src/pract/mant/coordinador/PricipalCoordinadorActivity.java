package pract.mant.coordinador;

import pract.mant.alumno.AceptarPrecontratoActivity;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


//Esta Activity se mostrará si los datos introducidos por el usuario
//coonciden con el rol coordinador
public class PricipalCoordinadorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pricipal_coordinador);
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
	
}
