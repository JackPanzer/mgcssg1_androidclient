package com.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.mant.adaptadores_alumno.AdaptadorDestinosAsignaturas;
import com.mant.adaptadores_coordinador.AdaptadorDestinosCoordinador;
import com.mant.auxiliares_coordinador.Nombre_Destino;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class ModificarDestinosActivity extends Activity {
	
	AdaptadorDestinosCoordinador adaptador_destinos;
    ExpandableListView lista_expandible;
    List<String> cabecera_lista;
    HashMap<String, List<Nombre_Destino>> contenido_lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_destinos);
		
		lista_expandible = (ExpandableListView) findViewById(R.id.expandableListView3);
		
		PrepararDatos();
		
		adaptador_destinos = new AdaptadorDestinosCoordinador(this, cabecera_lista, contenido_lista);
		
		lista_expandible.setAdapter(adaptador_destinos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.modificar_destinos, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickFinalizar(View v){
		finish();
		
	}
	
	private void PrepararDatos() {
		cabecera_lista = new ArrayList<String>();
		contenido_lista = new HashMap<String, List<Nombre_Destino>>();
 
        // Adding child data
        cabecera_lista.add("Destino 1");
        cabecera_lista.add("Destino 2");
        cabecera_lista.add("Destino 3");
        cabecera_lista.add("Destino 4");
 
        // Adding child data
        List<Nombre_Destino> d1 = new ArrayList<Nombre_Destino>();
        d1.add(new Nombre_Destino("Universidad de Cambridge"));
 
        List<Nombre_Destino> d2 = new ArrayList<Nombre_Destino>();
        d2.add(new Nombre_Destino("Universidad de Alabama"));
        
        List<Nombre_Destino> d3 = new ArrayList<Nombre_Destino>();
        d3.add(new Nombre_Destino("Universidad de Nueva York"));
        
        List<Nombre_Destino> d4 = new ArrayList<Nombre_Destino>();
        d4.add(new Nombre_Destino("Universidad de Mexico"));
        
 
        contenido_lista.put(cabecera_lista.get(0), d1); // Header, Child data
        contenido_lista.put(cabecera_lista.get(1), d2);
        contenido_lista.put(cabecera_lista.get(2), d3);
        contenido_lista.put(cabecera_lista.get(3), d4);
    }
}
