package com.mant.alumno;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
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
import com.mant.adaptadores_alumno.AdaptadorDestinosAsignaturas;
import com.mant.adaptadores_alumno.Adaptadordestinos;
import com.mant.auxiliares_alumno.Asignatura;
import com.mant.auxiliares_alumno.Destinos;
import com.mant.modelo.ArrayDestinos;
import com.mant.modelo.ArraySolicitudes;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DestinoAsignaturaActivity extends Activity {

	AdaptadorDestinosAsignaturas listAdapter;
    ExpandableListView expListView;
    List<String> NombreDestino;
    HashMap<String, List<Asignatura>> ListaAsignaturas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_asignatura);
		
		// get the listview
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destino_asignatura, menu);
		return true;
	}
	

	public void clickVolver(View v) {
		finish();
		}
	
	public void clickAceptar(View v) {
		Intent act = new Intent(this, AceptarPrecontratoActivity.class);
		startActivity(act);

	}
	
	public void cargarLista() {
		
		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		 
        listAdapter = new AdaptadorDestinosAsignaturas(this, NombreDestino, ListaAsignaturas);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);

	}
	
	private class aTaskConsultarSolicitudes extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArraySolicitudes respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "consultarSolicitudes";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskConsultarSolicitudes(Activity _ctxt, SessionManager _session) {

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
				
				request.addProperty("idDestino",-1);

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

					respuesta = new ArraySolicitudes(
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
			
			for (int i = 0; i < respuesta.getSolicitudes().size(); i++) {
				NombreDestino.add("Destino "+i+" : " +respuesta.getSolicitudes().get(i).getNomDestino());
			}
			
			
			}
			
			
		
			/*
			 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
			 * idIdioma; this.idNivel = idNivel;
			 */

			

	}
	
	private class aTaskobtenerAsignaturasSolicitables extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArraySolicitudes respuesta;
		private Activity context;
		private int idDestinos;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerAsignaturasSolicitables";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskobtenerAsignaturasSolicitables(Activity _ctxt, SessionManager _session, int idDestino) {

			this.context = _ctxt;
			this.session = _session;
			this.idDestinos=idDestino;
			
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
				
				request.addProperty("idDestino",idDestinos);

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

					respuesta = new ArraySolicitudes(
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
			
			for (int i = 0; i < respuesta.getSolicitudes().size(); i++) {
				NombreDestino.add("Destino "+i+" : " +respuesta.getSolicitudes().get(i).getNomDestino());
			}
			
			
			}
			
			
		
			/*
			 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
			 * idIdioma; this.idNivel = idNivel;
			 */

			

	}

}
