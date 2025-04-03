import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import entrada.Teclado;

public class ConsultarDepartamentos2 {

	// Consulta varios departamentos, por ubicaci�n, de la base de datos personal.db,
	// ordenados por nombre de forma ascendente.
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			String ubicacion = Teclado.leerCadena("¿Ubicación? ");
			ICriterion criterio = Where.equal("ubicacion", ubicacion);
			IQuery consulta = new CriteriaQuery(Departamento.class, criterio);
			consulta.orderByAsc("nombre");
			Objects<Departamento> coleccionDepartamentos = odb.getObjects(consulta);
			if (coleccionDepartamentos.size() == 0) {
				System.out.println("No hay departamentos con esa ubicaci�n en la base de datos.");
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
