package com.mant.auxiliares_coordinador;

//Esta clase almacenará los datos de la base de datos y luego será usada
//por la clase AdaptadorDestinosCoordinador para cargarlo en el layout
//coorespondiente

public class Nombre_Destino {
	private String nombre_destino;
	private String pais;
	private String idioma;
	private boolean disponible;
	private int plazas;
	private String nivel;
	
	
	
	public Nombre_Destino(String nombre_destino, String pais, String idioma,
			boolean disponible, int plazas, String nivel) {
		super();
		this.nombre_destino = nombre_destino;
		this.pais = pais;
		this.idioma = idioma;
		this.disponible = disponible;
		this.plazas = plazas;
		this.nivel = nivel;
	}
	

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombre_destino() {
		return nombre_destino;
	}

	public void setNombre_destino(String nombre_destino) {
		this.nombre_destino = nombre_destino;
	}
	
	

}
