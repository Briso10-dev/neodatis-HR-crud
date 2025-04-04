package principal;

import entrada.Teclado;
import modelo.Empleado;
import modelo.Departamento;
import acceso.AccesoEmpleado;
import acceso.AccesoDepartamento;
import org.neodatis.odb.ODBRuntimeException;
import java.util.Map;

public class Actividad_1x02 {
    
    private static void escribirMapaEmpleados(Map<String, Empleado> mapaEmpleados) {
        if (mapaEmpleados.isEmpty()) {
            System.out.println("No se ha encontrado ningún empleado en la base de datos.");
        } else {
            for (Map.Entry<String, Empleado> entrada : mapaEmpleados.entrySet()) {
                System.out.println("OID = " + entrada.getKey() + " -->\n" + 
                                 entrada.getValue().toString());
            }
            System.out.println("Se han consultado " + mapaEmpleados.size() + 
                             " empleados de la base de datos.");
        }
    }
    
    private static void escribirMenuOpciones() {
        System.out.println("\nGESTIÓN DE EMPLEADOS");
        System.out.println("0) Salir del programa.");
        System.out.println("1) Insertar un empleado en la base de datos.");
        System.out.println("2) Consultar todos los empleados de la base de datos.");
        System.out.println("3) Consultar un empleado, por OID, de la base de datos.");
        System.out.println("4) Actualizar un empleado, por OID, de la base de datos.");
        System.out.println("5) Eliminar un empleado, por OID, de la base de datos.");
    }
    
    public static void main(String[] args) {
        int opcion;
        Map<String, Empleado> mapaEmpleados;
        String nombre, fechaAlta;
        double salario;
        int codigo, codigoDepartamento;
        Empleado empleado;
        Departamento departamento;
        
        do {
            escribirMenuOpciones();
            opcion = Teclado.leerEntero("¿Opción? ");
            
            switch (opcion) {
                case 1:
                    try {
                        nombre = Teclado.leerCadena("¿Nombre? ");
                        fechaAlta = Teclado.leerCadena("¿Fecha de Alta? ");
                        salario = Teclado.leerReal("¿Salario? ");
                        codigoDepartamento = Teclado.leerEntero("¿OID del Departamento? ");
                        departamento = AccesoDepartamento.consultarUno(codigoDepartamento);
                        
                        if (departamento != null) {
                            empleado = new Empleado(nombre, fechaAlta, salario, departamento);
                            if (AccesoEmpleado.insertar(empleado)) {
                                System.out.println("Se ha insertado un empleado en la base de datos.");
                            }
                        } else {
                            System.out.println("No existe ningún departamento con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;
                    
                case 2:
                    try {
                        mapaEmpleados = AccesoEmpleado.consultarTodos();
                        escribirMapaEmpleados(mapaEmpleados);
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;
                    
                case 3:
                    try {
                        codigo = Teclado.leerEntero("¿OID? ");
                        empleado = AccesoEmpleado.consultarUno(codigo);
                        if (empleado != null) {
                            System.out.println(empleado.toString());
                        } else {
                            System.out.println("No existe ningún empleado con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;

                case 4:
                    try {
                        codigo = Teclado.leerEntero("¿OID? ");
                        nombre = Teclado.leerCadena("¿Nombre? ");
                        fechaAlta = Teclado.leerCadena("¿Fecha de Alta? ");
                        salario = Teclado.leerReal("¿Salario? ");
                        codigoDepartamento = Teclado.leerEntero("¿OID del Departamento? ");
                        departamento = AccesoDepartamento.consultarUno(codigoDepartamento);
                        
                        if (departamento != null) {
                            if (AccesoEmpleado.actualizar(codigo, nombre, fechaAlta, salario, departamento)) {
                                System.out.println("Se ha actualizado un empleado de la base de datos.");
                            } else {
                                System.out.println("No existe ningún empleado con ese OID en la base de datos.");
                            }
                        } else {
                            System.out.println("No existe ningún departamento con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;

                case 5:
                    try {
                        codigo = Teclado.leerEntero("¿OID? ");
                        if (AccesoEmpleado.eliminar(codigo)) {
                            System.out.println("Se ha eliminado un empleado de la base de datos.");
                        } else {
                            System.out.println("No existe ningún empleado con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;
                    
                case 0:
                    break;
                    
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        } while (opcion != 0);
    }
}
