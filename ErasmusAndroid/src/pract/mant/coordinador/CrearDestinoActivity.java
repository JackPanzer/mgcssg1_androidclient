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

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// En esta clase habría que crear una función que guardará los datos
//que introduce el coordinador en la base de datos

public class CrearDestinoActivity extends Activity {
	
	// Session Manager Class
    public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_destino);
		
		session = new SessionManager(getApplicationContext());

		session.checkLogin();
		
		TextView t = (TextView) findViewById(R.id.id_logueado);
        t.setText(session.getUserDetails().get(SessionManager.KEY_NAME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.crear_destino, menu);
		return true;
		
	}
	
	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickFinalizar(View v){
		String nombre = ((EditText) findViewById(R.id.acd_nombre)).getText().toString();
		int pais = Integer.parseInt(((EditText) findViewById(R.id.acd_pais)).getText().toString());
		int idioma = Integer.parseInt(((EditText) findViewById(R.id.acd_idioma)).getText().toString());
		int disponible = Integer.parseInt(((EditText) findViewById(R.id.acd_disponible)).getText().toString());
		int plazas = Integer.parseInt(((EditText) findViewById(R.id.acd_plazas)).getText().toString());
		int nivel = Integer.parseInt(((EditText) findViewById(R.id.acd_nivel)).getText().toString());
	
		session.checkLogin(); 
		if (!nombre.equals("")){
			aTaskCrearDestinos atl = new aTaskCrearDestinos(this, session,nombre,pais,idioma,disponible,plazas,nivel);
			atl.execute();
			finish();
		}
	}
	
	private class aTaskCrearDestinos extends AsyncTask<Void, Void, Void> {
		//Esto es un comentario
		// private int idUsu;
		private GenericResult respuesta;
		private SessionManager session; // SESSION OBJECT
		private Activity context;
		private String nombre;
		private int pais;
		private int idioma;
		private int disponible;
		private int plazas;
		private int nivel;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "crearDestino";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskCrearDestinos(Activity _ctxt, SessionManager _session,
				String nombre, int pais, int idioma, int disponible, int plazas,
				int nivel) {

			this.context = _ctxt;
			this.session = _session;
			this.nombre = nombre;
			this.pais = pais;
			this.idioma = idioma;
			this.disponible = disponible;
			this.plazas = plazas;
			this.nivel = nivel;
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			try {

				/* Conectando ... */
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				/* Indicamos parametros */
				request.addProperty("nombre", nombre);
				request.addProperty("idPais", pais);
				request.addProperty("idIdioma", idioma);
				request.addProperty("disponible", disponible);
				request.addProperty("numPlazas", plazas);
				request.addProperty("nvlRequerido", nivel);

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
