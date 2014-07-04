package com.mant.adaptadores_alumno;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.erasmusandroid.R;
import com.mant.auxiliares_alumno.Destinos;

/**
 * Esta clase extiende de ArrayAdapter la usaremos para añadir los
 * elementos necesarios para que nos aparezaca en nuestro layout
 * los nombres de los destinos.
 * @author Betanzos
 *
 */
public class Adaptadordestinos extends ArrayAdapter<Destinos> {

	private Activity contexto;
	private ArrayList<Destinos> mis_destinos;
	private int max_check = 3;
	private int check = 0;

	/**
	 * Constructor del Adaptadordestinos donde recibirá por parametro el
	 * contexto de la aplicacion y el ArrayList de los destinos.
	 * @param context
	 * @param mis_destinos
	 */
	
	public Adaptadordestinos(Activity context, ArrayList<Destinos> mis_destinos) {
		// Llamada al constructor de la clase superior donde requiere el
		// contexto, el layout y el arraylist
		super(context, R.layout.destinos, mis_destinos);
		this.contexto = context;
		this.mis_destinos = mis_destinos;

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
			item = inflador.inflate(R.layout.destinos, null);

			// Creamos un nuevo vistaitem que se almacenara en el tag de la
			// vista
			vistaitem = new VistaItem();

			// Almacenamos en el objeto la referencia del TextView buscandolo
			// por ID
			vistaitem.nombre = (TextView) item.findViewById(R.id.dest_nombre);

			// tambien almacenamos en el objeto la referencia del CheckBox
			// buscandolo por ID
			vistaitem.chkEstado = (CheckBox) item.findViewById(R.id.dest_check);

			//Guardamos en el tag de la vista el objeto vistaitem
			item.setTag(vistaitem);

		} else {
			// En caso de que la vista sea ya reutilizable se recupera el objeto
			// VistaItem almacenada en su tag
			vistaitem = (VistaItem) item.getTag();
		}

		// Se cargan los datos desde el ArrayList
		vistaitem.nombre.setText(mis_destinos.get(position).getDestino());
		vistaitem.chkEstado.setChecked(mis_destinos.get(position).isChekeado());

		
		//Comprobamos si se hacen mas de 3 check
		//Si se hacen mas de 3 se intenta impedir
		vistaitem.chkEstado
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mis_destinos.get(position).setChekeado(
								vistaitem.chkEstado.isChecked());

						if (vistaitem.chkEstado.isChecked()) {
							check++;
						}

						if (!vistaitem.chkEstado.isChecked()) {
							check--;
						}

						if (check > max_check) {

							vistaitem.chkEstado.setChecked(false);
							check--;
							mis_destinos.get(position).setChekeado(false);
						}

					}

				});

		// Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
	}

	// Esta clase se usa para almacenar el TextView y el CheckBox de una vista y
	// es donde esta el "truco" para que las vistas se guarden
	static class VistaItem {
		TextView nombre;
		CheckBox chkEstado;

	}

}
