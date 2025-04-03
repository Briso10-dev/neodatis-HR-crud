import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;

public class ConsultarEmpleados1 {

	// Consulta todos los empleados de la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			Objects<Empleado> coleccionEmpleados = odb.getObjects(Empleado.class);
			if (coleccionEmpleados.size() == 0) {
				System.out.println("No hay empleados en la base de datos.");
			}
			else {
				while (coleccionEmpleados.hasNext()) {
					Empleado empleado = coleccionEmpleados.next();
					oid = odb.getObjectId(empleado);
					System.out.println("OID = " + oid.toString() + 
					                   " --> " + empleado.toString());
				}
				System.out.println("Se han consultado " + coleccionEmpleados.size() + 
				                   " empleados de la base de datos.");
			}
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
