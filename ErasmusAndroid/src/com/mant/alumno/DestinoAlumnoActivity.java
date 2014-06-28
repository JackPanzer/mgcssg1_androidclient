package com.mant.alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.TareasAsincronas.SessionManager;
import com.mant.adaptadores_alumno.Adaptadordestinos;
import com.mant.adaptadores_coordinador.AdaptadorDestinosCoordinador2;
import com.mant.auxiliares_alumno.Destinos;
import com.mant.auxiliares_coordinador.Nombre_Destino2;
import com.mant.modelo.ArrayDestinos;

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

public class DestinoAlumnoActivity extends Activity {

	ArrayList<Destinos> todos_destinos = new ArrayList<Destinos>();

	ListView lstLista;

	Adaptadordestinos adaptador;
	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_alumno);

		lstLista = (ListView) findViewById(R.id.listView1);

		session = new SessionManager(getApplicationContext());

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

	public void clickVolver(View v) {
		finish();

	}

	public void clickAceptar(View v) {
		for (int i = 0; i < todos_destinos.size(); i++) {
			aTaskEnviarDestinos atl = new aTaskEnviarDestinos(this, session,todos_destinos.get(i));
			atl.execute();
		}

	}
	
	// Esta función debe cargar los datos del servidor
		public void cargarLista() {
			
			adaptador = new Adaptadordestinos(this, todos_destinos);

			lstLista.setAdapter(adaptador);

			lstLista.setDividerHeight(3);

		}

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
				request.addProperty("idAlumno",
						Integer.parseInt
							(session.getUserDetails().get(SessionManager.KEY_ID)));

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

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			for (int i = 0; i < respuesta.getDestinos().size(); i++) {
				todos_destinos.add(new Destinos(respuesta.getDestinos().get(i).getId(),respuesta.getDestinos().get(i).getNombre(), false));	
			}
			
			cargarLista();
			}
			
			/*
			 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
			 * idIdioma; this.idNivel = idNivel;
			 */
		
	}
	
	private class aTaskEnviarDestinos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayDestinos respuesta;
		private Activity context;
		private Destinos destino;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "crearSolicitud";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskEnviarDestinos(Activity _ctxt, SessionManager _session, Destinos destino) {

			this.context = _ctxt;
			this.session = _session;
			this.destino=destino;
			
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				request.addProperty("idAlumno",
						Integer.parseInt
							(session.getUserDetails().get(SessionManager.KEY_ID)));
				
				request.addProperty("idDestino",destino.getId());

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

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Intent act = new Intent(context, DestinoAsignaturaActivity.class);
			startActivity(act);
			}
			
			/*
			 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
			 * idIdioma; this.idNivel = idNivel;
			 */

			

	}

}
