package pract.mant.auxiliares_alumno;

import java.util.ArrayList;

//Clase auxiliar que guardar� los precontratos y dar� la posibilidad de enviarlos al
//coordinador acad�mico, esta clase ser� usada por AdaptadorPrecontratos, se le deben
//de a�adir mas atributos
public class Precontrato {
	private String nombre;
	private String telefono;
	private String titulacion;
	private String poblacion;
	private String idioma;
	
	public Precontrato(String nombre, String telefono, String titulacion,
			String poblacion, String idioma) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.titulacion = titulacion;
		this.poblacion = poblacion;
		this.idioma = idioma;
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
