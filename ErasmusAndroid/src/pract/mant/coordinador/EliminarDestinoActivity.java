package pract.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.adaptadores_coordinador.AdaptadorEliminaDestinosCoordinador;
import pract.mant.adaptadores_coordinador.AdaptadorModificarDestinosCoordinador;
import pract.mant.auxiliares_coordinador.ModificarDestinos;
import pract.mant.auxiliares_coordinador.EliminarDestino;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayDestinos;

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
 * En esta interfaz, se pueden borrar los destinos que se consideren
 * innecesarios del sistema
 *
 */
public class EliminarDestinoActivity extends Activity {

	AdaptadorEliminaDestinosCoordinador adaptador_destinos;
	ExpandableListView lista_expandible;
	List<String> cabecera_lista;// contenido de la cabecera, puede ser una numeraci�n
	HashMap<String, List<EliminarDestino>> contenido_lista;// contiene los nombre de los destinos
	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_destino);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));

		aTaskEliminarDestinos atl = new aTaskEliminarDestinos(this,
				session);
		atl.execute();

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.eliminar_destino, menu);
		return true;
	}

	/**
	 * Funci�n para el bot�n Volver, finaliza la actividad actual para volver
	 * a la anterior
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickVolver(View v) {
		finish();

	}

	/**
	 * Funci�n para el bot�n Finalizar, finaliza la actividad actual para volver
	 * a la anterior cuando se consigue insertar un nuevo destino en la base de
	 * datos
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickActualizar(View v) {
		aTaskEliminarDestinos atl = new aTaskEliminarDestinos(this,
				session);
		atl.execute();

	}

	/**
	 * Carga la informaci�n contenida en las estructuras de datos de la clase en
	 * la interfaz para su tratamiento
	 * 
	 */
	public void cargarLista() {
		
		lista_expandible = (ExpandableListView) findViewById(R.id.expandableListView4);

		// carga los datos de la base de datos

		adaptador_destinos = new AdaptadorEliminaDestinosCoordinador(this,cabecera_lista, contenido_lista);

		lista_expandible.setAdapter(adaptador_destinos);

	}

	/**
	 * Clase para eliminar el destino en la base de datos
	 *
	 */
	private class aTaskEliminarDestinos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerDestinos";
		final String SOAP_ACTION = "urn:Erasmus";

		/**
		 * 
		 * @param _ctxt Contexto de la actividad
		 * @param _session Objeto para gestionar la informaci�n del alumno
		 */
		public aTaskEliminarDestinos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				request.addProperty("entero", 1);

				/* Creamos un envelop <Sobre> */
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aqu� metemos la peticion en el "Sobre"

				/* Definimos un objeto transporte para dirigir el Sobre */
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta
				// que se obtiene la respuesta
				if (envelope.getResponse() != null) {

					respuesta = new ArrayDestinos(
							(SoapObject) envelope.getResponse());

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
		 * Carga cada destino que puede ser eliminado en un array,
		 * posteriormente se llama a la funci�n cargarLista para
		 * ajustarlo a c�mo se mostrar� el listado
		 * 
		 */
		@Override
		protected void onPostExecute(Void result) {

			cabecera_lista = new ArrayList<String>();
			contenido_lista = new HashMap<String, List<EliminarDestino>>();

			for (int i = 0; i < respuesta.getDestinos().size(); i++) {
				cabecera_lista.add("Destino " + (i + 1));
				List<EliminarDestino> d = new ArrayList<EliminarDestino>();
				d.add(new EliminarDestino(respuesta.getDestinos().get(i)
						.getNombre(),respuesta.getDestinos().get(i).getId(),false));
				contenido_lista.put(cabecera_lista.get(i), d);
			}
			cargarLista();
		}

	}
}
