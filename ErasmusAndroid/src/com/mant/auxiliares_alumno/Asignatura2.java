package com.mant.auxiliares_alumno;

//En esta clase se mostrará las asignaturas que el alumno ha elegido
//para ese destino en concreto, lo usará la clase
//AdaptadorAsignatura del paquete adaptadores_alumno despues de ser llamada por
//adaptador AdaptadorPrecontrato

public class Asignatura2 {
	private String nombre;

	public Asignatura2(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
