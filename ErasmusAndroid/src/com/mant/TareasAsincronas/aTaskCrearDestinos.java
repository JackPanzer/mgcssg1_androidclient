package com.mant.TareasAsincronas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mant.alumno.Principal_Alumno;
import com.mant.coordinador.PricipalCoordinadorActivity;
import com.mant.modelo.ArrayDestinos;
import com.mant.modelo.ComplexDestino;
import com.mant.modelo.ComplexUsuario;

public class aTaskCrearDestinos extends AsyncTask<Void, Void, Void> {
	//Esto es un comentario
	// private int idUsu;
	private ComplexUsuario respuesta;
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
				respuesta = new ComplexUsuario(
						(SoapObject) envelope.getResponse());

				// Si hasta aquí todo ha ido bien, lo siguiente será abrir la
				// nueva interfaz
				if (respuesta.getErrno() == 0) {
					// Todo ha ido bien, mostramos un Toast

					Toast t = Toast.makeText(context, "Destino Creado", Toast.LENGTH_SHORT);
					t.show();

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