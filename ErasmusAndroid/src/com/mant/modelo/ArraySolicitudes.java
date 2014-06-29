package com.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

public class ArraySolicitudes {
	private int errno;
	private Vector<ComplexSolicitud> solicitud;
	
	public ArraySolicitudes(SoapObject obj){
		
		if(obj != null){
			solicitud = new Vector<ComplexSolicitud>();
			
			/**
			 * La primera properti es el errno, es lo mismo que poner:
			 * String _errno = obj.getProperty(0)
			 * */
			String _errno = obj.getPrimitivePropertyAsString("errno");
			if(_errno.equals("")){
				errno = -2;
			} else {
				errno = Integer.parseInt(_errno);
			}
			
			//Obtengo el numero de filas del "array bidimensional"
			int nFil = obj.getPropertyCount();
			
			for(int i=1; i<nFil ;i++){
				SoapObject f = (SoapObject) obj.getProperty(i);
							
				/**
				 * Creamos un nuevo destino y lo añadimos a la lista
				 * */
				ComplexSolicitud auxDestino = new ComplexSolicitud(f);
				solicitud.add(auxDestino);
				
			}		
		}
	}
	
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Vector<ComplexSolicitud> getSolicitudes() {
		return solicitud;
	}

	public void setSolicitudes(Vector<ComplexSolicitud> solicitud) {
		this.solicitud = solicitud;
	}
}
