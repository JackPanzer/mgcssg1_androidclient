package pract.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

/**
 * Representa un array de asignaturas que se imparten en el
 * centro de origen.
 * 
 */
public class ArrayAsignaturas {

	private int errno;
	private Vector<ComplexAsignatura> asignaturas;
	
	/**
	 * Constructor del objeto. Dado un SoapObject con todas las asignaturas dentro,
	 * su cometido es cargar esa información dentro de la lista de asignaturas.
	 * 
	 * @param obj SoapObject con la información de las asignaturas.
	 */
	public ArrayAsignaturas(SoapObject obj){
		String _errno = obj.getPrimitivePropertyAsString("errno");
		
		if(_errno.equals("")){
			this.errno = 0;
		} else {
			this.errno = Integer.parseInt(_errno);
		}
		
		int totalObjetos = obj.getPropertyCount();
		
		asignaturas = new Vector<ComplexAsignatura>();
		
		for(int i = 1; i < totalObjetos; i++){
			SoapObject soapElement = (SoapObject) obj.getProperty(i);
			ComplexAsignatura asignatura = new ComplexAsignatura(soapElement);
			
			asignaturas.add(asignatura);
		}
	}

	/**
	 * Obtiene el código de error de la llamada SOAP
	 * 
	 * @return Código de error de la llamada SOAP
	 */
	public int getErrno() {
		return errno;
	}

	/**
	 * Establece el código de error de la llamada SOAP
	 * 
	 * @param errno Código de error de la llamada SOAP
	 */
	public void setErrno(int errno) {
		this.errno = errno;
	}

	/**
	 * Devuelve la lista de asignaturas
	 * 
	 * @return Lista de asignaturas
	 */
	public Vector<ComplexAsignatura> getAsignaturas() {
		return asignaturas;
	}

	/**
	 * Establece la lista de asignaturas
	 * 
	 * @param asignaturas Lista de asignaturas
	 */
	public void setAsignaturas(Vector<ComplexAsignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
}
