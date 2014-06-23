package com.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

public class ArrayDestinos {
	
	private int errno;
	private Vector<ComplexDestino> destinos;
	
	public ArrayDestinos(SoapObject obj){
		
		if(obj != null){
			destinos = new Vector<ComplexDestino>();
			
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
				ComplexDestino auxDestino = new ComplexDestino(f);
				destinos.add(auxDestino);
				
			}		
		}
	}
	
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Vector<ComplexDestino> getDestinos() {
		return destinos;
	}

	public void setDestinos(Vector<ComplexDestino> destinos) {
		this.destinos = destinos;
	}

}
