package com.mant.modelo;

import org.ksoap2.serialization.SoapObject;

public class GenericResult {

	private int errno;
	
	public GenericResult(SoapObject obj){
		if(obj != null){
			//Obteniendo los datos del usuario a través del obj. soap
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			
			if(_errno.equals("")){
				setErrno(0);
			} else {
				setErrno(Integer.parseInt(_errno));
			}
			
			
			
		}
		
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}
}
