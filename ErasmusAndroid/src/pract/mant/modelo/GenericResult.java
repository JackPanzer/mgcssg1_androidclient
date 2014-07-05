package pract.mant.modelo;

import org.ksoap2.serialization.SoapObject;

/**
 * Clase que recibe el resultado de un Objeto soap que solo contiene
 * un entero que significaría si la consulta ha devuelto un resultado
 * o por el contrario un error. La usaremos cada vez que solo queramos
 * guardar datos en la base de datos o actualizar.
 * @author Betanzos
 *
 */

public class GenericResult {

	private int errno;
	
	public GenericResult(SoapObject obj){
		if(obj != null){
			//Obtenemos los datos del objeto soap y lo guardamos en variables
			//auxialiares
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			
			
			//Tratamos los datos para convertirlos en el formato
			//que deseemos
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
