package com.mant.adaptadores_coordinador;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erasmusandroid.R;
import com.mant.adaptadores_alumno.AdaptadorAsignatura;
import com.mant.auxiliares_alumno.Precontrato;
import com.mant.auxiliares_coordinador.Propuesta;

public class AdaptadorPropuesta extends BaseExpandableListAdapter{

	private Context _context;
	List<String> cabecera_propuesta;
    HashMap<String, List<Propuesta>> contenido_propuesta;

	public AdaptadorPropuesta(Context context, List<String> cabecera_propuesta,
			HashMap<String, List<Propuesta>> contenido_propuesta) {
		this._context = context;
		this.cabecera_propuesta = cabecera_propuesta;
		this.contenido_propuesta = contenido_propuesta;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.contenido_propuesta.get(this.cabecera_propuesta.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		
		
		Propuesta p = (Propuesta) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.contenido_propuesta, null);
			
			
			
		}
		
		TextView nombre = (TextView) convertView.findViewById(R.id.cp_nombre);
		TextView telefono = (TextView) convertView.findViewById(R.id.cp_telefono);
		TextView titulacion = (TextView) convertView.findViewById(R.id.cp_titulacion);
		TextView poblacion = (TextView) convertView.findViewById(R.id.cp_poblacion);
		TextView idioma = (TextView) convertView.findViewById(R.id.cp_idioma);
		
		
		ListView lstLista=(ListView) convertView.findViewById(R.id.cp_lista_asig);
				
		AdaptadorPropuestaAsignaturas adaptador;
		
		adaptador = new AdaptadorPropuestaAsignaturas(_context, p.getAsignaturas());
		
		nombre.setText(p.getNombre());
		telefono.setText(p.getTelefono());
		titulacion.setText(p.getTitulacion());
		poblacion.setText(p.getPoblacion());
		idioma.setText(p.getIdioma());
		
		lstLista.setAdapter(adaptador);
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.contenido_propuesta.get(this.cabecera_propuesta.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.cabecera_propuesta.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.cabecera_propuesta.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.propuesta, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.txt_cabecera_propuesta);
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
