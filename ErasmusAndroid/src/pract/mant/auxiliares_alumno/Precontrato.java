package pract.mant.auxiliares_alumno;

import java.util.ArrayList;

//Clase auxiliar que guardará los precontratos y dará la posibilidad de enviarlos al
//coordinador académico, esta clase será usada por AdaptadorPrecontratos, se le deben
//de añadir mas atributos
public class Precontrato {
	private int idAl;
	private String nombre;
	private int IdDestino;
	private String Destino;
	private String fecha;
	private boolean aceptado;

	// private ArrayList<String> asignaturas;

	public Precontrato(int idAl, String nombre, int idDestino, String destino,
			boolean aceptado) {
		this.idAl = idAl;
		this.nombre = nombre;
		IdDestino = idDestino;
		Destino = destino;
		this.aceptado = aceptado;
	}

	public int getIdAl() {
		return idAl;
	}

	public void setIdAl(int idAl) {
		this.idAl = idAl;
	}

	public int getIdDestino() {
		return IdDestino;
	}

	public void setIdDestino(int idDestino) {
		IdDestino = idDestino;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	public boolean getAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	/*
	 * public ArrayList<String> getAsignaturas() { return asignaturas; } public
	 * void setAsignaturas(ArrayList<String> asignaturas) { this.asignaturas =
	 * asignaturas; }
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * public String getTelefono() { return telefono; } public void
	 * setTelefono(String telefono) { this.telefono = telefono; } public String
	 * getTitulacion() { return titulacion; } public void setTitulacion(String
	 * titulacion) { this.titulacion = titulacion; } public String
	 * getPoblacion() { return poblacion; } public void setPoblacion(String
	 * poblacion) { this.poblacion = poblacion; } public String getIdioma() {
	 * return idioma; } public void setIdioma(String idioma) { this.idioma =
	 * idioma; }
	 */
}
