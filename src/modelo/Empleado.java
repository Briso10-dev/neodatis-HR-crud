package modelo;

public class Empleado {
    private String nombre;
    private String fechaAlta;
    private double salario;
    private Departamento departamento;
    
    public Empleado(String nombre, String fechaAlta, double salario, Departamento departamento) {
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.departamento = departamento;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getFechaAlta() {
        return fechaAlta;
    }
    
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public String toString() {
        return "Empleado [Nombre = " + nombre + ",\n" +
               "FechaAlta = " + fechaAlta + ", Salario = " + String.format("%.2f", salario) + ",\n" +
               "Departamento = " + departamento.toString() + "]";
    }
}
