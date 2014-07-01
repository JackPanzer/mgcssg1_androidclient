package com.mant.modelo;

import java.util.ArrayList;
import org.ksoap2.serialization.SoapObject;

import android.R.bool;


public class ComplexPrecontrato {
	
	private int errno;
	private int idAl;
	private String Alumno;
	private int idDest;
	private String destino;
	private String fecha;
	private boolean aceptado;
	private ArrayList<ComplexAsignaturaExt> lAsigEx;

	
	
	public ComplexPrecontrato(SoapObject obj){
		if(obj != null){
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _idAl = obj.getPrimitivePropertyAsString("idAl");
			String _Alumno = obj.getPrimitivePropertyAsString("nomAlumno");
			String _idDest = obj.getPrimitivePropertyAsString("idDest");
			String _destino = obj.getPrimitivePropertyAsString("nomDestino");
			String _fecha = obj.getPrimitivePropertyAsString("fecha");
			String _aceptado = obj.getPrimitivePropertyAsString("aceptado");

			
			if(_errno.equals("")){
				errno = 0;
			} else {
				errno = Integer.parseInt(_errno);
			}
			
			if(_idAl.equals("")){
				idAl = 0;
			} else {
				idAl = Integer.parseInt(_idAl);
			}
			
			Alumno=_Alumno;
			
			if(_idDest.equals("")){
				idDest = 0;
			} else {
				idDest = Integer.parseInt(_idDest);
			}
			destino = _destino;
			
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


	public int getIdAl() {
		return idAl;
	}


	public void setIdAl(int idAl) {
		this.idAl = idAl;
	}


	public String getAlumno() {
		return Alumno;
	}


	public void setAlumno(String alumno) {
		Alumno = alumno;
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
