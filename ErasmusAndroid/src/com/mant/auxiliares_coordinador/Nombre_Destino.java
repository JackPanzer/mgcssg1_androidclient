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
	private int idDestino;
	private int idPais;
	private int idIdioma;
	private int idNivel;
	
	
	public Nombre_Destino(String nombre_destino, String pais, String idioma,
			boolean disponible, int plazas, String nivel, int idDestino,
			int idPais, int idIdioma, int idNivel) {
		super();
		this.nombre_destino = nombre_destino;
		this.pais = pais;
		this.idioma = idioma;
		this.disponible = disponible;
		this.plazas = plazas;
		this.nivel = nivel;
		this.idDestino = idDestino;
		this.idPais = idPais;
		this.idIdioma = idIdioma;
		this.idNivel = idNivel;
	}
	
	public int getIdDestino() {
		return idDestino;
	}


	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}


	public int getIdPais() {
		return idPais;
	}


	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}


	public int getIdIdioma() {
		return idIdioma;
	}


	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}


	public int getIdNivel() {
		return idNivel;
	}


	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
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
