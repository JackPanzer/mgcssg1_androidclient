package pract.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.adaptadores_alumno.AdaptadorDestinosAsignaturas;
import pract.mant.adaptadores_coordinador.AdaptadorModificarDestinosCoordinador;
import pract.mant.auxiliares_coordinador.ModificarDestinos;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayDestinos;

import com.example.erasmusandroid.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

//Se debe a�adir una funci�n que almacene los cambios 
public class ModificarDestinosActivity extends Activity {

	AdaptadorModificarDestinosCoordinador adaptador_destinos;
	ExpandableListView lista_expandible;
	protected List<String> cabecera_lista;
	protected HashMap<String, List<ModificarDestinos>> contenido_lista;
	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_destinos);

		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));

		// Llamada a Asintask
		aTaskConsultarTodosDestinos atl = new aTaskConsultarTodosDestinos(this,
				session);
		atl.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.modificar_destinos, menu);
		return true;
	}

	public void clickVolver(View v) {
		finish();

	}

	public void clickFinalizar(View v) {
		finish();

	}

	public void cargarLista() {
		lista_expandible = (ExpandableListView) findViewById(R.id.expandableListView3);

		adaptador_destinos = new AdaptadorModificarDestinosCoordinador(this,
				cabecera_lista, contenido_lista);

		lista_expandible.setAdapter(adaptador_destinos);


	}

	private class aTaskConsultarTodosDestinos extends
			AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerDestinos";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskConsultarTodosDestinos(Activity _ctxt,
				SessionManager _session) {

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
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aqu� metemos la
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

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			cabecera_lista = new ArrayList<String>();
			contenido_lista = new HashMap<String, List<ModificarDestinos>>();

			for (int i = 0; i < respuesta.getDestinos().size(); i++) {
				cabecera_lista.add("Destino " + (i + 1));
				List<ModificarDestinos> d = new ArrayList<ModificarDestinos>();
				d.add(new ModificarDestinos(respuesta.getDestinos().get(i)
						.getNombre(), respuesta.getDestinos().get(i).getPais(),
						respuesta.getDestinos().get(i).getIdioma(), respuesta
								.getDestinos().get(i).isDisponible(), respuesta
								.getDestinos().get(i).getNumplazas(), respuesta
								.getDestinos().get(i).getNvlrequerido(),
						respuesta.getDestinos().get(i).getId(), respuesta
								.getDestinos().get(i).getIdpais(), respuesta
								.getDestinos().get(i).getId_idioma(), respuesta
								.getDestinos().get(i).getIdnvlrequerido()));
				contenido_lista.put(cabecera_lista.get(i), d);
			}

			/*
			 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
			 * idIdioma; this.idNivel = idNivel;
			 */

			cargarLista();
		}

	}

}
