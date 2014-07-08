package pract.mant.adaptadores_coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.auxiliares_coordinador.ModificarDestinos;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayDestinos;
import pract.mant.modelo.GenericResult;


import com.example.erasmusandroid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Esta clase extiende de BaseExpandableListAdapter la usaremos para mostrar
 * los distintos destinos almacenados, para luego dar la posibilidad de modificar
 * alguno de ellos de la base de datos.
 * @author Betanzos
 *
 */
public class AdaptadorModificarDestinosCoordinador extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> cabecera_lista;
	private HashMap<String, List<ModificarDestinos>> contenido_lista;
	private  SessionManager session;

	/**
	 * Constructor del AdaptadorModificarDestinosCoordinador donde recibirá por parametro el
	 * contexto de la aplicacion, una lista con los destinos y una tabla hash que relaciona
	 * cada destino con sus caracteristicas
	 * @param context contiene el contexto de la aplicación
	 * @param cabecera_lista contiene la lista de destinos
	 * @param contenido_lista contiene la tabla hash
	 */
	
	public AdaptadorModificarDestinosCoordinador(Context context,
			List<String> cabecera_lista,
			HashMap<String, List<ModificarDestinos>> contenido_lista) {
		this._context = context;
		this.cabecera_lista = cabecera_lista;
		this.contenido_lista = contenido_lista;
	}

	/**
	 * Esta función añade los datos del destinos al destinos, almacenadas en la
	 * tabla hash y que listarán según el groupPosition o en este caso destino, y 
	 * childPosition o en este caso posición de los parametros de un destino  dentro la tabla hash
	 */
	
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

		final ModificarDestinos nombre_destino = (ModificarDestinos) getChild(
				groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.modificar_nombre_destino, null);
		}
		// Nombre
		final EditText nombre = (EditText) convertView.findViewById(R.id.mnd_nombre);

		nombre.setText(nombre_destino.getNombre_destino());

		// Pais
		EditText txt_pais = (EditText) convertView.findViewById(R.id.mnd_pais);

		txt_pais.setText(nombre_destino.getPais());

		// idioma
		EditText txt_idioma = (EditText) convertView
				.findViewById(R.id.mnd_idioma);

		txt_idioma.setText(nombre_destino.getIdioma());

		// disponible
		final CheckBox bol_disponible = (CheckBox) convertView
				.findViewById(R.id.mnd_disponible);

		bol_disponible.setChecked(nombre_destino.getDisponible());

		// plazas
		final EditText txt_plazas = (EditText) convertView.findViewById(R.id.mnd_plazas);
		int plazas = nombre_destino.getPlazas();
		txt_plazas.setText(Integer.toString(plazas));

		// nivel
		EditText txt_nivel = (EditText) convertView.findViewById(R.id.mnd_nivel);

		txt_nivel.setText(nombre_destino.getNivel());

		//Boton guardar
		Button guardar = (Button) convertView.findViewById(R.id.mnd_guardar);
		
		guardar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				String nombre_mod = nombre.getText().toString();
				int disponible;
				//int pais = Integer.parseInt(((EditText) findViewById(R.id.acd_pais)).getText().toString());
				//int idioma = Integer.parseInt(((EditText) findViewById(R.id.acd_idioma)).getText().toString());
				if(bol_disponible.isChecked()){
					disponible = 1;
					
				}
				else
					disponible = 0;
				
				int plazas=Integer.parseInt(txt_plazas.getText().toString());
				//crear y llamar a task
				aTaskModificarTodosDestinos atl = new aTaskModificarTodosDestinos((Activity)_context, session,nombre_destino.getIdDestino() ,nombre_mod,nombre_destino.getIdPais(),nombre_destino.getIdIdioma(),disponible,plazas,nombre_destino.getIdNivel());
				atl.execute();

			}
		});

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

	/**
	 * Esta función añade un nombre a la cabecera la cual tomará del ArrayList
	 * Destinos y que se listrán por orden según aparecen en la lista através de
	 * la variable groupPositon.
	 */
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.nombre_cabecera_destino_modificar, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.cabecera_destino);
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

	/**
	 * Clase que contiene una tarea asincrona que realizará una llamada al servidor 
	 * para actualizar aquellos destinos que haya marcado el usuario.
	 * @author Betanzos
	 *
	 */
	
	public class aTaskModificarTodosDestinos extends AsyncTask<Void, Void, Void> {
		//Esto es un comentario
		// private int idUsu;
		private GenericResult respuesta;
		private SessionManager session; // SESSION OBJECT
		private Activity context;
		private String nombre;
		private int id_nombre;
		private int pais;
		private int idioma;
		private int disponible;
		private int plazas;
		private int nivel;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "editarDestino";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskModificarTodosDestinos(Activity _ctxt, SessionManager _session,int id_nombre,
				String nombre, int pais, int idioma, int disponible, int plazas,
				int nivel) {

			this.context = _ctxt;
			this.session = _session;
			this.nombre = nombre;
			this.pais = pais;
			this.idioma = idioma;
			this.disponible = disponible;
			this.plazas = plazas;
			this.nivel = nivel;
			this.id_nombre= id_nombre;
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				
				request.addProperty("idDestino", id_nombre);
				request.addProperty("nombre", nombre);
				request.addProperty("idPais", pais);
				request.addProperty("idIdioma", idioma);
				request.addProperty("disponible", disponible);
				request.addProperty("numPlazas", plazas);
				request.addProperty("nvlRequerido", nivel);

				/* Creamos un envelop <Sobre> */
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aquí metemos la peticion en el "Sobre"

				/* Definimos un objeto transporte para dirigir el Sobre */
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta que
				// se obtiene la respuesta
				// SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				if (envelope.getResponse() != null) {
					respuesta = new GenericResult((SoapObject) envelope.getResponse());

					// Si hasta aquí todo ha ido bien, lo siguiente será abrir la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast
						//Toast t = Toast.makeText(context, "Destino Creado", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						//Toast t = Toast.makeText(context, "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					
					else if(respuesta.getErrno()==-3){
						
					}
					else{
						// Todo ha ido bien, mostramos un Toast
						//Toast t = Toast.makeText(context, "Fallo en Conexion", Toast.LENGTH_SHORT);
						//t.show();
						

						// en base al rol
						
					}

				}

			} catch (Exception e) {

				String text = e.getMessage();
				int duration = Toast.LENGTH_SHORT;

				System.out.println(text);
				Toast t = Toast.makeText(context, text, duration);
				t.show();
			}

			return null;
		}

	}

}
