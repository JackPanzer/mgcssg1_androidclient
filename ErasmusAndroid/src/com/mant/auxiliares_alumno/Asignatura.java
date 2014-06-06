package com.mant.auxiliares_alumno;

public class Asignatura {
	private String nombre;
	private boolean estado;
	
	//CONSTRUCTOR DE LA CLASE//
	public Asignatura(String nombre, boolean estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	//GETTERS Y SETTERS DE LA CLASE//
	
	public String getasignatura() {
		return nombre;
	}

	public void setasignatura(String nombre) {
		this.nombre = nombre;
	}

	public boolean isChekeado() {
		return estado;
	}

	public void setChekeado(boolean chekeado) {
		estado = chekeado;
	}
}
