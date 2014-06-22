package com.mant.TareasAsincronas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.PricipalCoordinadorActivity;
import com.mant.modelo.ComplexUsuario;

public class aTaskLogin extends AsyncTask <Void, Void, Void>{

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
			envelope.setOutputSoapObject(request); //Aqu� metemos la peticion en el "Sobre"
			
			/* Definimos un objeto transporte para dirigir el Sobre*/
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); //Lanzamos la llamada
			
			// Con call se produce la llamada, y se espera (bloquea) hasta que se obtiene la respuesta
			//SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
			if (envelope.getResponse() != null){
				respuesta = new ComplexUsuario((SoapObject)envelope.getResponse());
				
				//Si hasta aqu� todo ha ido bien, lo siguiente ser� abrir la nueva interfaz
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
						act = new Intent(this.context, Principal_Alumno.class);
						this.context.startActivity(act);
						break;
					case 2:
						act = new Intent(this.context, PricipalCoordinadorActivity.class);
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
