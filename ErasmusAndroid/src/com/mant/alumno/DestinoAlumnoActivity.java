package com.mant.alumno;

import java.util.ArrayList;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.adaptadores_alumno.Adaptadordestinos;
import com.mant.auxiliares_alumno.Destinos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class DestinoAlumnoActivity extends Activity {

	
		ArrayList<Destinos> todos_destinos = new ArrayList<Destinos>();
		
		ListView lstLista;
		
		Adaptadordestinos adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_alumno);
		
		lstLista = (ListView) findViewById(R.id.listView1);
		
		todos_destinos.add(new Destinos("Destino 1", false));
		todos_destinos.add(new Destinos("Destino 2", false));
		todos_destinos.add(new Destinos("Destino 3", false));
		todos_destinos.add(new Destinos("Destino 4", false));
		
		
				adaptador = new Adaptadordestinos(this, todos_destinos);

				
				lstLista.setAdapter(adaptador);

				
				lstLista.setDividerHeight(3);

				//Se le aplica un Listener donde ira lo que tiene que hacer en caso de que sea pulsado
				lstLista.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

						//En caso de que la posicion seleccionada gracias a "arg2" sea true que lo cambie a false 
						if (todos_destinos.get(arg2).isChekeado()) {
							todos_destinos.get(arg2).setChekeado(false);
						} else {
							//aqui al contrario que la anterior, que lo pase a true.
							todos_destinos.get(arg2).setChekeado(true);
						}
						//Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
						adaptador.notifyDataSetChanged();

					}
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.destino_alumno, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickAceptar(View v){
		Intent act = new Intent(this, DestinoAsignaturaActivity.class);
		startActivity(act);
		
	}
	
}


