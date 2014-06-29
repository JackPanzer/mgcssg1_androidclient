package com.mant.modelo;

import org.ksoap2.serialization.SoapObject;

public class ComplexSolicitud {

	private int errno;
	private String nomAlumno;
	private int  idAl;
	private String nomDestino;
	private int idDest; 
	private String fecha; 
	private boolean aceptado;
	
	public ComplexSolicitud(SoapObject obj) {
		if(obj != null){
			//Obteniendo los datos del usuario a través del obj. soap
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _nomAlumno = obj.getPrimitivePropertyAsString("nomAlumno");
			String _idAl = obj.getPrimitivePropertyAsString("idAl");
			String _nomDestino = obj.getPrimitivePropertyAsString("nomDestino");
			String _idDest = obj.getPrimitivePropertyAsString("idDest");
			String _fecha = obj.getPrimitivePropertyAsString("fecha");
			String _aceptado = obj.getPrimitivePropertyAsString("aceptado");
			
			if(_errno.equals("")){
				setErrno(0);
			} else {
				setErrno(Integer.parseInt(_errno));
			}
			
			nomAlumno = _nomAlumno;
			
			if(_idAl.equals("")){
				idAl = 0;
			} else {
				idAl = Integer.parseInt(_idAl);
			}
			
			nomDestino=_nomDestino;
			
			if(_idDest.equals("")){
				idDest = 0;
			} else{
				idDest = Integer.parseInt(_idDest);
			}
			
			fecha = _fecha;
		
			if(_aceptado.equals("")){
				aceptado = false;
			} else if(_aceptado.equals("0")){
				aceptado = false;
			} else aceptado = true;
			
			
		}
		
	}
	
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public String getNomAlumno() {
		return nomAlumno;
	}

	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}

	public int getIdAl() {
		return idAl;
	}

	public void setIdAl(int idAl) {
		this.idAl = idAl;
	}

	public String getNomDestino() {
		return nomDestino;
	}

	public void setNomDestino(String nomDestino) {
		this.nomDestino = nomDestino;
	}

	public int getIdDest() {
		return idDest;
	}

	public void setIdDest(int idDest) {
		this.idDest = idDest;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

}
