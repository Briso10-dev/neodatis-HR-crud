import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;
import entrada.Teclado;

public class ActualizarDepartamento {

	// Actualiza un departamento, por OID, de la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data/personal.db");
			int codigo = Teclado.leerEntero("¿OID? ");
			oid = OIDFactory.buildObjectOID(codigo);			
			Departamento departamento = (Departamento) odb.getObjectFromId(oid);
			String nombre = Teclado.leerCadena("¿Nombre? ");
			String ubicacion = Teclado.leerCadena("¿Ubicación? ");
			departamento.setNombre(nombre);
			departamento.setUbicacion(ubicacion);
			odb.store(departamento);
			System.out.println("Se ha actualizado un departamento con OID " +
			                   oid.toString() + " de la base de datos.");
		} 
		catch (ODBRuntimeException odbre) {
			System.out.println("Error de NeoDatis: " + odbre.getMessage());
			System.out.println("No se ha encontrado ningún departamento con OID " +
			                   oid.toString());	
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
}
