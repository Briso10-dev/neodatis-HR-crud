package principal;

import entrada.Teclado;
import modelo.Departamento;
import acceso.AccesoDepartamento;
import org.neodatis.odb.ODBRuntimeException;
import java.util.Map;

public class Actividad_1x01 {
    
    private static void escribirMapaDepartamentos(Map<String, Departamento> mapaDepartamentos) {
        if (mapaDepartamentos.isEmpty()) {
            System.out.println("No se ha encontrado ningún departamento en la base de datos.");
        } else {
            for (Map.Entry<String, Departamento> entrada : mapaDepartamentos.entrySet()) {
                System.out.println("OID = " + entrada.getKey() + " -->\n" + 
                                 "Departamento [Nombre = " + entrada.getValue().getNombre() + 
                                 ", Ubicación = " + entrada.getValue().getUbicacion() + "]");
            }
            System.out.println("Se han consultado " + mapaDepartamentos.size() + 
                             " departamentos de la base de datos.");
        }
    }
    
    private static void escribirMenuOpciones() {
        System.out.println("\nGESTIÓN DE DEPARTAMENTOS");
        System.out.println("0) Salir del programa.");
        System.out.println("1) Insertar un departamento en la base de datos.");
        System.out.println("2) Consultar todos los departamentos de la base de datos.");
        System.out.println("3) Consultar un departamento, por OID, de la base de datos.");
        System.out.println("4) Actualizar un departamento, por OID, de la base de datos.");
        System.out.println("5) Eliminar un departamento, por OID, de la base de datos.");
    }
    
    public static void main(String[] args) {
        int opcion;
        Map<String, Departamento> mapaDepartamentos;
        String nombre, ubicacion;
        int codigo;
        Departamento departamento;
        
        do {
            escribirMenuOpciones();
            opcion = Teclado.leerEntero("¿Opción? ");
            
            switch (opcion) {
                case 1:
                    try {
                        nombre = Teclado.leerCadena("¿Nombre? ");
                        ubicacion = Teclado.leerCadena("¿Ubicación? ");
                        departamento = new Departamento(nombre, ubicacion);
                        AccesoDepartamento.insertar(departamento);
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;
                    
                case 2:
                    try {
                        mapaDepartamentos = AccesoDepartamento.consultarTodos();
                        escribirMapaDepartamentos(mapaDepartamentos);
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;
                    
                case 3:
                    try {
                        codigo = Teclado.leerEntero("¿OID? ");
                        departamento = AccesoDepartamento.consultarUno(codigo);
                        if (departamento != null) {
                            System.out.println("Departamento [Nombre = " + departamento.getNombre() + 
                                            ", Ubicación = " + departamento.getUbicacion() + "]");
                        } else {
                            System.out.println("No existe ningún departamento con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        System.out.println("Error de NeoDatis: " + odbre.getMessage());
                    }
                    break;

                case 4:
                    try {
                        codigo = Teclado.leerEntero("¿OID? ");
                        nombre = Teclado.leerCadena("¿Nombre? ");
                        ubicacion = Teclado.leerCadena("¿Ubicación? ");
                        if (AccesoDepartamento.actualizar(codigo, nombre, ubicacion)) {
                            System.out.println("Se ha actualizado un departamento de la base de datos.");
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
                        if (AccesoDepartamento.eliminar(codigo)) {
                            System.out.println("Se ha eliminado un departamento de la base de datos.");
                        } else {
                            System.out.println("No existe ningún departamento con ese OID en la base de datos.");
                        }
                    } catch (ODBRuntimeException odbre) {
                        if (odbre.getMessage().contains("referenced")) {
                            System.out.println("El departamento está referenciado en un empleado de la base de datos.");
                        } else {
                            System.out.println("Error de NeoDatis: " + odbre.getMessage());
                        }
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
