package pract.mant.coordinador;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.GenericResult;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.widget.EditText;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

public class CrearAlumnoActivity extends Activity {

	// Session Manager Class
	public SessionManager session;
	private int titulacion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_alumno);
		Spinner sp = (Spinner) findViewById(R.id.spinner_titulacion);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.titulaciones, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				titulacion = position + 1;
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		session = new SessionManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.crear_alumno, menu);
		return true;
	}

	public void clickVolver(View v) {
		finish();

	}

	public void clickFinalizar(View v) {
		String nombre = ((EditText) findViewById(R.id.alumno_nombre)).getText()
				.toString();
		String apellidos = ((EditText) findViewById(R.id.alumno_apellidos))
				.getText().toString();
		String nif = ((EditText) findViewById(R.id.alumno_nif)).getText()
				.toString();
		String direccion = ((EditText) findViewById(R.id.alumno_direccion))
				.getText().toString();
		String poblacion = ((EditText) findViewById(R.id.alumno_poblacion))
				.getText().toString();
		String nick = ((EditText) findViewById(R.id.alumno_nick)).getText()
				.toString();
		String password = ((EditText) findViewById(R.id.alumno_password))
				.getText().toString();

		session.checkLogin();
		if (!nombre.equals("") && !apellidos.equals("") && !nif.equals("")
				&& !nick.equals("") && !password.equals("")) {
			aTaskCrearAlumno atl = new aTaskCrearAlumno(this, session, nombre,
					apellidos, nif, direccion, poblacion, nick, password,
					titulacion);
			atl.execute();
			finish();
		}
	}

	private class aTaskCrearAlumno extends AsyncTask<Void, Void, Void> {
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
		private int titulacion;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "crearAlumno";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskCrearAlumno(Activity _ctxt, SessionManager _session,
				String nombre, String apellidos, String nif, String direccion,
				String poblacion, String nick, String password, int titulacion) {

			this.context = _ctxt;
			this.session = _session;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.nif = nif;
			this.direccion = direccion;
			this.poblacion = poblacion;
			this.nick = nick;
			this.password = password;
			this.titulacion = titulacion;
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
				request.addProperty("titulacion", titulacion);

				/* Creamos un envelop <Sobre> */
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); // Aquí metemos la
														// peticion en el
														// "Sobre"

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
					respuesta = new GenericResult(
							(SoapObject) envelope.getResponse());

					// Si hasta aquí todo ha ido bien, lo siguiente será abrir
					// la
					// nueva interfaz
					if (respuesta.getErrno() == 0) {
						// Todo ha ido bien, mostramos un Toast

						// Toast t = Toast.makeText(context, "Destino Creado",
						// Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					} else if (respuesta.getErrno() == -2) {
						// Todo ha ido bien, mostramos un Toast

						// Toast t = Toast.makeText(context,
						// "Sentencia Incorrecta", Toast.LENGTH_SHORT);
						// t.show();

						// en base al rol

					} else {
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

	}

}
