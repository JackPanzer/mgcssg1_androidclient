package com.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

public class ArrayDestinos {
	
	private int errno;
	private Vector<ComplexDestino> destinos;
	
	public ArrayDestinos(SoapObject obj){
		
		if(obj != null){
			destinos = new Vector<ComplexDestino>();
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			Object o = obj.getAttributeSafely("destinos");
			
			
			
			if(_errno.equals("")){
				errno = -2;
			} else {
				errno = Integer.parseInt(_errno);
			}
		}
		
		
		//(Vector<SoapObject>)envelope.getResponse();
		
		//for (SoapObject soapObject : vectorOfSoapObject) {
		  //  put all properties into  DataPlusID  object
		  //  DataPlusID  dataPlusIDObj = new DataPlusID();
		   
		  // dataPlusIDObj.setData(soapObject.getPropertyAsString("data"));
			//}
		
	
	}

}
