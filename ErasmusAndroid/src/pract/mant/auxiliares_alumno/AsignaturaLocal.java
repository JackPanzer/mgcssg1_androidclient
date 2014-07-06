package pract.mant.auxiliares_alumno;

/**
 * En esta clase se guardaran las asignaturas que el alumno puede elegir
 * para cursar este periodo académico, el check servirá para enviar a la base 
 * de datos las asignaturas que ha elegido para convalidar, lo usará la clase
 * AdaptadorAsignaturasLocales del paquete adaptadores_alumno
 * 
 */
public class AsignaturaLocal {
	private int creditos;
	private String nombre;
	private boolean estado;
	private int id;
	private String coordinador;
	private boolean convalidar;
	
	//CONSTRUCTOR DE LA CLASE//
	public AsignaturaLocal(String nombre, boolean estado, int creditos, int id, String coordinador, boolean convalidar) {
		this.nombre = nombre;
		this.estado = estado;
		this.creditos = creditos;
		this.id=id;
		this.coordinador = coordinador;
		this.convalidar = convalidar;
	}
	
	//GETTERS Y SETTERS DE LA CLASE//
	
	public int getCreditos() {
		return creditos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	public String getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}

	public String getasignatura() {
		return nombre;
	}

	public void setasignatura(String nombre) {
		this.nombre = nombre;
	}

	public boolean isChekeado() {
		return estado;
	}

	public void setChekeado(boolean chekeado) {
		estado = chekeado;
	}

	public boolean isConvalidar() {
		return convalidar;
	}

	public void setConvalidar(boolean convalidar) {
		this.convalidar = convalidar;
	}
}
