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
import android.widget.Toast;

//Esta activity previsualiza los precontratos
public class AceptarPrecontratoActivity extends Activity {

	AdaptadorPrecontrado adaptador_precontrato;
	ExpandableListView expandible_precontrato;
	List<String> cabecera_precontrato;
	HashMap<String, List<Precontrato>> contenido_precontrato;

	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aceptar_precontrato);

		session = new SessionManager(getApplicationContext());

		session.checkLogin();

		// Creo el objeto que maneja el expandible
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

	public void clickVolver(View v) {
		finish();

	}

	public void cargarLista() {

		// TODO Auto-generated method stub
		expandible_precontrato = (ExpandableListView) findViewById(R.id.expandableListView2);
		// Añado los datos a los arrays
		// LLamo al adaptador que cargará los datos en el layout
		adaptador_precontrato = new AdaptadorPrecontrado(this, cabecera_precontrato, contenido_precontrato);

		// Se carga en la aplicancion
		expandible_precontrato.setAdapter(adaptador_precontrato);

	}

	// funcion de prueba que será sustituida por consulta a la base de datos

	private class aTaskObtenerPrecontratos extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArrayPrecontrato respuesta;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "obtenerPrecontratosDeAlumno";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskObtenerPrecontratos(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				request.addProperty(
						"idAlumno",
						Integer.parseInt(session.getUserDetails().get(
								SessionManager.KEY_ID)));

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
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

		@Override
		protected void onPostExecute(Void result) {
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
