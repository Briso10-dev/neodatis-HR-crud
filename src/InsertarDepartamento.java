import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import entrada.Teclado;

public class InsertarDepartamento {

	// Inserta un departamento en la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			String nombre = Teclado.leerCadena("¿Nombre? ");
			String ubicacion = Teclado.leerCadena("¿Ubicación? ");
			Departamento departamento = new Departamento(nombre, ubicacion);
			OID oid = odb.store(departamento);
			System.out.println("Se ha insertado un departamento con OID " + 
			                   oid.toString() + " en la base de datos.");
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
