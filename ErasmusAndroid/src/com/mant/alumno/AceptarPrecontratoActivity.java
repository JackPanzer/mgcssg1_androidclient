package com.mant.alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.adaptadores_alumno.AdaptadorPrecontrado;
import com.mant.auxiliares_alumno.Asignatura2;
import com.mant.auxiliares_alumno.Precontrato;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class AceptarPrecontratoActivity extends Activity {
	
	AdaptadorPrecontrado adaptador_precontrato;
    ExpandableListView expListView;
    List<String> lista_Destinos;
    HashMap<String, List<Precontrato>> contenido_destino;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_precontrato);
		
		expListView = (ExpandableListView) findViewById(R.id.expandableListView2);
		 
        // preparing list data
        prepareListData();
 
        adaptador_precontrato = new AdaptadorPrecontrado(this, lista_Destinos, contenido_destino);
 
        // setting list adapter
        expListView.setAdapter(adaptador_precontrato);
	}

	private void prepareListData() {
		lista_Destinos = new ArrayList<String>();
		contenido_destino = new HashMap<String, List<Precontrato>>();
 
        // Adding child data
		lista_Destinos.add("Destino 1");
		lista_Destinos.add("Destino 2");
		lista_Destinos.add("Destino 3");
		lista_Destinos.add("Destino 4");
 
        // Adding child data
        ArrayList<Asignatura2> d1 = new ArrayList<Asignatura2>();
        d1.add(new Asignatura2("Asignatura 1"));
        d1.add(new Asignatura2("Asignatura 2"));
        d1.add(new Asignatura2("Asignatura 3"));
        d1.add(new Asignatura2("Asignatura 4"));
        
        List<Precontrato> p1 = new ArrayList<Precontrato>();
        p1.add(new Precontrato("Jacinto Mata", "677234568", "Aeronautica", "Huelva", "B1", d1));
        
 
        contenido_destino.put(lista_Destinos.get(0), p1); // Header, Child data
        contenido_destino.put(lista_Destinos.get(1), p1);
        contenido_destino.put(lista_Destinos.get(2), p1);
        contenido_destino.put(lista_Destinos.get(3), p1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aceptar_precontrato, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
		
	}
	
	
}
