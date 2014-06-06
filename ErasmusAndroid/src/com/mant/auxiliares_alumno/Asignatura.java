package com.mant.auxiliares_alumno;

//En esta clase se guardaran las asignaturas que el alumno puede elegir
//para ese destino en concreto, el check servir� para enviar a la base 
//de datos las asignaturas que ha elegido de ese destino, lo usar� la clase
// AdaptadorDestinoAsignatura del paquete adaptadores_alumno

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
