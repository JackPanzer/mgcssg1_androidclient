package pract.mant.modelo;

import org.ksoap2.serialization.SoapObject;

/**
 * Representa una asignatura de la base de datos
 *
 */
public class ComplexAsignatura {
	private int errno;
	
	private int idAsig;
	private String nombre;
	private String titulacion;
	private int creditos;
	private String coordinador;
	
	/**
	 * Constructor de la clase, convierte un objeto SoapObject
	 * con una estructura de ComplexAsignatura del servidor en
	 * datos que podamos manejar
	 * 
	 * @param obj SoapObject con información sobre la asignatura
	 */
	public ComplexAsignatura(SoapObject obj){
		
		String _errno = obj.getPrimitivePropertyAsString("errno");
		String _idAsig = obj.getPrimitivePropertyAsString("id");
		String _nombre = obj.getPrimitivePropertyAsString("nombre");
		String _titulacion = obj.getPrimitivePropertyAsString("titulacion");
		String _creditos = obj.getPrimitivePropertyAsString("creditos");
		String _coordinador = obj.getPrimitivePropertyAsString("coordinador");
		
		if(_errno.equals("")){
			this.errno = 0;
		} else {
			this.errno = Integer.parseInt(_errno);
		}
		
		if(_creditos.equals("")){
			this.creditos = 0;
		} else {
			this.creditos = Integer.parseInt(_creditos);
		}
		
		if(_idAsig.equals("")){
			this.idAsig = 0;
		} else {
			this.idAsig = Integer.parseInt(_idAsig);
		}
		
		this.nombre = _nombre;
		this.titulacion = _titulacion;
		this.coordinador = _coordinador;
			
	}

	/**
	 * Obtiene el código de error de la llamada SOAP
	 * 
	 * @return Código de error de la llamada
	 */
	public int getErrno() {
		return errno;
	}

	/**
	 * Establece el código de error de la llamada SOAP
	 * 
	 * @param errno Código de error de la llamada
	 */
	public void setErrno(int errno) {
		this.errno = errno;
	}

	/**
	 * Obtiene el ID de la asignatura
	 * 
	 * @return ID de la asignatura
	 */
	public int getIdAsig() {
		return idAsig;
	}

	/**
	 * Establece el ID de la asignatura
	 * 
	 * @param idAsig ID de la asignatura
	 */
	public void setIdAsig(int idAsig) {
		this.idAsig = idAsig;
	}

	/**
	 * Obtiene el nombre de la asignatura
	 * 
	 * @return Nombre de la asignatura
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la asignatura
	 * 
	 * @param nombre Nombre de la asignatura
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el nombre de la titulación donde se imparte la asignatura
	 *
	 * @return Nombre de la titulación
	 */
	public String getTitulacion() {
		return titulacion;
	}

	/**
	 * Establece el nombre de la titulación donde se imparte la asignatura
	 * 
	 * @param titulacion Nombre de la titulación
	 */
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	/**
	 * Obtiene los créditos de la asignatura
	 * 
	 * @return Créditos de la asignatura
	 */
	public int getCreditos() {
		return creditos;
	}

	/**
	 * Establece los créditos de la asignatura
	 * 
	 * @param creditos Créditos de la asignatura
	 */
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	/**
	 * Obtiene el nombre del coordinador de la asignatura
	 * 
	 * @return Nombre del coordinador de la asignatura
	 */
	public String getCoordinador() {
		return coordinador;
	}

	/**
	 * Establece el nombre del coordinador de la asignatura
	 * 
	 * @param coordinador Nombre del coordinador de la asignatura
	 */
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}
}
