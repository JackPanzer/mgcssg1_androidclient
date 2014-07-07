package pract.mant.auxiliares_coordinador;

/**
 * Clase que almacena los datos de un destino recibido de la base de datos
 * para ser usada por la clase AdaptadorEliminaDestinosCoordinador y
 * cargarlo en el layout correspondiente
 *
 */
public class EliminarDestino {
	String nombre_destino;
	int id_destino;
	boolean check;

	public EliminarDestino(String nombre_destino, int id_destino, boolean check) {
		super();
		this.nombre_destino = nombre_destino;
		this.id_destino = id_destino;
		this.check = check;
	}

	public int getId_destino() {
		return id_destino;
	}

	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getNombre_destino() {
		return nombre_destino;
	}

	public void setNombre_destino(String nombre_destino) {
		this.nombre_destino = nombre_destino;
	}

}
