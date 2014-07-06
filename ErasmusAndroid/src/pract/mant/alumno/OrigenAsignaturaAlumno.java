package pract.mant.alumno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import pract.mant.adaptadores_alumno.AdaptadorAsignaturasLocales;
import pract.mant.auxiliares_alumno.AsignaturaLocal;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayAsignaturas;
import pract.mant.modelo.ArrayDestinos;
import pract.mant.modelo.ComplexAsignatura;
import pract.mant.modelo.GenericResult;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.erasmusandroid.R;

/**
 * Interfaz de usuario donde el alumno puede seleccionar las asignaturas que
 * está impartiendo en la actualidad y si quiere convalidarlas a la hora de
 * solicitar una beca ERASMUS
 * 
 */
public class OrigenAsignaturaAlumno extends Activity {

	private ArrayAsignaturas respuestaSoap;
	private List<AsignaturaLocal> asignaturas;
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_origen_asignatura_alumno);

		session = new SessionManager(getApplicationContext());
		aTaskObtenerAsignaturas hilo = new aTaskObtenerAsignaturas(session);

		hilo.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.origen_asignatura_alumno, menu);
		return true;
	}

	/**
	 * Carga la información contenida en las estructuras de datos de la clase en
	 * la interfaz para su tratamiento
	 * 
	 */
	public void cargarLista() {
		if (asignaturas != null) {

			ListView lstLista;
			lstLista = (ListView) findViewById(R.id.activity_origen_asignatura_listview);

			AdaptadorAsignaturasLocales adaptador = new AdaptadorAsignaturasLocales(
					this, asignaturas);
			lstLista.setAdapter(adaptador);

			lstLista.setDividerHeight(3);
		}
	}

	/**
	 * Evento que se lanza cuando el usuario pulsa sobre el botón volver para
	 * retroceder a la pantalla anterior.
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void volver(View v) {
		this.finish();
	}

	/**
	 * Evento que se lanza cuando el usuario pulsa sobre el botón finalizar para
	 * enviar los datos a su matrícula.
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	public void finalizar(View v) {
		aTaskMatricularAsignaturas hilo = new aTaskMatricularAsignaturas(
				session);

		hilo.execute();
	}

	/**
	 * Clase para obtener la lista de asignaturas a las que un alumno se puede
	 * matricular en base a su titulación
	 * 
	 */
	private class aTaskObtenerAsignaturas extends AsyncTask<Void, Void, Void> {

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerAsignaturasParaMatricular";
		final String SOAP_ACTION = "urn:Erasmus";

		private SessionManager session;

		/**
		 * Constructor de la clase. Se inicializa en él la variable para obtener
		 * datos del alumno.
		 * 
		 * @param session
		 *            Objeto para gestionar la información del alumno.
		 */
		public aTaskObtenerAsignaturas(SessionManager session) {
			this.session = session;
		}

		private ArrayAsignaturas obtenerAsignaturas() throws IOException,
				XmlPullParserException {

			ArrayAsignaturas retorno = null;

			/* Conectando ... */
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			/* Indicamos parametros */
			request.addProperty(
					"idAlumno",
					Integer.parseInt(session.getUserDetails().get(
							SessionManager.KEY_ID)));

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aquí metemos la
													// peticion
													// en el "Sobre"

			/* Definimos un objeto transporte para dirigir el Sobre */
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

			// Con call se produce la llamada, y se espera (bloquea) hasta
			// que
			// se obtiene la respuesta
			// SoapPrimitive response =
			// (SoapPrimitive)envelope.getResponse();
			if (envelope.getResponse() != null) {

				SoapObject arrayAsigs = (SoapObject) envelope.getResponse();
				retorno = new ArrayAsignaturas(arrayAsigs);

			}

			return retorno;

		}

		@Override
		protected Void doInBackground(Void... params) {

			try {

				ArrayAsignaturas respuesta = obtenerAsignaturas();
				respuestaSoap = respuesta;

				// En este punto tenemos ya todas las asignaturas a las que el
				// alumno puede acceder
				// así que se procede a cargarlas en un formato apropiado para
				// el array adapter

				asignaturas = new ArrayList<AsignaturaLocal>();

				for (ComplexAsignatura actual : respuesta.getAsignaturas()) {

					String _nombre = actual.getNombre();
					boolean _estado = false;
					int _creditos = actual.getCreditos();
					int _id = actual.getIdAsig();
					String _coordinador = actual.getCoordinador();

					// Agregando asignatura para mostrar
					AsignaturaLocal wrapper = new AsignaturaLocal(_nombre,
							_estado, _creditos, _id, _coordinador, false);
					asignaturas.add(wrapper);
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			cargarLista();
		}

	}

	private class aTaskMatricularAsignaturas extends
			AsyncTask<Void, Void, Void> {

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "matricularAsignatura";
		final String SOAP_ACTION = "urn:Erasmus";

		private SessionManager session;

		/**
		 * Constructor de la clase. Se inicializa en él la variable para obtener
		 * datos del alumno.
		 * 
		 * @param session
		 *            Objeto para gestionar la información del alumno.
		 */
		public aTaskMatricularAsignaturas(SessionManager session) {
			this.session = session;
		}

		private GenericResult guardarAsignatura(int idAsignatura,
				int quiereConval) throws IOException, XmlPullParserException {

			GenericResult retorno = null;

			/* Conectando ... */
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			/* Indicamos parametros */
			request.addProperty(
					"idAlumno",
					Integer.parseInt(session.getUserDetails().get(
							SessionManager.KEY_ID)));

			request.addProperty("idAsignatura", idAsignatura);

			request.addProperty("quiereConval", quiereConval);

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aquí metemos la
													// peticion
													// en el "Sobre"

			/* Definimos un objeto transporte para dirigir el Sobre */
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

			// Con call se produce la llamada, y se espera (bloquea) hasta
			// que
			// se obtiene la respuesta
			// SoapPrimitive response =
			// (SoapPrimitive)envelope.getResponse();
			if (envelope.getResponse() != null) {

				SoapObject resultado = (SoapObject) envelope.getResponse();
				retorno = new GenericResult(resultado);

			}

			return retorno;

		}

		@Override
		protected Void doInBackground(Void... params) {

			GenericResult respuesta = null;

			try {

				// guardamos los cambios que ha hecho el
				// alumno

				for (int i = 0; i < asignaturas.size(); i++) {
					if (asignaturas.get(i).isChekeado()) {
						int aux=0;
						if(asignaturas.get(i).isConvalidar()){
							aux =1;
						}
						respuesta = guardarAsignatura(asignaturas.get(i).getId(), aux);//FORZANDO CONVALIDAR

						// Si hasta aquí todo ha ido bien, lo siguiente será
						// abrir
						// la
						// nueva interfaz
						if (respuesta.getErrno() == 0) {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Asignatura Matriculada",
							// Toast.LENGTH_SHORT);
							// t.show();

						} else if (respuesta.getErrno() == -2) {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
							// t.show();

						} else {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Fallo en Conexion", Toast.LENGTH_SHORT);
							// t.show();

						}
					}
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			finish();
		}

	}
}
