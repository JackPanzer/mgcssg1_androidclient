package com.mant.TareasAsincronas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.mant.modelo.ComplexUsuario;

import android.app.Activity;
import android.os.AsyncTask;

public class aTaskLogin extends AsyncTask <Void, Void, Void>{

	private String nick;
	private String password;
	private ComplexUsuario respuesta;
	
	/*TODO
	CAMBIAR POR LOS NUESTROS*/
	final String NAMESPACE = "urn:Erasmus";
	final String URL = "http://10.0.2.2/services.php";
	final String METHOD_NAME = "loginUsuario";
	final String SOAP_ACTION = "urn:Erasmus";
	
	public aTaskLogin(String _nick, String _password){
		this.nick = _nick;
		this.password = _password;
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
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
