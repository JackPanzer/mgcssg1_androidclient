package com.mant.alumno;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.adaptadores_alumno.AdaptadorDestinosAsignaturas;
import com.mant.auxiliares_alumno.Asignatura;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DestinoAsignaturaActivity extends Activity {

	AdaptadorDestinosAsignaturas listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Asignatura>> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_asignatura);
		
		// get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new AdaptadorDestinosAsignaturas(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        expListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

				//En caso de que la posicion seleccionada gracias a "arg2" sea true que lo cambie a false 
				if (listDataChild.get(1).get(arg2).isChekeado()) {
					listDataChild.get(1).get(arg2).setChekeado(false);
				} else {
					//aqui al contrario que la anterior, que lo pase a true.
					listDataChild.get(1).get(arg2).setChekeado(true);
				}
				//Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
				listAdapter.notifyDataSetChanged();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destino_asignatura, menu);
		return true;
	}
	

	public void clickVolver(View v) {
		finish();
		}
	
	public void clickAceptar(View v) {
		Intent act = new Intent(this, AceptarPrecontratoActivity.class);
		startActivity(act);

	}
	
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Asignatura>>();
 
        // Adding child data
        listDataHeader.add("Destino 1");
        listDataHeader.add("Destino 2");
        listDataHeader.add("Destino 3");
        listDataHeader.add("Destino 4");
 
        // Adding child data
        List<Asignatura> d1 = new ArrayList<Asignatura>();
        d1.add(new Asignatura("Asignatura 1", false));
        d1.add(new Asignatura("Asignatura 2", false));
        d1.add(new Asignatura("Asignatura 3", false));
        d1.add(new Asignatura("Asignatura 4", false));
 
        List<Asignatura> d2 = new ArrayList<Asignatura>();
        d2.add(new Asignatura("Asignatura 1", false));
        d2.add(new Asignatura("Asignatura 2", false));
        d2.add(new Asignatura("Asignatura 3", false));
        d2.add(new Asignatura("Asignatura 4", false));
 
        List<Asignatura> d3 = new ArrayList<Asignatura>();
        d3.add(new Asignatura("Asignatura 1", false));
        d3.add(new Asignatura("Asignatura 2", false));
        d3.add(new Asignatura("Asignatura 3", false));
        d3.add(new Asignatura("Asignatura 4", false));
 
        List<Asignatura> d4 = new ArrayList<Asignatura>();
        d4.add(new Asignatura("Asignatura 1", false));
        d4.add(new Asignatura("Asignatura 2", false));
        d4.add(new Asignatura("Asignatura 3", false));
        d4.add(new Asignatura("Asignatura 4", false));
 
        listDataChild.put(listDataHeader.get(0), d1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), d2);
        listDataChild.put(listDataHeader.get(2), d3);
        listDataChild.put(listDataHeader.get(3), d4);
    }

}
