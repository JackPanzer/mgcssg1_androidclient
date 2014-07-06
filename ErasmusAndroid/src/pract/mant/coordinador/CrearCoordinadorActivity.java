package pract.mant.coordinador;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.GenericResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CrearCoordinadorActivity extends Activity {
	public SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_coordinador);
		session = new SessionManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_coordinador, menu);
		return true;
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickFinalizar(View v){
		String nombre = ((EditText) findViewById(R.id.coordinador_nombre)).getText().toString();
		String apellidos = ((EditText) findViewById(R.id.coordinador_apellidos)).getText().toString();
		String nif = ((EditText) findViewById(R.id.coordinador_nif)).getText().toString();
		String direccion = ((EditText) findViewById(R.id.coordinador_direccion)).getText().toString();
		String poblacion = ((EditText) findViewById(R.id.coordinador_poblacion)).getText().toString();
		String nick = ((EditText) findViewById(R.id.coordinador_nick)).getText().toString();
		String password = ((EditText) findViewById(R.id.coordinador_password)).getText().toString();
		
		session.checkLogin();
		if (!nombre.equals("") && !apellidos.equals("") && !nif.equals("") && !nick.equals("") && !password.equals("")){
			aTaskCrearCoordinador atl = new aTaskCrearCoordinador(this, session, nombre, apellidos, nif, direccion, poblacion, nick, password);
			atl.execute();
			finish();
		}
	}
	
	private class aTaskCrearCoordinador extends AsyncTask<Void, Void, Void> {
		private GenericResult respuesta;
		private SessionManager session;
		private Activity context;
		private String nombre;
		private String apellidos;
		private String nif;
		private String direccion;
		private String poblacion;
		private String nick;
		private String password;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "crearCoordinador";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskCrearCoordinador(Activity _ctxt, SessionManager _session,
				String nombre, String apellidos, String nif, String direccion, String poblacion,
				String nick, String password) {

			this.context = _ctxt;
			this.session = _session;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.nif = nif;
			this.direccion = direccion;
			this.poblacion = poblacion;
			this.nick = nick;
			this.password = password;
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				request.addProperty("nombre", nombre);
				request.addProperty("apellidos", apellidos);
				request.addProperty("nif", nif);
				request.addProperty("direccion", direccion);
				request.addProperty("poblacion", poblacion);
				request.addProperty("nick", nick);
				request.addProperty("passwd", password);

				/* Creamos un envelop <Sobre> */
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aquí metemos la peticion en el "Sobre"

				/* Definimos un objeto transporte para dirigir el Sobre */
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta que
				// se obtiene la respuesta
				// SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				if (envelope.getResponse() != null) {
					respuesta = new GenericResult((SoapObject) envelope.getResponse());

					// Si hasta aquí todo ha ido bien, lo siguiente será abrir la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast

						//Toast t = Toast.makeText(context, "Destino Creado", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast

						//Toast t = Toast.makeText(context, "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						//t.show();

						// en base al rol
						
					}
					else{
						// Todo ha ido bien, mostramos un Toast

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

	}
	
}
