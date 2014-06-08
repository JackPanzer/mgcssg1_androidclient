package com.mant.TareasAsincronas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;

public class aTaskLogin extends AsyncTask <Void, Void, Void>{

	private String nick;
	private String password;
	private String respuesta;
	
	/*TODO
	CAMBIAR POR LOS NUESTROS*/
	final String NAMESPACE = "urn:EjemploClaseService";
	final String URL = "http://10.0.2.2/wsejemploclase.php";
	final String METHOD_NAME = "realizarconsulta1";
	final String SOAP_ACTION = "urn:EjemploClaseService";
	
	public aTaskLogin(String _nick, String _password){
		this.nick = _nick;
		this.password = _password;
		respuesta = "";
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		try{
			/* Conectando ...*/
			SoapObject request= new SoapObject(NAMESPACE, METHOD_NAME);
			
			/* Indicamos parametros */
			request.addProperty("nick", nick);
			request.addProperty("pass", password);
			
			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); //Aquí metemos la peticion en el "Sobre"
			
			/* Definimos un objeto transporte para dirigir el Sobre*/
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); //Lanzamos la llamada
			
			// Con call se produce la llamada, y se espera (bloquea) hasta que se obtiene la respuesta
			SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
			if (response != null){
				respuesta = response.toString();
			}
		}catch(Exception e){
			respuesta = "Hay una excepcion: \n"+ e.getMessage();
		}
		return null;
	}

}
