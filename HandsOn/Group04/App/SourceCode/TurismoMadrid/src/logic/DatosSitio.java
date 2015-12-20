package logic;

public class DatosSitio {
	private String nombre;
	private String descripcion;
	private String horario;
	private String equipamiento;
	private String telefono;
	private String latitud;
	private String longitud;
	private int numero;
	private String distrito;
	private int codPost;
	private String calle;
	private String metro;
	private String bus;
	private String renfe;

	public DatosSitio(String nombre, String descripcion, String horario, String equipamiento, String telefono,
			String latitud, String longitud, int numero, String distrito, int codPost, String calle, String metro,
			String bus, String renfe) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horario = horario;
		this.equipamiento = equipamiento;
		this.telefono = telefono;
		this.latitud = latitud;
		this.longitud = longitud;
		this.numero = numero;
		this.distrito = distrito;
		this.codPost = codPost;
		this.calle = calle;
		this.metro = metro;
		this.bus = bus;
		this.renfe = renfe;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(String equipamiento) {
		this.equipamiento = equipamiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public int getCodPost() {
		return codPost;
	}

	public void setCodPost(int codPost) {
		this.codPost = codPost;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getMetro() {
		return metro;
	}

	public void setMetro(String metro) {
		this.metro = metro;
	}

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getRenfe() {
		return renfe;
	}

	public void setRenfe(String renfe) {
		this.renfe = renfe;
	}

}
