package com.mant.auxiliares_coordinador;

import java.util.ArrayList;

//Clase auxiliar que cargará los precontratos que han sido enviados 
//y guardados en la base de datos
public class Propuesta {
	private String nombre;
	private String telefono;
	private String titulacion;
	private String poblacion;
	private String idioma;
	private ArrayList<String> asignaturas;
	
	public Propuesta(String nombre, String telefono, String titulacion,
			String poblacion, String idioma, ArrayList<String> asignaturas) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.titulacion = titulacion;
		this.poblacion = poblacion;
		this.idioma = idioma;
		this.asignaturas = asignaturas;
	}
	public ArrayList<String> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(ArrayList<String> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
