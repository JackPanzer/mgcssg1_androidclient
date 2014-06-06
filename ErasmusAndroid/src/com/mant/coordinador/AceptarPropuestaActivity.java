package com.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.adaptadores_alumno.AdaptadorPrecontrado;
import com.mant.adaptadores_coordinador.AdaptadorPropuesta;
import com.mant.auxiliares_alumno.Asignatura2;
import com.mant.auxiliares_alumno.Precontrato;
import com.mant.auxiliares_coordinador.Propuesta;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class AceptarPropuestaActivity extends Activity {
	
	AdaptadorPropuesta adaptador_propuesta;
    ExpandableListView expandible_propuesta;
    List<String> cabecera_propuesta;//cabecera del expandible
    HashMap<String, List<Propuesta>> contenido_propuesta;//contenido del expandible

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_propuesta);
		
		//Creo el objeto que maneja el expandible
		expandible_propuesta = (ExpandableListView) findViewById(R.id.expandableListView5);
		//Añado los datos a los arrays
		preparar_datos();
		//LLamo al adaptador que cargará los datos en el layout
		adaptador_propuesta = new AdaptadorPropuesta(this, cabecera_propuesta, contenido_propuesta);
 
        //Se carga en la aplicancion
		expandible_propuesta.setAdapter(adaptador_propuesta);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.aceptar_propuesta, menu);
		return true;
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	//funcion de prueba que será sustituida por consulta a la base de datos
	private void preparar_datos() {
		cabecera_propuesta = new ArrayList<String>();
		contenido_propuesta = new HashMap<String, List<Propuesta>>();
 
        // Adding child data
		cabecera_propuesta.add("Propuesta 1");
		cabecera_propuesta.add("Propuesta 2");
		cabecera_propuesta.add("Propuesta 3");
		cabecera_propuesta.add("Propuesta 4");
 
        // Adding child data
        
		ArrayList<String> asignaturas = new ArrayList<String>();
		asignaturas.add("Asignatura 1");
		asignaturas.add("Asignatura 2");
		asignaturas.add("Asignatura 3");
		asignaturas.add("Asignatura 4");
		
        List<Propuesta> p1 = new ArrayList<Propuesta>();
        p1.add(new Propuesta("Jacinto Mata", "677234566", "Aeronautica", "Huelva", "B1", asignaturas));
        
        List<Propuesta> p2 = new ArrayList<Propuesta>();
        p2.add(new Propuesta("Anna Simon", "675346091", "Forestales", "Malaga", "A1", asignaturas));
        
        List<Propuesta> p3 = new ArrayList<Propuesta>();
        p3.add(new Propuesta("Conchita Wurst", "675434898", "Informatica", "Sevilla", "P1", asignaturas));
        
        List<Propuesta> p4 = new ArrayList<Propuesta>();
        p4.add(new Propuesta("Bruce Willis", "687570501", "Mecanica", "Jaen", "I1", asignaturas));
        
        
 
        contenido_propuesta.put(cabecera_propuesta.get(0), p1); // Header, Child data
        contenido_propuesta.put(cabecera_propuesta.get(1), p2);
        contenido_propuesta.put(cabecera_propuesta.get(2), p3);
        contenido_propuesta.put(cabecera_propuesta.get(3), p4);
		
	}


}
