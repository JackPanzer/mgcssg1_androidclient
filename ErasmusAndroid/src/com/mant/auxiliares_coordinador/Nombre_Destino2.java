package com.mant.auxiliares_coordinador;

//Esta clase almacenar� los datos de la base de datos y luego ser� usada
//por la clase AdaptadorDestinosCoordinador2 para cargarlo en el layout
//coorespondiente, adem�s a�ade un check que estar� a false

public class Nombre_Destino2 {
	String nombre_destino;
	int id_destino;
	boolean check;

	public Nombre_Destino2(String nombre_destino, int id_destino, boolean check) {
		super();
		this.nombre_destino = nombre_destino;
		this.id_destino = id_destino;
		this.check = check;
	}

	public int getId_destino() {
		return id_destino;
	}

	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
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
