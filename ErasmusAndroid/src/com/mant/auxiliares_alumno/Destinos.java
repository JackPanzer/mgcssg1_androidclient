package com.mant.auxiliares_alumno;

public class Destinos {
	private String destino;
	private boolean estado;
	
	//CONSTRUCTOR DE LA CLASE//
	public Destinos(String destino, boolean estado) {
		this.destino = destino;
		this.estado = estado;
	}

	//GETTERS Y SETTERS DE LA CLASE//
	
	public String getDia() {
		return destino;
	}

	public void setDia(String destino) {
		this.destino = destino;
	}

	public boolean isChekeado() {
		return estado;
	}

	public void setChekeado(boolean chekeado) {
		estado = chekeado;
	}
}
