package pract.mant.auxiliares_alumno;

import java.util.ArrayList;

/**
 * Clase auxiliar que guardará los precontratos y dará la posibilidad de enviarlos al
 * coordinador académico, esta clase será usada por AdaptadorPrecontratos.
 * @author Betanzos
 *
 */


public class Precontrato {
	private int idAl;
	private String nombre;
	private int IdDestino;
	private String Destino;
	private boolean aceptado;


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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
