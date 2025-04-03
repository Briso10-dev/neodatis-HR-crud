import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import entrada.Teclado;

public class InsertarEmpleado {

	// Inserta un empleado con un departamento en la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oidEmpleado = null;
		OID oidDepartamento = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			String nombre = Teclado.leerCadena("¿Nombre? ");
			String fechaAlta = Teclado.leerCadena("¿Fecha de Alta? ");
			double salario = Teclado.leerReal("¿Salario? ");
			Objects<Departamento> coleccionDepartamentos = odb.getObjects(Departamento.class);
			if (coleccionDepartamentos.size() == 0) {
				System.out.println("No hay departamentos en la base de datos.");
			}
			else {
				while (coleccionDepartamentos.hasNext()) {
					Departamento departamento = coleccionDepartamentos.next();
					oidDepartamento = odb.getObjectId(departamento);
					System.out.println("OID = " + oidDepartamento.toString() + 
					                   " --> " + departamento.toString());
				}
			}
			int codigoDepartamento = Teclado.leerEntero("¿OID de Departamento? ");
			oidDepartamento = OIDFactory.buildObjectOID(codigoDepartamento);
			Departamento departamento = (Departamento) odb.getObjectFromId(oidDepartamento);
			Empleado empleado = new Empleado(nombre, fechaAlta, salario, departamento);
			oidEmpleado = odb.store(empleado);
			System.out.println("Se ha insertado un empleado con OID " + 
			                   oidEmpleado.toString() + " en la base de datos.");
		}
		catch (ODBRuntimeException odbre) {
			System.out.println("Error de NeoDatis: " + odbre.getMessage());
			System.out.println("No se ha encontrado ningún departamento con OID " +
			                   oidDepartamento.toString());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
