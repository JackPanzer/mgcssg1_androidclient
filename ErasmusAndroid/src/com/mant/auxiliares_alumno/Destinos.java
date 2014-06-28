package com.mant.auxiliares_alumno;

//Carga los destinos disponibles para ese alumno en concreto, los cuales se podrán
//elegir con un check, no se puede permitir mas de tres, será usada por el 
//la clase Adaptadordestinos dentro del paquete adaptadores_alumno

public class Destinos {
	private int id;
	private String destino;
	private boolean estado;
	
	//CONSTRUCTOR DE LA CLASE//
	public Destinos(int id,String destino, boolean estado) {
		this.id =id;
		this.destino = destino;
		this.estado = estado;
	}

	//GETTERS Y SETTERS DE LA CLASE//
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public boolean isChekeado() {
		return estado;
	}

	public void setChekeado(boolean chekeado) {
		estado = chekeado;
	}
}
