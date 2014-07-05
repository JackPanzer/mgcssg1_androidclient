package pract.mant.alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.TareasAsincronas.SessionManager;
import pract.mant.adaptadores_alumno.Adaptadordestinos;
import pract.mant.adaptadores_coordinador.AdaptadorEliminaDestinosCoordinador;
import pract.mant.auxiliares_alumno.Destinos;
import pract.mant.auxiliares_coordinador.Nombre_Destino2;
import pract.mant.modelo.ArrayDestinos;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * La actividad DestinoAlumnoActivity listará todos los destinos para un alumno
 * en concreto.
 * 
 * @author Betanzos
 * 
 */

public class DestinoAlumnoActivity extends Activity {

	private ArrayList<Destinos> todos_destinos = new ArrayList<Destinos>();

	private ListView lstLista;

	private Adaptadordestinos adaptador; // Adaptador que extiende de ArrayAdapter y que
									// lista los destinos
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_alumno);

		lstLista = (ListView) findViewById(R.id.listView1);

		session = new SessionManager(getApplicationContext());

		/**
		 * Comprueba que el usuario este logueado Redirecciona a la pantalla de
		 * Login si no es así
		 * */

		session.checkLogin();

		// Llamada a Asintask
		aTaskVerDestinos atl = new aTaskVerDestinos(this, session);
		atl.execute();

		// Se le aplica un Listener donde ira lo que tiene que hacer en caso de
		// que sea pulsado

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.destino_alumno, menu);
		return true;
	}

	/**
	 * Una vez que se pulsa el botón volver se vuelve a la actividad
	 * Principal_Alumno
	 * 
	 * @param v
	 */

	public void clickVolver(View v) {
		finish();

	}

	/**
	 * Se accede a la actividad DestinoAsignaturaActivity en la cual se listan
	 * las asignaturas disponibles para los destinos que ha elegido el alumno.
	 * 
	 * @param v
	 */

	public void clickAceptar(View v) {

		aTaskEnviarDestinos atl = new aTaskEnviarDestinos(this, session);
		atl.execute();

	}

	// Esta función debe cargar los datos del servidor
	public void cargarLista() {

		adaptador = new Adaptadordestinos(this, todos_destinos); // Llamada al adaptador que listará
																	// los destinos dentro del listview

		lstLista.setAdapter(adaptador);

		lstLista.setDividerHeight(3);

	}

	/**
	 * La tarea asincrona se encargará de hacer una consulta a la base de datos
	 * para consultar los destinos disponibles para ese alumno en concreto.
	 * 
	 * @author Betanzos
	 * 
	 */

	private class aTaskVerDestinos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "consultarDestinos";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskVerDestinos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

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

					respuesta = new ArrayDestinos(
							(SoapObject) envelope.getResponse());

					// Si hasta aquí todo ha ido bien, lo siguiente será abrir
					// la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast
						// Toast t = Toast.makeText(context, "Destino Creado",
						// Toast.LENGTH_SHORT);
						// t.show();

					} else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						// Toast t = Toast.makeText(context,
						// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						// t.show();

					}

					else if (respuesta.getErrno() == -3) {
						// Todo ha ido bien, mostramos un Toast

					} else {
						// Todo ha ido bien, mostramos un Toast
						// Toast t = Toast.makeText(context,
						// "Fallo en Conexion", Toast.LENGTH_SHORT);
						// t.show();

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
		 *En el onPostExecute la respuesta la cargamos en una clase auxiliar
		 *que luego usaremos para mostrarlo en nuestro layout.
		 */
		
		@Override
		protected void onPostExecute(Void result) {
			
			for (int i = 0; i < respuesta.getDestinos().size(); i++) {
				todos_destinos.add(new Destinos(respuesta.getDestinos().get(i)
						.getId(), respuesta.getDestinos().get(i).getNombre(),
						false));
			}

			cargarLista();
		}

	}

	/**
	 * La tarea asincrona se encargará de hacer una consulta a la base de datos
	 * para añadir los destinos que el alumno ha elegido.
	 * 
	 * @author Betanzos
	 * 
	 */

	private class aTaskEnviarDestinos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "crearSolicitud";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskEnviarDestinos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;

		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				for (int i = 0; i < todos_destinos.size(); i++) {
					/* Conectando ... */
					SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

					/* Indicamos parametros */
					request.addProperty(
							"idAlumno",
							Integer.parseInt(session.getUserDetails().get(
									SessionManager.KEY_ID)));

					request.addProperty("idDestino", todos_destinos.get(i)
							.getId());

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
					transporte.call(SOAP_ACTION, envelope); // Lanzamos la
															// llamada

					// Con call se produce la llamada, y se espera (bloquea)
					// hasta
					// que
					// se obtiene la respuesta
					// SoapPrimitive response =
					// (SoapPrimitive)envelope.getResponse();
					if (envelope.getResponse() != null) {

						respuesta = new ArrayDestinos(
								(SoapObject) envelope.getResponse());

						// Si hasta aquí todo ha ido bien, lo siguiente será
						// abrir la
						// nueva interfaz
						if (respuesta.getErrno() == 0) {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Destinos Guardados", Toast.LENGTH_SHORT);
							// t.show();

						} else if (respuesta.getErrno() == -2) {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
							// t.show();

						}

						else {
							// Todo ha ido bien, mostramos un Toast
							// Toast t = Toast.makeText(context,
							// "Fallo en Conexion", Toast.LENGTH_SHORT);
							// t.show();

						}

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
		 * Una vez terminemos de realizar las consultas se ejecutaría onPostExecute
		 * cargando la siguiente Activity que sería DestinoAsignaturaActivity
		 */

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Intent act = new Intent(context, DestinoAsignaturaActivity.class);
			startActivity(act);
		}

	}

}
