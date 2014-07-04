package com.mant.adaptadores_alumno;

import java.util.HashMap;
import java.util.List;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.mant.auxiliares_alumno.Asignatura;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class AdaptadorDestinosAsignaturas extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> Destinos; // Cabecera
	// Tabla hash que asocia cada nombre del destino con las asignaturas
	// que contiene
	private HashMap<String, List<Asignatura>> Contenido_Asignaturas;

	/**
	 * Esta clase extiende de AdaptadorDestinosAsignaturas la usaremos para
	 * añadir los elementos necesarios para que nos aparezaca en nuestro layout
	 * la cabecera de nuestro despegable y el contenido de los mismos.
	 * 
	 * @author Betanzos
	 * 
	 */

	public AdaptadorDestinosAsignaturas(Context context, List<String> Destinos,
			HashMap<String, List<Asignatura>> Contenido_Asignaturas) {
		this._context = context;
		this.Destinos = Destinos;
		this.Contenido_Asignaturas = Contenido_Asignaturas;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.Contenido_Asignaturas.get(this.Destinos.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final Asignatura a = (Asignatura) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.asignaturas, null);
		}

		TextView nombre = (TextView) convertView.findViewById(R.id.asig_nombre);

		nombre.setText(a.getasignatura());

		CheckBox cb = (CheckBox) convertView.findViewById(R.id.asig_check);

		cb.setChecked(a.isChekeado());

		TextView creditos = (TextView) convertView
				.findViewById(R.id.asig_creditos);
		String valor = String.valueOf(a.getCreditos());
		creditos.setText(valor);

		cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				a.setChekeado(isChecked);
			}
		});

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.Contenido_Asignaturas.get(this.Destinos.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.Destinos.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.Destinos.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * Esta función añade un nombre a la cabecera la cual tomará del ArrayList
	 * 
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.destinos2, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
