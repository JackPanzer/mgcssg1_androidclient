package com.mant.adaptadores_alumno;

import java.util.ArrayList;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.mant.auxiliares_alumno.Asignatura2;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AdaptadorAsignatura extends ArrayAdapter<Asignatura2>{
	
	Context contexto;
	ArrayList<Asignatura2> mis_asignaturas;

	public AdaptadorAsignatura(Context _context, ArrayList<Asignatura2> mis_asignaturas) {
		//Llamada al constructor de la clase superior donde requiere el contexto, el layout y el arraylist
		super(_context, R.layout.asignaturas2, mis_asignaturas);
		this.contexto = _context;
		this.mis_asignaturas = mis_asignaturas;

	}

	//Este metodo es el que se encarga de dibujar cada Item de la lista
	//y se invoca cada vez que se necesita mostrar un nuevo item.
	//En el se pasan parametros como la posicion del elemento mostrado, la vista (View) del elemento mostrado y el conjunto de vistas
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;
		
		VistaItem vistaitem;

		if (item == null) {
			
			LayoutInflater infalInflater = (LayoutInflater) this.contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			item = infalInflater.inflate(R.layout.asignaturas2, null);

			vistaitem = new VistaItem();

			vistaitem.nombre = (TextView) item.findViewById(R.id.nom_asig);


			item.setTag(vistaitem);

		} else {
			vistaitem = (VistaItem) item.getTag();
		}

		vistaitem.nombre.setText(mis_asignaturas.get(position).getNombre());
		
		return (item);
	}
	static class VistaItem {
		TextView nombre;

	}

}
