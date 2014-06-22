package com.mant.TareasAsincronas;


import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.PricipalCoordinadorActivity;
import com.mant.modelo.ArrayDestinos;
import com.mant.modelo.ComplexDestino;
import com.mant.modelo.ComplexUsuario;

public class aTaskConsultarDestinos extends AsyncTask <Void, Void, Void>{

	private int idUsu;
	private ArrayList<ComplexDestino> respuesta;
	private Activity context;
	

	final String NAMESPACE = "urn:Erasmus";
	final String URL = "http://10.0.2.2/services.php";
	final String METHOD_NAME = "consultarDestinos";
	final String SOAP_ACTION = "urn:Erasmus";
	
	public aTaskConsultarDestinos(Activity _ctxt, int _idUsu){
		
		this.context = _ctxt;
		this.idUsu = _idUsu;
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {

		try {
			
			/* Conectando ...*/
			SoapObject request= new SoapObject(NAMESPACE, METHOD_NAME);
			
			/* Indicamos parametros */
			request.addProperty("idAlumno", this.idUsu);
			
			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); //Aquí metemos la peticion en el "Sobre"
			
			/* Definimos un objeto transporte para dirigir el Sobre*/
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); //Lanzamos la llamada
			
			if (envelope.getResponse() != null){
				
				ArrayDestinos misDestinos = new ArrayDestinos((SoapObject)envelope.getResponse());
				
			//	respuesta = new ArrayList<ComplexDestino>(collection)

				
 
						
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
}