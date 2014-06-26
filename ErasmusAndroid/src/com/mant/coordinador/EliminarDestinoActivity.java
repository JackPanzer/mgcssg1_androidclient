package com.mant.coordinador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.TareasAsincronas.SessionManager;
import com.mant.adaptadores_coordinador.AdaptadorDestinosCoordinador;
import com.mant.adaptadores_coordinador.AdaptadorDestinosCoordinador2;
import com.mant.auxiliares_coordinador.Nombre_Destino;
import com.mant.auxiliares_coordinador.Nombre_Destino2;
import com.mant.modelo.ArrayDestinos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

//Hay que crear una funcion que despues de pulsar el boton elimine
//los destinos que se hayan elegido tras pulsar el check
public class EliminarDestinoActivity extends Activity {

	AdaptadorDestinosCoordinador2 adaptador_destinos;
	ExpandableListView lista_expandible;
	List<String> cabecera_lista;// contenido de la cabecera, puede ser una
								// numeracion
	HashMap<String, List<Nombre_Destino2>> contenido_lista;// contiene los
															// nombre de los
															// destinos
	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_destino);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();

		// Llamada a Asintask
		aTaskEliminarDestinos atl = new aTaskEliminarDestinos(this,
				session);
		atl.execute();

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.eliminar_destino, menu);
		return true;
	}

	public void clickVolver(View v) {
		finish();

	}

	public void clickFinalizar(View v) {
		finish();

	}

	// Esta función debe cargar los datos del servidor
	public void cargarLista() {
		
		lista_expandible = (ExpandableListView) findViewById(R.id.expandableListView4);

		// carga los datos de la base de datos

		adaptador_destinos = new AdaptadorDestinosCoordinador2(this,cabecera_lista, contenido_lista);

		lista_expandible.setAdapter(adaptador_destinos);

	}

	private class aTaskEliminarDestinos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerDestinos";
		final String SOAP_ACTION = "urn:Erasmus";

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
			contenido_lista = new HashMap<String, List<Nombre_Destino2>>();

			for (int i = 0; i < respuesta.getDestinos().size(); i++) {
				cabecera_lista.add("Destino " + (i + 1));
				List<Nombre_Destino2> d = new ArrayList<Nombre_Destino2>();
				d.add(new Nombre_Destino2(respuesta.getDestinos().get(i)
						.getNombre(),respuesta.getDestinos().get(i).getId(),false));
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
