
public class Departamento {

	private String nombre;
	private String ubicacion;
	
	
	public Departamento(String nombre, String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		return 
			"Departamento [Nombre = " + this.nombre + 
			", Ubicación = " + this.ubicacion + 
			"]";
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
