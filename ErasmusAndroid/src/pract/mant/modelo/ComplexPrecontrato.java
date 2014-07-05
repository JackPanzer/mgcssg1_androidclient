package pract.mant.modelo;

import java.util.ArrayList;
import org.ksoap2.serialization.SoapObject;

import android.R.bool;


public class ComplexPrecontrato {
	
	private int errno;
	private int idAl;
	private String Alumno;
	private int idDest;
	private String destino;
	private boolean aceptado;
	//private ArrayList<ComplexAsignaturaExt> lAsigEx;

	
	
	public ComplexPrecontrato(SoapObject obj){
		if(obj != null){
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _idAl = obj.getPrimitivePropertyAsString("idAlumno");
			String _Alumno = obj.getPrimitivePropertyAsString("nomAlumno");
			String _idDest = obj.getPrimitivePropertyAsString("idDestino");
			String _destino = obj.getPrimitivePropertyAsString("nomDestino");

			
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
			
			aceptado =false;
			
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

}
