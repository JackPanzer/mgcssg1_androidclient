package pract.mant.erasmusandroid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pract.mant.alumno.PrincipalAlumno;
import pract.mant.coordinador.CrearAlumnoActivity;
import pract.mant.coordinador.PrincipalCoordinadorActivity;
import pract.mant.modelo.ComplexUsuario;

import com.example.erasmusandroid.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Esta es nuestra actividad principal, desde está actividad
 * después de saber el rol del usuario que se intenta loguear 
 * pasaremos a una actividad u otra.
 * @author Betanzos
 *
 */

public class MainActivity extends Activity {
	
public SessionManager session; //SESSION OBJECT

	
	@Override
	/**
	 * La funcion onCreate llamará a la clase SessionManager
	 * que contralará nuestra sesión de usuario.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        session = new SessionManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Después de hacer click en el botón salir
	 * saldremos de la aplicación.
	 * @param v contiene la vista actual
	 */
	public void clickSalir(View v){
		finish();
		
	}
	
	/**
	 * Después de hacer click sobre el botón Logín y habiendo añadido
	 * nuestro respectivo usuario y contraseña, pasaremos a nuestra actividad
	 * correspondiente que puede ser la de coordinador o la del alumno.
	 * @param v
	 */
	public void clickLogin(View v){
		//Creamos las variables nick y pass que recogerán los datos de entrada 
		//de la activity y que pasaremos a nuestra tarea asíncrona aTaskLogin.
		String nick = ((EditText) findViewById(R.id.am_usuario)).getText().toString();
		String pass = ((EditText)findViewById(R.id.am_pass)).getText().toString();
		
		aTaskLogin atl = new aTaskLogin(this, session, nick, pass);
		atl.execute();
	}
	
	public void clickCrearAlumno(View v){
		Intent act = new Intent(this, CrearAlumnoActivity.class);
		this.startActivity(act);
	}
	
	/**
	 * Tarea Asincrona que se encarga de loguear a los usuarios.
	 * @author Betanzos
	 *
	 */
	private class aTaskLogin extends AsyncTask <Void, Void, Void>{

		private String nick;
		private String password;
		private ComplexUsuario respuesta;
		private Activity context;
		
		private SessionManager session; //SESSION OBJECT
		
		
		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "loginUsuario";
		final String SOAP_ACTION = "urn:Erasmus";
		
		public aTaskLogin(Activity context, SessionManager _session, String _nick, String _password){
			this.nick = _nick;
			this.password = _password;
			this.context = context;
			session = _session;
		}
		
		/**
		 * Llamada a la función remota que se encargará de devolver
		 * nuestro rol. Dependiendo el rol que devuelva se lanzará una
		 * actividad u otra, o en caso de corresponder a ningún rol y no
		 * lanzará nada.
		 */
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				/* Conectando ...*/
				SoapObject request= new SoapObject(NAMESPACE, METHOD_NAME);
				
				/* Indicamos parametros */
				request.addProperty("nick", nick);
				request.addProperty("passwd", password);
				
				/* Creamos un envelop <Sobre> */
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request); //Aquí metemos la peticion en el "Sobre"
				
				/* Definimos un objeto transporte para dirigir el Sobre*/
				HttpTransportSE transporte = new HttpTransportSE(URL);
				transporte.debug = true;
				transporte.call(SOAP_ACTION, envelope); //Lanzamos la llamada
				
				// Con call se produce la llamada, y se espera (bloquea) hasta que se obtiene la respuesta
				//SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				if (envelope.getResponse() != null){
					respuesta = new ComplexUsuario((SoapObject)envelope.getResponse());
					
					//Si hasta aquí todo ha ido bien, lo siguiente será abrir la nueva interfaz
					if(respuesta.getErrno() == 0){
						//Todo ha ido bien, hay que cargar la interfaz
						
						/**
						 * Creamos la sesion					
						 * */
						session.createLoginSession(respuesta);
						
						//en base al rol
						Intent act;
						switch(respuesta.getRol()){
						case 1:
							act = new Intent(this.context, PrincipalAlumno.class);
							this.context.startActivity(act);
							break;
						case 2:
							act = new Intent(this.context, PrincipalCoordinadorActivity.class);
							this.context.startActivity(act);
							break;
						default:
							//Rol desconocido, posible error en el servidor/cliente
							break;
						}
					}
					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

	}

	

}
