import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;

public class ConsultarDepartamentos1 {

	// Consulta todos los departamentos de la base de datos personal.db
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			Objects<Departamento> coleccionDepartamentos = odb.getObjects(Departamento.class);
			if (coleccionDepartamentos.size() == 0) {
				System.out.println("No hay departamentos en la base de datos.");
			}
			else {
				while (coleccionDepartamentos.hasNext()) {
					Departamento departamento = coleccionDepartamentos.next();
					oid = odb.getObjectId(departamento);
					System.out.println("OID = " + oid.toString() + 
					                   " --> " + departamento.toString());
				}
				System.out.println("Se han consultado " + coleccionDepartamentos.size() + 
				                   " departamentos de la base de datos.");
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
