package com.mant.adaptadores_coordinador;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.erasmusandroid.R;

public class AdaptadorPropuestaAsignaturas extends ArrayAdapter<String>{
	
	Context contexto;
	ArrayList<String> mis_asignaturas;

	public AdaptadorPropuestaAsignaturas(Context _context, ArrayList<String> mis_asignaturas) {
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
			item = infalInflater.inflate(R.layout.contenido_propuesta_asignaturas, null);

			vistaitem = new VistaItem();

			vistaitem.nombre = (TextView) item.findViewById(R.id.cpa_asignaturas);


			item.setTag(vistaitem);

		} else {
			vistaitem = (VistaItem) item.getTag();
		}

		vistaitem.nombre.setText(mis_asignaturas.get(position));
		
		return (item);
	}
	static class VistaItem {
		TextView nombre;

	}

}
