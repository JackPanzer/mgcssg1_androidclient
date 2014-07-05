package pract.mant.modelo;

import org.ksoap2.serialization.SoapObject;

public class ComplexUsuario {

	private int errno;
	private int id;
	private String nombre;
	private String apellidos;
	private String nif;
	private int rol;
	private String direccion;
	private String poblacion;
	private String nick;
	private String passwd;
	private int titulacion;
	
	public ComplexUsuario(SoapObject obj){
		if(obj != null){
			//Obteniendo los datos del usuario a través del obj. soap
			
			String _errno = obj.getPrimitivePropertyAsString("errno");
			String _id = obj.getPrimitivePropertyAsString("id");
			String _nombre = obj.getPrimitivePropertyAsString("nombre");
			String _apellidos = obj.getPrimitivePropertyAsString("apellidos");
			String _nif = obj.getPrimitivePropertyAsString("nif");
			String _rol = obj.getPrimitivePropertyAsString("rol");
			String _direccion = obj.getPrimitivePropertyAsString("direccion");
			String _poblacion = obj.getPrimitivePropertyAsString("poblacion");
			String _nick = obj.getPrimitivePropertyAsString("nick");
			String _passwd = obj.getPrimitivePropertyAsString("passwd");
			String _titulacion = obj.getPrimitivePropertyAsString("titulacion");
			
			if(_errno.equals("")){
				errno = -2;
			} else {
				errno = Integer.parseInt(_errno);
			}
			
			if(_id.equals("")){
				id = 0;
			} else {
				id = Integer.parseInt(_id);
			}
			
			nombre = _nombre;
			apellidos = _apellidos;
			nif = _nif;
			
			if(_rol.equals("")){
				rol = 0;
			} else {
				rol = Integer.parseInt(_rol);
			}
			
			direccion = _direccion;
			poblacion = _poblacion;
			nick = _nick;
			passwd = _passwd;
			
			if(_titulacion.equals("")){
				titulacion = 0;
			} else {
				titulacion = Integer.parseInt(_titulacion);
			}
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(int titulacion) {
		this.titulacion = titulacion;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}
	
	
	
	
}
