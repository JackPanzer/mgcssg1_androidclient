package com.mant.adaptadores_coordinador;

import java.util.HashMap;
import java.util.List;

import com.mant.auxiliares_coordinador.Nombre_Destino;

import com.example.erasmusandroid.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class AdaptadorDestinosCoordinador extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> cabecera_lista;
	private HashMap<String, List<Nombre_Destino>> contenido_lista;

	public AdaptadorDestinosCoordinador(Context context, List<String> cabecera_lista,
			HashMap<String, List<Nombre_Destino>> contenido_lista) {
		this._context = context;
		this.cabecera_lista = cabecera_lista;
		this.contenido_lista = contenido_lista;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.contenido_lista.get(this.cabecera_lista.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		Nombre_Destino nombre_destino = (Nombre_Destino) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.modificar_nombre_destino, null);
		}
		
		EditText txt_destino = (EditText) convertView.findViewById(R.id.EditTextModDestino);
		
		txt_destino.setText(nombre_destino.getNombre_destino());
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.contenido_lista.get(this.cabecera_lista.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.cabecera_lista.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.cabecera_lista.size();
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
			convertView = infalInflater.inflate(R.layout.nombre_cabecera_destino_modificar, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.cabecera_destino);
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

