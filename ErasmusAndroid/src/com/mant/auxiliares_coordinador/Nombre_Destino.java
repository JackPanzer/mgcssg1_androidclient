package com.mant.auxiliares_coordinador;

//Esta clase almacenará los datos de la base de datos y luego será usada
//por la clase AdaptadorDestinosCoordinador para cargarlo en el layout
//coorespondiente

public class Nombre_Destino {
	String nombre_destino;

	
	public Nombre_Destino(String nombre_destino) {
		super();
		this.nombre_destino = nombre_destino;
	}

	public String getNombre_destino() {
		return nombre_destino;
	}

	public void setNombre_destino(String nombre_destino) {
		this.nombre_destino = nombre_destino;
	}
	
	

}
