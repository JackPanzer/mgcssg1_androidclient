package com.mant.TareasAsincronas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;

import com.mant.modelo.ComplexDestino;

import java.util.HashMap;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Intent;
import android.widget.Toast;

import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.PricipalCoordinadorActivity;
import com.mant.modelo.ArrayDestinos;
import com.mant.modelo.ComplexDestino;
import com.mant.modelo.ComplexUsuario;

public class aTaskConsultarTodosDestinos extends AsyncTask<Void, Void, Void> {

	// private int idUsu;
	private SessionManager session; // SESSION OBJECT
	private ArrayDestinos respuesta;
	private Activity context;

	final String NAMESPACE = "urn:Erasmus";
	final String URL = "http://10.0.2.2/services.php";
	final String METHOD_NAME = "obtenerDestinos";
	final String SOAP_ACTION = "urn:Erasmus";

	public aTaskConsultarTodosDestinos(Activity _ctxt, SessionManager _session) {

		this.context = _ctxt;
		this.session = _session;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {

			/* Conectando ... */
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			/* Creamos un envelop <Sobre> */
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request); // Aquí metemos la peticion
													// en el "Sobre"

			/* Definimos un objeto transporte para dirigir el Sobre */
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.debug = true;
			transporte.call(SOAP_ACTION, envelope); // Lanzamos la llamada

			// Con call se produce la llamada, y se espera (bloquea) hasta que
			// se obtiene la respuesta
			// SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
			if (envelope.getResponse() != null) {

				respuesta = new ArrayDestinos((SoapObject) envelope.getResponse());

				/**
				 * MOSTRAR LOS DESTINOS EN LA LISTA
				 */

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