
public class Empleado {

	private String nombre;
	private String fechaAlta;
	private double salario;
	private Departamento departamento;

	
	public Empleado(String nombre, String fechaAlta, 
	                double salario, Departamento departamento) {
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.salario = salario;
		this.departamento = departamento;
	}
		
	@Override
	public String toString() {
		String cadenaDepartamento = "null";
		if (this.departamento != null) {
			cadenaDepartamento = this.departamento.toString();
		}
		return 
			"Empleado [Nombre = " + this.nombre + 
			", FechaAlta = " + this.fechaAlta + 
			", Salario = " + String.format("%.2f", this.salario) +
			", Departamento = " + cadenaDepartamento + 
			"]";
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
