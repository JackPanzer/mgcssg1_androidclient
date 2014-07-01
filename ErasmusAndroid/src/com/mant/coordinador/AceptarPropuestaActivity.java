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
import com.mant.adaptadores_alumno.AdaptadorPrecontrado;
import com.mant.adaptadores_coordinador.AdaptadorPropuesta;
import com.mant.auxiliares_alumno.Asignatura2;
import com.mant.auxiliares_alumno.Precontrato;
import com.mant.auxiliares_coordinador.Nombre_Destino2;
import com.mant.auxiliares_coordinador.Propuesta;
import com.mant.modelo.ArrayDestinos;
import com.mant.modelo.ArrayPrecontrato;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class AceptarPropuestaActivity extends Activity {
	
	AdaptadorPropuesta adaptador_propuesta;
    ExpandableListView expandible_propuesta;
    List<String> cabecera_propuesta;//cabecera del expandible
    HashMap<String, List<Propuesta>> contenido_propuesta;//contenido del expandible

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_propuesta);
		
		//Creo el objeto que maneja el expandible
		expandible_propuesta = (ExpandableListView) findViewById(R.id.expandableListView5);
		//Añado los datos a los arrays
		preparar_datos();
		//LLamo al adaptador que cargará los datos en el layout
		adaptador_propuesta = new AdaptadorPropuesta(this, cabecera_propuesta, contenido_propuesta);
 
        //Se carga en la aplicancion
		expandible_propuesta.setAdapter(adaptador_propuesta);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.aceptar_propuesta, menu);
		return true;
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	//funcion de prueba que será sustituida por consulta a la base de datos
	private void preparar_datos() {
		cabecera_propuesta = new ArrayList<String>();
		contenido_propuesta = new HashMap<String, List<Propuesta>>();
 
        // Adding child data
		cabecera_propuesta.add("Propuesta 1");
		cabecera_propuesta.add("Propuesta 2");
		cabecera_propuesta.add("Propuesta 3");
		cabecera_propuesta.add("Propuesta 4");
 
        // Adding child data
        
		ArrayList<String> asignaturas = new ArrayList<String>();
		asignaturas.add("Asignatura 1");
		asignaturas.add("Asignatura 2");
		asignaturas.add("Asignatura 3");
		asignaturas.add("Asignatura 4");
		
        List<Propuesta> p1 = new ArrayList<Propuesta>();
        p1.add(new Propuesta("Jacinto Mata", "677234566", "Aeronautica", "Huelva", "B1", asignaturas));
        
        List<Propuesta> p2 = new ArrayList<Propuesta>();
        p2.add(new Propuesta("Anna Simon", "675346091", "Forestales", "Malaga", "A1", asignaturas));
        
        List<Propuesta> p3 = new ArrayList<Propuesta>();
        p3.add(new Propuesta("Conchita Wurst", "675434898", "Informatica", "Sevilla", "P1", asignaturas));
        
        List<Propuesta> p4 = new ArrayList<Propuesta>();
        p4.add(new Propuesta("Bruce Willis", "687570501", "Mecanica", "Jaen", "I1", asignaturas));
        
        
 
        contenido_propuesta.put(cabecera_propuesta.get(0), p1); // Header, Child data
        contenido_propuesta.put(cabecera_propuesta.get(1), p2);
        contenido_propuesta.put(cabecera_propuesta.get(2), p3);
        contenido_propuesta.put(cabecera_propuesta.get(3), p4);
		
	}
	
	private class aTaskObtenerPrecontratos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayPrecontrato respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerPrecontratos";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskObtenerPrecontratos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				
				request.addProperty("entero", 1);

				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aquí metemos la
														// peticion
														// en el "Sobre"

				
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta
				// que
				// se obtiene la respuesta
				// SoapPrimitive response =
				// (SoapPrimitive)envelope.getResponse();
				if (envelope.getResponse() != null) {

					respuesta = new ArrayPrecontrato(
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

			
		}

	}
	
	


}
