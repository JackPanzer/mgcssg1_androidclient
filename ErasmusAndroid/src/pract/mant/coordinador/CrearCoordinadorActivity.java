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
import android.widget.TextView;
import android.widget.Toast;

/**
 * En esta interfaz, un coordinador puede dar de alta a otro que no est� registrado
 * en el sistema proporcionando los datos requeridos
 *
 */
public class CrearCoordinadorActivity extends Activity {
	public SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_coordinador);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_coordinador, menu);
		return true;
	}

	/**
	 * Funci�n para el bot�n Volver, finaliza la actividad actual para volver
	 * a la anterior
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickVolver(View v){
		finish();
		
	}

	/**
	 * Funci�n para el bot�n Finalizar, finaliza la actividad actual para volver
	 * a la anterior cuando se consigue insertar un nuevo coordinador en la base
	 * de datos
	 * 
	 * @param v
	 *            Bot�n que env�a el evento
	 */
	public void clickFinalizar(View v){
		String nombre = ((EditText) findViewById(R.id.coordinador_nombre)).getText().toString();
		String apellidos = ((EditText) findViewById(R.id.coordinador_apellidos)).getText().toString();
		String nif = ((EditText) findViewById(R.id.coordinador_nif)).getText().toString();
		String direccion = ((EditText) findViewById(R.id.coordinador_direccion)).getText().toString();
		String poblacion = ((EditText) findViewById(R.id.coordinador_poblacion)).getText().toString();
		String nick = ((EditText) findViewById(R.id.coordinador_nick)).getText().toString();
		String password = ((EditText) findViewById(R.id.coordinador_password)).getText().toString();
		
		session.checkLogin();
		/* No se realiza ninguna acci�n si no est�n rellenos los campos
		 * obligatorios
		 */
		if (!nombre.equals("") && !apellidos.equals("") && !nif.equals("") && !nick.equals("") && !password.equals("")){
			aTaskCrearCoordinador atl = new aTaskCrearCoordinador(this, session, nombre, apellidos, nif, direccion, poblacion, nick, password);
			atl.execute();
			finish();
		}
	}

	/**
	 * Clase para almacenar el nuevo coordinador en la base de datos
	 *
	 */
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

		/**
		 * 
		 * @param _ctxt Contexto de la actividad
		 * @param _session Objeto para gestionar la informaci�n del coordinador
		 * @param nombre Nombre del nuevo coordinador
		 * @param apellidos Apellidos del nuevo coordinador
		 * @param nif n�mero de identificaci�n personal del nuevo coordinador
		 * @param direccion direcci�n del nuevo coordinador (opcional)
		 * @param poblacion poblaci�n del nuevo coordinador (opcional)
		 * @param nick nombre de la cuenta del nuevo coordinador
		 * @param password contrase�a de la cuenta del nuevo coordinador
		 */
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
				envelope.setOutputSoapObject(request); // Aqu� metemos la peticion en el "Sobre"

				/* Definimos un objeto transporte para dirigir el Sobre */
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

				// Con call se produce la llamada, y se espera (bloquea) hasta que
				// se obtiene la respuesta
				if (envelope.getResponse() != null) {
					respuesta = new GenericResult((SoapObject) envelope.getResponse());

					// Si hasta aqu� todo ha ido bien, lo siguiente ser� abrir la
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
