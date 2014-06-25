package com.mant.modelo;

import org.ksoap2.serialization.SoapObject;

public class ComplexDestino {
	
	private int errno;
	private int id;
	private String nombre;
	private String pais;
	private int id_pais;//nuevo
	private String idioma;
	private int id_idioma;//nuevo
	private boolean disponible;
	private int numplazas;
	private String nvlrequerido;
	private int id_nvlrequerido;//nuevo
	
	public ComplexDestino(SoapObject obj){
		if(obj != null){
			//Obteniendo los datos del usuario a través del obj. soap
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _id = obj.getPrimitivePropertyAsString("id");
			String _nombre = obj.getPrimitivePropertyAsString("nombre");
			String _pais = obj.getPrimitivePropertyAsString("pais");
			String _id_pais = obj.getPrimitivePropertyAsString("id_pais");
			String _idioma = obj.getPrimitivePropertyAsString("idioma");
			String _id_idioma = obj.getPrimitivePropertyAsString("id_idioma");
			String _disponible = obj.getPrimitivePropertyAsString("disponible");
			String _numplazas = obj.getPrimitivePropertyAsString("numplazas");
			String _nvlrequerido = obj.getPrimitivePropertyAsString("nvlrequerido");
			String _id_nvlrequerido = obj.getPrimitivePropertyAsString("id_nvlrequerido");
			
			if(_errno.equals("")){
				setErrno(0);
			} else {
				setErrno(Integer.parseInt(_errno));
			}
			
			if(_id.equals("")){
				id = 0;
			} else {
				id = Integer.parseInt(_id);
			}
			
			nombre = _nombre;
			pais = _pais;
			idioma = _idioma;
			
			if(_disponible.equals("")){
				disponible = false;
			} else if(_disponible.equals("0")){
				disponible = false;
			} else disponible = true;
			
			if(_numplazas.equals("")){
				numplazas = 0;
			} else{
				numplazas = Integer.parseInt(_numplazas);
			}
			
			nvlrequerido = _nvlrequerido;
			
			if(_id_pais.equals("")){
				id_pais = 0;
			} else{
				id_pais = Integer.parseInt(_id_pais);
			}
			
			if(_id_idioma.equals("")){
				id_idioma = 0;
			} else{
				id_idioma = Integer.parseInt(_id_idioma);
			}
			
			if(_id_nvlrequerido.equals("")){
				id_nvlrequerido = 0;
			} else{
				id_nvlrequerido = Integer.parseInt(_id_nvlrequerido);
			}
		}
		
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getNumplazas() {
		return numplazas;
	}

	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}

	public String getNvlrequerido() {
		return nvlrequerido;
	}

	public void setNvlrequerido(String nvlrequerido) {
		this.nvlrequerido = nvlrequerido;
	}
	

	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public int getId_idioma() {
		return id_idioma;
	}

	public void setId_idioma(int id_idioma) {
		this.id_idioma = id_idioma;
	}

	public int getId_nvlrequerido() {
		return id_nvlrequerido;
	}

	public void setId_nvlrequerido(int id_nvlrequerido) {
		this.id_nvlrequerido = id_nvlrequerido;
	}

}
