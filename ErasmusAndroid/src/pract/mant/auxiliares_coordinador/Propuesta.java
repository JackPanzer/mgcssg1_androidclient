package pract.mant.auxiliares_coordinador;

import java.util.ArrayList;

/**
 * Clase que almacena los datos de un precontrato recibido de la base de datos
 * 
 */
public class Propuesta {
	private int idAl;
	private String nombre;
	private int IdDestino;
	private String Destino;
	private String fecha;
	private boolean aceptado;
	
	public Propuesta(int idAl, String nombre, int idDestino, String destino,boolean aceptado) {
		super();
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
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
