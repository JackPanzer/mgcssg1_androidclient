package pract.mant.adaptadores_alumno;

import java.util.HashMap;
import java.util.List;

import pract.mant.auxiliares_alumno.Asignatura;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Esta clase extiende de BaseExpandableListAdapter la usaremos para crear 
 * los distintos expandible que contendr�n en su cabecera el nombre del
 * destino y tras expandirlo las distintas asignaturas para cada destino.
 * @author Betanzos
 *
 */

public class AdaptadorDestinosAsignaturas extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> Destinos; // Cabecera
	// Tabla hash que asocia cada nombre del destino con las asignaturas
	// que contiene
	private HashMap<String, List<Asignatura>> Contenido_Asignaturas;

	/**
	 * Constructor del AdaptadorDestinosAsignaturas donde recibir� por parametro el
	 * contexto de la aplicacion,un ArrayList con los destinos, una tabla hash donde 
	 * se relacionan los nombres de los distintos destinos con las asignturas
	 * que se imparten.
	 * @param context contexto actual
	 * @param Destinos array con el nombre de los destinos
	 * @param Contenido_Asignaturas tabla hash que relaciona destino con array de asignaturas
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

	/**
	 * Esta funci�n a�ade las asignaturas que tiene cada destino, almacenadas en la
	 * tabla hash y que listar�n seg�n el groupPosition o en este caso destino, y 
	 * childPosition o en este caso posici�n de la asigntura dentro la tabla hash
	 */
	
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		//En este caso no hemos usado una vista y lo hemos hecho directamente
		final Asignatura a = (Asignatura) getChild(groupPosition, childPosition);

		//Inflamos el dise�o
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.contenido_asignaturas, null);
		}
		
		//Creamos y cargamos en nuestro layout los datos que hemos recogido
		TextView nombre = (TextView) convertView.findViewById(R.id.asig_nombre);
		nombre.setText(a.getasignatura());

		CheckBox cb = (CheckBox) convertView.findViewById(R.id.asig_check);

		cb.setChecked(a.isChekeado());

		TextView creditos = (TextView) convertView
				.findViewById(R.id.asig_creditos);
		String valor = String.valueOf(a.getCreditos());
		creditos.setText(valor);

		
		//Cada vez que se cambia un check se actualiza tambi�n el objeto
		//que pasa los datos
		cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				a.setChekeado(isChecked);
			}
		});

		//Devolvemos la vista
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
	 * Esta funci�n a�ade un nombre a la cabecera la cual tomar� del ArrayList
	 * Destinos y que se listr�n por orden seg�n aparecen en la lista atrav�s de
	 * la variable groupPositon.
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		//Inflamos el dise�o
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.cabecera_destinos, null);
		}
		//Creamos y cargamos en nuestro layout los datos que hemos recogido
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.cd_nombre_destino);
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
