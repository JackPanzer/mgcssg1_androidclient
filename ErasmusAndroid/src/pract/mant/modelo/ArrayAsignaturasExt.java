package pract.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;


/**
 * Representa un array de asignaturas que se imparten en el
 * centro de destino.
 * @author Betanzos
 *
 */
public class ArrayAsignaturasExt {
	private int errno;
	private Vector<ComplexAsignaturaExt> asignaturas;// Cada asignatura se guarda en la clase ComplexAsignaturaExt
	
	
	/**
	 * Constructor del objeto. Dado un SoapObject con todas las asignaturas extrangeras dentro,
	 * su cometido es cargar esa información dentro de la lista de asignaturas.
	 * 
	 * @param obj SoapObject con la información de las asignaturas extrangeras.
	 */
	
	public ArrayAsignaturasExt(SoapObject obj){
		
		if(obj != null){
			asignaturas = new Vector<ComplexAsignaturaExt>();
			
			/**
			 * La primera properti es el errno, es lo mismo que poner:
			 * String _errno = obj.getProperty(0)
			 * */
			String _errno = obj.getPrimitivePropertyAsString("errno");
			if(_errno.equals("")){
				errno = -2;
			} else {
				errno = Integer.parseInt(_errno);
			}
			
			//Obtengo el numero de filas del "array bidimensional"
			int nFil = obj.getPropertyCount();
			
			for(int i=1; i<nFil ;i++){
				SoapObject f = (SoapObject) obj.getProperty(i);
							
				/**
				 * Guardamos la asignatura añadiendola a la lista
				 * */
				ComplexAsignaturaExt auxAsignatura = new ComplexAsignaturaExt(f);
				asignaturas.add(auxAsignatura);
				
			}		
		}
	}
	
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Vector<ComplexAsignaturaExt> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Vector<ComplexAsignaturaExt> asignaturas) {
		this.asignaturas = asignaturas;
	}
}
