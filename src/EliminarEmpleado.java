import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;
import entrada.Teclado;

public class EliminarEmpleado {

	// Elimina un empleado, por OID, de la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			int codigo = Teclado.leerEntero("¿OID? ");
			oid = OIDFactory.buildObjectOID(codigo);
			Empleado empleado = (Empleado) odb.getObjectFromId(oid);
			odb.delete(empleado);
			System.out.println("Se ha eliminado un empleado con OID " +
			                   oid.toString() + " de la base de datos.");
		} 
		catch (ODBRuntimeException odbre) {
			System.out.println("Error de NeoDatis: " + odbre.getMessage());
			System.out.println("No se ha encontrado ning�n empleado con OID " +
			                   oid.toString());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

}
