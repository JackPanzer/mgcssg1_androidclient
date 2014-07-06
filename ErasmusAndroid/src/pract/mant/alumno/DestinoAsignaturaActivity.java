package pract.mant.alumno;

import java.io.IOException;
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
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import pract.mant.adaptadores_alumno.AdaptadorDestinosAsignaturas;
import pract.mant.adaptadores_alumno.AdaptadorDestinos;
import pract.mant.auxiliares_alumno.Asignatura;
import pract.mant.auxiliares_alumno.Destinos;
import pract.mant.auxiliares_coordinador.Propuesta;
import pract.mant.erasmusandroid.SessionManager;
import pract.mant.modelo.ArrayAsignaturasExt;
import pract.mant.modelo.ArrayDestinos;
import pract.mant.modelo.ArraySolicitudes;
import pract.mant.modelo.ComplexAsignaturaExt;
import pract.mant.modelo.ComplexSolicitud;
import pract.mant.modelo.GenericResult;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

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
	List<Integer> IdsDestinos;
	HashMap<String, List<Asignatura>> ListaAsignaturas;

	public SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_asignatura);

		session = new SessionManager(getApplicationContext());
		session.checkLogin();

		aTaskConsultarSolicitudes atl = new aTaskConsultarSolicitudes(this,
				session);
		atl.execute();

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
		
		aTaskEnviarAsignaturasPrecontrato atl = new aTaskEnviarAsignaturasPrecontrato(this,
				session);
		atl.execute();
		//Intent act = new Intent(this, AceptarPrecontratoActivity.class);
		//startActivity(act);

	}

	public void cargarLista() {

		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);

		listAdapter = new AdaptadorDestinosAsignaturas(this, NombreDestino,
				ListaAsignaturas);

		// setting list adapter
		expListView.setAdapter(listAdapter);

	}

	/**
	 * Permite la consulta de solicitudes que el alumno ha hecho entre sesiones
	 * 
	 */
	private class aTaskConsultarSolicitudes extends AsyncTask<Void, Void, Void> {

		private SessionManager session; // SESSION OBJECT
		private ArraySolicitudes arraySols;
		private List<ArrayAsignaturasExt> arrayAsigs;
		private Activity context;

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "consultarSolicitudes";
		final String METHOD_NAME2 = "obtenerAsignaturasSolicitables";
		final String SOAP_ACTION = "urn:Erasmus";

		public aTaskConsultarSolicitudes(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;

		}
		
		/**
		 * Carga las solicitudes en un Array declarado anteriormente
		 * para guardar las solicitudes echas por un alumno.
		 * 
		 * @throws IOException
		 * @throws XmlPullParserException
		 */
		private void obtenerArraySols() throws IOException, XmlPullParserException{
			/* Conectando ... */
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			/* Indicamos parametros */
			request.addProperty(
					"idAlumno",
					Integer.parseInt(session.getUserDetails().get(
							SessionManager.KEY_ID)));

			request.addProperty("idDestino", -1);

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aqu� metemos la
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

				arraySols = new ArraySolicitudes(
						(SoapObject) envelope.getResponse());

				// Si hasta aqu� todo ha ido bien, lo siguiente ser� abrir
				// la
				// nueva interfaz
				if (arraySols.getErrno() == 0) {
					// Todo ha ido bien, mostramos un Toast
					System.out.println("Hola");
					// Toast t = Toast.makeText(context, "Destino Creado",
					// Toast.LENGTH_SHORT);
					// t.show();

					// en base al rol

				} else if (arraySols.getErrno() == -2) {
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
		}
		
		/**
		 * Obtiene una lista de asignaturas matriculables en base
		 * al id del alumno y al destino que ha solicitado
		 * 
		 * @param idAlumno Id del alumno solicitante
		 * @param idDestino Id del destino a cursar la carrera
		 * @return Lista de asignaturas extrangeras
		 * @throws IOException
		 * @throws XmlPullParserException
		 */
		private ArrayAsignaturasExt obtenerArrayAsigs(Integer idAlumno, Integer idDestino)
				throws IOException, XmlPullParserException{
			/* Conectando ... */
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);

			/* Indicamos parametros */
			request.addProperty("idAlumno", idAlumno);

			request.addProperty("idDestino", idDestino);

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aqu� metemos la
													// peticion
													// en el "Sobre"

			/* Definimos un objeto transporte para dirigir el Sobre */
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); // Lanzamos la
														// llamada

			// Con call se produce la llamada, y se espera (bloquea)
			// hasta
			// que
			// se obtiene la respuesta
			// SoapPrimitive response =
			// (SoapPrimitive)envelope.getResponse();
			ArrayAsignaturasExt temp = null;
			if (envelope.getResponse() != null) {
				
				temp = new ArrayAsignaturasExt(
						(SoapObject) envelope.getResponse());
				
				/**
				 * Una consulta que no devuelve ning�n dato por no existir
				 * no es necesario almacenar el errno.
				 * 
				 * Se descarta esta consulta.
				 */
				if(temp.getErrno() == 1){
					return null;
				}
				
			}
			
			return temp;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				obtenerArraySols();

				NombreDestino = new ArrayList<String>();
				IdsDestinos = new ArrayList<Integer>();

				for (int i = 0; i < arraySols.getSolicitudes().size(); i++) {
					NombreDestino.add(arraySols.getSolicitudes().get(i)
											.getNomDestino());
					
					IdsDestinos.add(arraySols.getSolicitudes().get(i)
											.getIdDest());
				}

				// Segunda Consulta a la base de datos
				arrayAsigs = new ArrayList<ArrayAsignaturasExt>();
				for (int i = 0; i < NombreDestino.size(); i++) {

					ArrayAsignaturasExt temp = obtenerArrayAsigs(Integer.parseInt(session.getUserDetails().get(
							SessionManager.KEY_ID)), IdsDestinos.get(i));
					
					if(temp != null){ //�Ha devuelto el servicio SOAP un dato �til?
						arrayAsigs.add(temp);
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
			
			try{
				
				ListaAsignaturas = new HashMap<String, List<Asignatura>>();
				for(ComplexSolicitud sol : arraySols.getSolicitudes()){
					//Establecemos los principios de clave - valor para introducir
					//los datos en el diccionario
					String clave = sol.getNomDestino();
					List<Asignatura> valores = new ArrayList<Asignatura>();
					
					for(ArrayAsignaturasExt ext : arrayAsigs){
						for(ComplexAsignaturaExt asg : ext.getAsignaturas()){
							Asignatura temp = null;
							if(asg.getCentro().equalsIgnoreCase(sol.getNomDestino())){ 
								//�Esta asignatura se cursa en el destino actual?
								temp = new Asignatura(asg.getNombre(), false, asg.getCreditos(),asg.getId());
								valores.add(temp);
							}
						}
					}
					
					ListaAsignaturas.put(clave, valores);
					
				}
				
				cargarLista();
			} catch(Exception e){
				e.getMessage();
			}
			
		}

		/*
		 * this.idDestino = idDestino; this.idPais = idPais; this.idIdioma =
		 * idIdioma; this.idNivel = idNivel;
		 */

	}

	/**
	 * Env�a las asignaturas que se han elegido a la BBDD para el precontrato.
	 * Llegados a este punto, el coordinador puede aceptar un precontrato.
	 * 
	 *
	 */
	private class aTaskEnviarAsignaturasPrecontrato extends AsyncTask<Void, Void, Void> {

		final String NAMESPACE = "urn:Erasmus";
		final String URL = "http://10.0.2.2/services.php";
		final String METHOD_NAME = "agregarAsignaturaSolicitud";
		final String SOAP_ACTION = "urn:Erasmus";
		private Activity context;
		private GenericResult resultado;
		private SessionManager session;
		
		public aTaskEnviarAsignaturasPrecontrato(Activity _ctxt, SessionManager _session) {

			this.context = _ctxt;
			this.session = _session;

		}
		
		private void enviarAsignatura(int idDestino, int idAsignatura) 
				throws IOException, XmlPullParserException{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			/* Indicamos parametros */
			request.addProperty("idAlumno",Integer.parseInt(session.getUserDetails().get(SessionManager.KEY_ID)));
			request.addProperty("idDestino", idDestino);
			request.addProperty("idAsignaturaExt", idAsignatura);

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aqu� metemos la
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

							resultado = new GenericResult(
									(SoapObject) envelope.getResponse());

							// Si hasta aqu� todo ha ido bien, lo siguiente ser� abrir
							// la
							// nueva interfaz
							if (resultado.getErrno() == 0) {
								// Todo ha ido bien, mostramos un Toast
								System.out.println("Hola");
								// Toast t = Toast.makeText(context, "Destino Creado",
								// Toast.LENGTH_SHORT);
								// t.show();

								// en base al rol

							} else if (resultado.getErrno() == -2) {
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
			
			// Con call se produce la llamada, y se espera (bloquea) hasta
			// que
			// se obtiene la respuesta
			// SoapPrimitive response =
			// (SoapPrimitive)envelope.getResponse();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			//int var = ListaAsignaturas.get(NombreDestino.get(0)).size();
			
			for(int i=0; i<IdsDestinos.size();i++){
				for(int j=0; j<ListaAsignaturas.get(NombreDestino.get(i)).size();j++){
					if(ListaAsignaturas.get(NombreDestino.get(i)).get(j).isChekeado()){
						try {
							enviarAsignatura(IdsDestinos.get(i), ListaAsignaturas.get(NombreDestino.get(i)).get(j).getId());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent act = new Intent(context, AceptarPrecontratoActivity.class);
			startActivity(act);
		}
		
	}
}