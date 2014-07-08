package pract.mant.adaptadores_alumno;

import java.util.HashMap;
import java.util.List;

import pract.mant.auxiliares_alumno.Precontrato;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Esta clase extiende de BaseExpandableListAdapter la usaremos para crear 
 * los distintos expandible que contendrán en su cabecera "Precontrato"
 * seguido de un numero, máximo 3, y tras expandirlo un resumen del precontrato
 * con el nombre del alumno y el destino.
 * @author Betanzos
 *
 */

public class AdaptadorPrecontrado extends BaseExpandableListAdapter{

	private Context _context;
	private List<String> Cabecera_Precontrato; // Contiene la cabecera de nuestro precontrato
	// Tabla hash que asocia cada precontrato con el destino
	//y el nombre del alumno
	private HashMap<String, List<Precontrato>> Contenido_Precontrato;

	/**
	 * Constructor del AdaptadorPrecontrado donde recibirá por parametro el
	 * contexto de la aplicacion,un ArrayList con "Precontrato" seguido de un id, 
	 * una tabla hash donde se relacionan los Precontratos con el nombre del alumno
	 * y el destino que ha elegido.
	 * @param context contexto actual
	 * @param Cabecera_Precontrato array que contiene los destinos
	 * @param Contenido_Precontrato tabla hash que relaciona los destinos con los distintos campos de precontrato
	 */
	
	public AdaptadorPrecontrado(Context context, List<String> Cabecera_Precontrato,
			HashMap<String, List<Precontrato>> Contenido_Precontrato) {
		this._context = context;
		this.Cabecera_Precontrato = Cabecera_Precontrato;
		this.Contenido_Precontrato = Contenido_Precontrato;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.Contenido_Precontrato.get(this.Cabecera_Precontrato.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * Esta función añade el nomnbre del alumno y el nombre del destino, almacenadas en la
	 * tabla hash y que listarán según el groupPosition o en este caso precontrato, y 
	 * childPosition o en este caso nombre del alumno y destino dentro la tabla hash
	 */
	
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		//En este caso no hemos usado una vista y lo hemos hecho directamente
		
		Precontrato p = (Precontrato) getChild(groupPosition, childPosition);
		
		//Inflamos el diseño
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.precontrato, null);
			
			
			
		}
		//Creamos y cargamos en nuestro layout los datos que hemos recogido
		TextView nombre = (TextView) convertView.findViewById(R.id.p_nombre);
		TextView destino = (TextView) convertView.findViewById(R.id.p_destino);
		
		nombre.setText(p.getNombre());
		destino.setText(p.getDestino());
		
		//Devolvemos la vista
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.Contenido_Precontrato.get(this.Cabecera_Precontrato.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.Cabecera_Precontrato.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.Cabecera_Precontrato.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	/**
	 * Esta función añade un nombre a la cabecera la cual tomará del ArrayList
	 * Destinos y que se listrán por orden según aparecen en la lista através de
	 * la variable groupPositon.
	 */

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		//Inflamos el diseño
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.cabecera_precontratos, null);
		}
		//Creamos y cargamos en nuestro layout los datos que hemos recogido
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.cprecontrato_cabecera);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);
		//Devolvemos la vista
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
