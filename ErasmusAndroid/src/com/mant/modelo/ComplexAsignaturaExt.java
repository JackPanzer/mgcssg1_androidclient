package com.mant.modelo;

import org.ksoap2.serialization.SoapObject;

public class ComplexAsignaturaExt {
	
	private int errno;
	
	private int id;
	private String nombre;
	private int creditos;
	private String centro; //Nombre del centro (Destino) donde se imparte
	private int idcentro; //Id del centro donde se imparte
	
	public ComplexAsignaturaExt(SoapObject obj){
		if(obj != null){
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _id = obj.getPrimitivePropertyAsString("id");
			String _nombre = obj.getPrimitivePropertyAsString("nombre");
			String _creditos = obj.getPrimitivePropertyAsString("creditos");
			String _centro = obj.getPrimitivePropertyAsString("centro");
			String _idcentro = obj.getPrimitivePropertyAsString("idcentro");
			
			if(_errno.equals("")){
				errno = 0;
			} else {
				errno = Integer.parseInt(_errno);
			}
			
			if(_id.equals("")){
				id = 0;
			} else {
				id = Integer.parseInt(_id);
			}
			
			if(_idcentro.equals("")){
				idcentro = 0;
			} else {
				idcentro = Integer.parseInt(_id);
			}
			
			nombre = _nombre;
			centro = _centro;
			
			if(_creditos.equals(""))
				creditos = 0;
			else 
				creditos = Integer.parseInt(_creditos); 
			
		}
	}
	
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}

}
