package com.mant.auxiliares_alumno;

//Carga los destinos disponibles para ese alumno en concreto, los cuales se podrán
//elegir con un check, no se puede permitir mas de tres, será usada por el 
//la clase Adaptadordestinos dentro del paquete adaptadores_alumno

public class Destinos {
	private String destino;
	private boolean estado;
	
	//CONSTRUCTOR DE LA CLASE//
	public Destinos(String destino, boolean estado) {
		this.destino = destino;
		this.estado = estado;
	}

	//GETTERS Y SETTERS DE LA CLASE//
	
	public String getDia() {
		return destino;
	}

	public void setDia(String destino) {
		this.destino = destino;
	}

	public boolean isChekeado() {
		return estado;
	}

	public void setChekeado(boolean chekeado) {
		estado = chekeado;
	}
}
