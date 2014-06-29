package com.mant.auxiliares_alumno;

//En esta clase se guardaran las asignaturas que el alumno puede elegir
//para ese destino en concreto, el check servirá para enviar a la base 
//de datos las asignaturas que ha elegido de ese destino, lo usará la clase
// AdaptadorDestinoAsignatura del paquete adaptadores_alumno

public class Asignatura {
	private int creditos;
	private String nombre;
	private boolean estado;
	
	//CONSTRUCTOR DE LA CLASE//
	public Asignatura(String nombre, boolean estado, int creditos) {
		this.nombre = nombre;
		this.estado = estado;
		this.creditos = creditos;
	}
	
	//GETTERS Y SETTERS DE LA CLASE//
	
	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
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
