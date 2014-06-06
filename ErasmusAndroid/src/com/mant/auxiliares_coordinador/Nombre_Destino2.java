package com.mant.auxiliares_coordinador;

//Esta clase almacenará los datos de la base de datos y luego será usada
//por la clase AdaptadorDestinosCoordinador2 para cargarlo en el layout
//coorespondiente, además añade un check que estará a false

public class Nombre_Destino2 {
	String nombre_destino;
	boolean check;

	public Nombre_Destino2(String nombre_destino, boolean check) {
		super();
		this.nombre_destino = nombre_destino;
		this.check = check;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getNombre_destino() {
		return nombre_destino;
	}

	public void setNombre_destino(String nombre_destino) {
		this.nombre_destino = nombre_destino;
	}

}
