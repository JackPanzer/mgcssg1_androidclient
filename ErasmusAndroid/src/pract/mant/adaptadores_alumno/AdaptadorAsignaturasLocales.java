package pract.mant.adaptadores_alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pract.mant.adaptadores_alumno.AdaptadorDestinos.VistaItem;
import pract.mant.auxiliares_alumno.Asignatura;
import pract.mant.auxiliares_alumno.AsignaturaLocal;
import pract.mant.auxiliares_alumno.Destinos;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Esta clase extiende de BaseExpandableListAdapter la usaremos para crear 
 * los distintos registros de asignaturas para su posterior envío
 * a la base de datos.
 *
 */

public class AdaptadorAsignaturasLocales extends ArrayAdapter<AsignaturaLocal> {

	private Activity contexto;
	private List<AsignaturaLocal> mis_asignaturas;

	/**
	 * Constructor del Adaptadordestinos donde recibirá por parametro el
	 * contexto de la aplicacion y el ArrayList de los destinos.
	 * @param context
	 * @param mis_destinos
	 */
	
	public AdaptadorAsignaturasLocales(Activity context, List<AsignaturaLocal> mis_asignaturas) {
		// Llamada al constructor de la clase superior donde requiere el
		// contexto, el layout y el arraylist
		super(context, R.layout.asiglocales, mis_asignaturas);
		this.contexto = context;
		this.mis_asignaturas = mis_asignaturas;

	}

	/**
	 * Este metodo es el que se encarga de dibujar cada Item de la lista
	 * y se invoca cada vez que se necesita mostrar un nuevo item.
	 * En el se pasan parametros como la posicion del elemento mostrado, la
	 * vista (View) del elemento mostrado y el conjunto de vistas
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		View item = convertView;

		// Creamos esta variable para almacen posteriormente en el la vista que
		// ha dibujado
		final VistaItem vistaitem;

		// Si se decide que no existe una vista reutilizable para el proximo
		// item entra en la condicion.
		// De este modo tambien ahorramos tener que volver a generar vistas
		if (item == null) {

			// Obtenemos una referencia de Inflater para poder inflar el diseño
			LayoutInflater inflador = contexto.getLayoutInflater();

			// Se le define a la vista (item) el tipo de diseño que tiene que
			// tener
			item = inflador.inflate(R.layout.asiglocales, null);

			// Creamos un nuevo vistaitem que se almacenara en el tag de la
			// vista
			vistaitem = new VistaItem();

			// Almacenamos en el objeto la referencia del TextView buscandolo
			// por ID
			vistaitem.nombre = (TextView) item.findViewById(R.id.asiglocal_nombre);
			
			vistaitem.coordinador = (TextView) item.findViewById(R.id.asiglocal_coordiandor);
			
			vistaitem.creditos = (TextView) item.findViewById(R.id.asiglocal_creditos);
			
			// tambien almacenamos en el objeto la referencia del CheckBox
			// buscandolo por ID
			vistaitem.chkEstado = (CheckBox) item.findViewById(R.id.asiglocal_check);
			vistaitem.chkEstado
			.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					AsignaturaLocal modelo = (AsignaturaLocal) vistaitem.chkEstado.getTag();
					
					modelo.setChekeado(isChecked);
				}

			});
			
			vistaitem.convalidar = (CheckBox) item.findViewById(R.id.asiglocal_convalidar);
			vistaitem.convalidar
			.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					AsignaturaLocal modelo = (AsignaturaLocal) vistaitem.convalidar.getTag();
					
					modelo.setConvalidar(isChecked);
				}

			});

			//Guardamos en el tag de la vista el objeto vistaitem
			item.setTag(vistaitem);
			vistaitem.chkEstado.setTag(mis_asignaturas.get(position));
			vistaitem.convalidar.setTag(mis_asignaturas.get(position));
			
			
		} else {
			// En caso de que la vista sea ya reutilizable se recupera el objeto
			// VistaItem almacenada en su tag
			item = convertView;
		      ((VistaItem) item.getTag()).chkEstado.setTag(mis_asignaturas.get(position));
		      ((VistaItem) item.getTag()).convalidar.setTag(mis_asignaturas.get(position));
		}

		VistaItem holder = (VistaItem) item.getTag();
	    //holder.text.setText(list.get(position).getName());
	    //holder.checkbox.setChecked(list.get(position).isSelected());
	    
	    // Se cargan los datos desde el ArrayList
	 	holder.nombre.setText(mis_asignaturas.get(position).getasignatura());
	 	holder.coordinador.setText(mis_asignaturas.get(position).getCoordinador());
	 	holder.creditos.setText(String.valueOf( mis_asignaturas.get(position).getCreditos() + " créditos" ));
	 	holder.chkEstado.setChecked(mis_asignaturas.get(position).isChekeado());
	 	holder.convalidar.setChecked(false);
	    
		// Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
	}

	// Esta clase se usa para almacenar los TextView y el CheckBox de una vista 
	static class VistaItem {
		TextView nombre;
		TextView coordinador;
		TextView creditos;
		CheckBox chkEstado;
		CheckBox convalidar;
	}

}
