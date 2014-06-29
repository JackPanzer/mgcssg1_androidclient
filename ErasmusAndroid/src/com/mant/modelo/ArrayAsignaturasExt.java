package com.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

public class ArrayAsignaturasExt {
	private int errno;
	private Vector<ComplexAsignaturaExt> asignaturas;
	
	
	public ArrayAsignaturasExt(SoapObject obj){
		
		if(obj != null){
			asignaturas = new Vector<ComplexAsignaturaExt>();
			
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
				ComplexAsignaturaExt auxAsignatura = new ComplexAsignaturaExt(f);
				asignaturas.add(auxAsignatura);
				
			}		
		}
	}
	
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Vector<ComplexAsignaturaExt> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Vector<ComplexAsignaturaExt> asignaturas) {
		this.asignaturas = asignaturas;
	}
}
