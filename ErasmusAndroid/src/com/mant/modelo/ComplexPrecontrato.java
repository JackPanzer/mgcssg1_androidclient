package com.mant.modelo;

import java.util.ArrayList;
import org.ksoap2.serialization.SoapObject;


public class ComplexPrecontrato {
	
	private String Usuario;
	private String destino;
	private ArrayList<ComplexAsignaturaExt> lAsigEx;
	
	public ComplexPrecontrato(SoapObject obj){
		
	}
	
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public ArrayList<ComplexAsignaturaExt> getlAsigEx() {
		return lAsigEx;
	}
	public void setlAsigEx(ArrayList<ComplexAsignaturaExt> lAsigEx) {
		this.lAsigEx = lAsigEx;
	}

}
