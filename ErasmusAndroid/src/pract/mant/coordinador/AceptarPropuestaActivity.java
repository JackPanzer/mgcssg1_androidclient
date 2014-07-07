package pract.mant.coordinador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import pract.mant.adaptadores_alumno.AdaptadorPrecontrado;
import pract.mant.adaptadores_coordinador.AdaptadorEliminaDestinosCoordinador;
import pract.mant.adaptadores_coordinador.AdaptadorPropuesta;
import pract.mant.auxiliares_alumno.Precontrato;
import pract.mant.auxiliares_coordinador.EliminarDestino;
import pract.mant.auxiliares_coordinador.Propuesta;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayDestinos;
import pract.mant.modelo.ArrayPrecontrato;
import pract.mant.modelo.GenericResult;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * En esta interfaz, el coordinador puede visualizar los precontratos pendientes
 * de aceptación, y aceptarlos si así lo cree conveniente.
 *
 */
public class AceptarPropuestaActivity extends Activity {

	AdaptadorPropuesta adaptador_propuesta;
	ExpandableListView expandible_propuesta;
	List<String> cabecera_propuesta;// cabecera del expandible
	HashMap<String, List<Propuesta>> contenido_propuesta;// contenido del expandible

	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_propuesta);

		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));

		// Creo el objeto que maneja el expandible
		aTaskObtenerPrecontratos atl = new aTaskObtenerPrecontratos(this,
				session);
		atl.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.aceptar_propuesta, menu);
		return true;
	}
	/**
	 * Función para el botón Volver, finaliza la actividad actual para volver
	 * a la anterior
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickVolver(View v) {
		finish();

	}
	/**
	 * Función para el botón Aceptar, llama a la tarea asíncrona que se encarga de
	 * aceptar los precontratos
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void clickAceptar(View v) {
		aTaskAceptarSolicitud atl = new aTaskAceptarSolicitud(this,
				session);
		atl.execute();
		

	}
	
	/**
	 * Una vez aceptados los precontratos, se recarga la lista de los restantes
	 * por aceptar
	 * 
	 */
	public void Actualizar() {
		aTaskObtenerPrecontratos atl = new aTaskObtenerPrecontratos(this,
				session);
		atl.execute();

	}
	
	/**
	 * Carga la información contenida en las estructuras de datos de la clase en
	 * la interfaz para su tratamiento
	 * 
	 */
	public void cargarLista() {

		expandible_propuesta = (ExpandableListView) findViewById(R.id.expandableListView5);
		// Añado los datos a los arrays
		// LLamo al adaptador que cargará los datos en el layout
		adaptador_propuesta = new AdaptadorPropuesta(this, cabecera_propuesta,contenido_propuesta);

		// Se carga en la aplicancion
		expandible_propuesta.setAdapter(adaptador_propuesta);

	}
	/**
	 * Clase para obtener la lista de los precontratos por aceptar
	 *
	 */
	private class aTaskObtenerPrecontratos extends AsyncTask<Void, Void, Void> {

		private SessionManager session;
		private ArrayPrecontrato respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerPrecontratos";
		final String SOAP_ACTION = "urn:Erasmus";
		
		/**
		 * 
		 * @param _ctxt Contexto de la actividad
		 * @param _session Objeto para gestionar la información del alumno
		 */
		public aTaskObtenerPrecontratos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				//Conectando
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				//Añadimos los parámetros necesarios para la consulta
				request.addProperty("idAlumno", 1);
				//Creamos un sobre
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta
				// que se obtiene la respuesta
				if (envelope.getResponse() != null) {
					SoapObject aux = (SoapObject) envelope.getResponse();
					
					respuesta = new ArrayPrecontrato(aux);

					// Si hasta aquí todo ha ido bien, lo siguiente será abrir
					// la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context, "Destino Creado",
						// Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					} else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context,
						// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					}

					else {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context,
						// "Fallo en Conexion", Toast.LENGTH_SHORT);
						// t.show();

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

		/**
		 * Cargamos en nuestra lista para la cabecera y en nuestra tabla hash para
		 * el contenido, los datos recibidos de la consulta
		 */
		@Override
		protected void onPostExecute(Void result) {
			if(respuesta!=null){
			cabecera_propuesta = new ArrayList<String>();
			contenido_propuesta = new HashMap<String, List<Propuesta>>();
			for (int i = 0; i < respuesta.getPrecontrato().size(); i++) {
				cabecera_propuesta.add("Propuesta" + (i + 1));
				List<Propuesta> p1 = new ArrayList<Propuesta>();
				p1.add(new Propuesta(respuesta.getPrecontrato().get(i)
						.getIdAl(), respuesta.getPrecontrato().get(i)
						.getAlumno(), respuesta.getPrecontrato().get(i)
						.getIdDest(), respuesta.getPrecontrato().get(i)
						.getDestino(), respuesta.getPrecontrato().get(i)
						.isAceptado()));
				contenido_propuesta.put(cabecera_propuesta.get(i), p1);
			}
			cargarLista();
		}
			
		}
	}
	
	/**
	 * Clase que se encarga de almacenar los precontratos como aceptados
	 *
	 */
	private class aTaskAceptarSolicitud extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private GenericResult respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "aceptarSolicitud";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskAceptarSolicitud(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		/**
		 * Se recorren todas las propuestas examinando las que se han marcado para
		 * aceptar, los datos de éstas se mandarán a la función AceptarSolicitud
		 * 
		 */
		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < cabecera_propuesta.size(); i++) {
				for (int j = 0; j < contenido_propuesta.get(cabecera_propuesta.get(i)).size(); j++) {
					if (contenido_propuesta.get(cabecera_propuesta.get(i)).get(j).getAceptado()) {
						AceptarSolicitud(contenido_propuesta.get(cabecera_propuesta.get(i)).get(j).getIdAl(),contenido_propuesta.get(cabecera_propuesta.get(i)).get(j).getIdDestino());
					}
				}
			}
			return null;
		}
		/**
		 * Función que se encarga de guardar las solicitudes en la base de datos
		 * 
		 * @param idAlumno
		 *            El identificador del alumno que creó la propuesta
		 * @param idDestino
		 *            El identificador del destino al que desea marchar
		 */
		public Void AceptarSolicitud(int idAlumno,int idDestino) {

			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				int var = Integer.parseInt(session.getUserDetails().get(SessionManager.KEY_ID));	
				request.addProperty("idUsuario",idAlumno);
				request.addProperty("idDestino", idDestino);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aquí metemos la peticion en el "Sobre"

				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta que
				// se obtiene la respuesta
				if (envelope.getResponse() != null) {

					respuesta = new GenericResult(
							(SoapObject) envelope.getResponse());

					// Si hasta aquí todo ha ido bien, lo siguiente será
					// abrir la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context, "Destino Creado",
						// Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					} else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context,
						// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					}

					else {
						// Todo ha ido bien, mostramos un Toast
						System.out.println("Hola");
						// Toast t = Toast.makeText(context,
						// "Fallo en Conexion", Toast.LENGTH_SHORT);
						// t.show();

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

		/**
		 * Al finalizar, actualizamos la lista de precontratos pendientes
		 * 
		 */
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Actualizar();
		}
	}
}
