package pract.mant.adaptadores_coordinador;

import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.adaptadores_coordinador.AdaptadorModificarDestinosCoordinador.aTaskModificarTodosDestinos;
import pract.mant.auxiliares_coordinador.ModificarDestinos;
import pract.mant.auxiliares_coordinador.EliminarDestino;
import pract.mant.erasmusandroid.SessionManager;
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


public class AdaptadorEliminaDestinosCoordinador extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> cabecera_lista;
	private HashMap<String, List<EliminarDestino>> contenido_lista;
	private SessionManager session;

	public AdaptadorEliminaDestinosCoordinador(Context context, List<String> cabecera_lista,
			HashMap<String, List<EliminarDestino>> contenido_lista) {
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

		final EliminarDestino nombre_destino2 = (EliminarDestino) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.eliminar_nombre_destino, null);
		}
		
		TextView txt_destino = (TextView) convertView.findViewById(R.id.textViewnombreuni);
		
		txt_destino.setText(nombre_destino2.getNombre_destino());
		
		final CheckBox check = (CheckBox)convertView.findViewById(R.id.checkBoxeliminar);
		
		check.setChecked(nombre_destino2.isCheck());
		
		//Boton guardar
				Button guardar = (Button) convertView.findViewById(R.id.end_guardar);
				
				guardar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						int id_nombre = nombre_destino2.getId_destino();
						
						if(check.isChecked()){
							//crear y llamar a task
							aTaskEliminarDestinos atl = new aTaskEliminarDestinos((Activity)_context, session,id_nombre);
							atl.execute();
						}
						
						

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

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.nombre_cabecera_destino_eliminar, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.cabecera_destino_eliminar);
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

	public class aTaskEliminarDestinos extends AsyncTask<Void, Void, Void> {
		//Esto es un comentario
		// private int idUsu;
		private GenericResult respuesta;
		private SessionManager session; // SESSION OBJECT
		private Activity context;
		private int id_nombre;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "borrarDestino";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskEliminarDestinos(Activity _ctxt, SessionManager _session,int id_nombre) {

			this.context = _ctxt;
			this.session = _session;
			this.id_nombre= id_nombre;
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				
				request.addProperty("idDestino", id_nombre);

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
						System.out.println("Hola");
						//Toast t = Toast.makeText(context, "Destino Creado", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						//Toast t = Toast.makeText(context, "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					
					else if(respuesta.getErrno()==-3){
						
						System.out.println("Hola");
					}
					else{
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
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

