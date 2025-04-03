import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import entrada.Teclado;

public class ActualizarEmpleado {

	// Actualiza un empleado, por OID, de la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oidEmpleado = null;
		OID oidDepartamento = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			int codigo = Teclado.leerEntero("¿OID? ");
			oidEmpleado = OIDFactory.buildObjectOID(codigo);
			Empleado empleado = (Empleado) odb.getObjectFromId(oidEmpleado);
			String nombre = Teclado.leerCadena("¿Nombre? ");
			String fechaAlta = Teclado.leerCadena("¿Fecha de Alta? ");
			double salario = Teclado.leerReal("¿Salario? ");
			empleado.setNombre(nombre);
			empleado.setFechaAlta(fechaAlta);
			empleado.setSalario(salario);
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
			empleado.setDepartamento(departamento);
			odb.store(empleado);
			System.out.println("Se ha actualizado un empleado con OID " +
			                   oidEmpleado.toString() + " de la base de datos.");
		} 
		catch (ODBRuntimeException odbre) {
			System.out.println("Error de NeoDatis: " + odbre.getMessage());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
