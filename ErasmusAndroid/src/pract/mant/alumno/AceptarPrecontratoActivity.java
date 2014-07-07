package pract.mant.alumno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.adaptadores_alumno.AdaptadorPrecontrado;
import pract.mant.adaptadores_coordinador.AdaptadorPropuesta;
import pract.mant.auxiliares_alumno.Precontrato;
import pract.mant.auxiliares_coordinador.Propuesta;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayPrecontrato;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Interfaz de usuario donde el alumno puede visualizar los precontratos que
 * ha realizado para la beca ERASMUS
 * 
 */
public class AceptarPrecontratoActivity extends Activity {
	// Adaptador que extiende de BaseExpandableListAdapter y que lista los precontratos
	private AdaptadorPrecontrado adaptador_precontrato; 
	
	private ExpandableListView expandible_precontrato;
	
	private List<String> cabecera_precontrato; //Contiene la cabecera del precontrato
	
	//Tabla hash que relaciona el id del Precontrato con el nombre del alumno y el destino
	private HashMap<String, List<Precontrato>> contenido_precontrato;
	
	//contiene nuestra sesion de usuario
	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_precontrato);

		session = new SessionManager(getApplicationContext());

		/**
		 * Comprueba que el usuario este logueado Redirecciona a la pantalla de
		 * Login si no es así
		 * */
		
		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));

        // Llamada a Asintask
		aTaskObtenerPrecontratos atl = new aTaskObtenerPrecontratos(this,
				session);
		atl.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aceptar_precontrato, menu);
		return true;
	}

	/**
	 * Evento que se lanza cuando el usuario pulsa sobre el botón volver para
	 * retroceder a la pantalla anterior.
	 * 
	 * @param v
	 *            Botón que envía el evento
	 */
	
	public void clickVolver(View v) {
		finish();

	}

	/**
	 * Carga la información contenida en las estructuras de datos de la clase en
	 * la interfaz para su tratamiento
	 * 
	 */
	
	public void cargarLista() {

		expandible_precontrato = (ExpandableListView) findViewById(R.id.expandableListView2);
		// Añado los datos a los arrays
		// LLamo al adaptador que cargará los datos en el layout
		adaptador_precontrato = new AdaptadorPrecontrado(this, cabecera_precontrato, contenido_precontrato);

		// Se carga en la aplicancion
		expandible_precontrato.setAdapter(adaptador_precontrato);

	}

	/**
	 * Clase para obtener la lista de precontratos que un alumno ha rellenado
	 * 
	 */

	private class aTaskObtenerPrecontratos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // contiene nuestra sesion
		private ArrayPrecontrato respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerPrecontratosDeAlumno";
		final String SOAP_ACTION = "urn:Erasmus";
		
		/**
		 * 
		 * @param _ctxt Contexto de la actividad
		 * @param _session Objeto para gestionar la información del alumno.
		 */
		
		public aTaskObtenerPrecontratos(Activity _ctxt, SessionManager _session) {

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
				envelope.setOutputSoapObject(request); 
				

				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta
				// que
				// se obtiene la respuesta
				if (envelope.getResponse() != null) {
					SoapObject aux = (SoapObject) envelope.getResponse();

					respuesta = new ArrayPrecontrato(aux);

					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast
						// Toast t = Toast.makeText(context, "Listado de Precontratos",
						// Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					} else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast
						// Toast t = Toast.makeText(context,
						// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					}

					else {
						// Todo ha ido bien, mostramos un Toast
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

		@Override
		protected void onPostExecute(Void result) {
			
			// Ahora cargamos en nuestra cabecera_precontrato y contenido_precontrato
			//los datos que hemos recibido de la consulta
			
			if (respuesta != null) {
				cabecera_precontrato = new ArrayList<String>();
				contenido_precontrato = new HashMap<String, List<Precontrato>>();
				for (int i = 0; i < respuesta.getPrecontrato().size(); i++) {
					cabecera_precontrato.add("Precontrato " + (i + 1));
					List<Precontrato> p1 = new ArrayList<Precontrato>();
					p1.add(new Precontrato(respuesta.getPrecontrato().get(i)
							.getIdAl(), respuesta.getPrecontrato().get(i)
							.getAlumno(), respuesta.getPrecontrato().get(i)
							.getIdDest(), respuesta.getPrecontrato().get(i)
							.getDestino(), respuesta.getPrecontrato().get(i)
							.isAceptado()));
					contenido_precontrato.put(cabecera_precontrato.get(i), p1);
				}
				cargarLista();
			}

		}
	}

}
