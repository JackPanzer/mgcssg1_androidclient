package com.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.adaptadores_coordinador.AdaptadorDestinosCoordinador2;
import com.mant.auxiliares_coordinador.Nombre_Destino;
import com.mant.auxiliares_coordinador.Nombre_Destino2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class EliminarDestinoActivity extends Activity {

	AdaptadorDestinosCoordinador2 adaptador_destinos;
    ExpandableListView lista_expandible;
    List<String> cabecera_lista;
    HashMap<String, List<Nombre_Destino2>> contenido_lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_destino);
		
		lista_expandible = (ExpandableListView) findViewById(R.id.expandableListView4);
		
		PrepararDatos();
		
		adaptador_destinos = new AdaptadorDestinosCoordinador2(this, cabecera_lista, contenido_lista);
		
		lista_expandible.setAdapter(adaptador_destinos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_destino, menu);
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
		contenido_lista = new HashMap<String, List<Nombre_Destino2>>();
 
        // Adding child data
        cabecera_lista.add("Destino 1");
        cabecera_lista.add("Destino 2");
        cabecera_lista.add("Destino 3");
        cabecera_lista.add("Destino 4");
 
        // Adding child data
        List<Nombre_Destino2> d1 = new ArrayList<Nombre_Destino2>();
        d1.add(new Nombre_Destino2("Universidad de Cambridge",false));
 
        List<Nombre_Destino2> d2 = new ArrayList<Nombre_Destino2>();
        d2.add(new Nombre_Destino2("Universidad de Alabama",false));
        
        List<Nombre_Destino2> d3 = new ArrayList<Nombre_Destino2>();
        d3.add(new Nombre_Destino2("Universidad de Nueva York",false));
        
        List<Nombre_Destino2> d4 = new ArrayList<Nombre_Destino2>();
        d4.add(new Nombre_Destino2("Universidad de Mexico",false));
        
 
        contenido_lista.put(cabecera_lista.get(0), d1); // Header, Child data
        contenido_lista.put(cabecera_lista.get(1), d2);
        contenido_lista.put(cabecera_lista.get(2), d3);
        contenido_lista.put(cabecera_lista.get(3), d4);
    }
	
}
