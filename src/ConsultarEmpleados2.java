import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import entrada.Teclado;

public class ConsultarEmpleados2 {

	// Consulta varios empleados de la base de datos personal.db, 
	// con el criterio de b�squeda de tener un salario m�nimo dado y 
	// pertenecer a un departamento dado,
	// ordenados por nombre de forma ascendente.
	public static void main(String[] args) {
		ODB odb = null;
		OID oid = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			double salarioEmpleado = Teclado.leerReal("¿Salario Mínimo de Empleado? ");
			String nombreDepartamento = Teclado.leerCadena("¿Nombre de Departamento? ");
			ICriterion criterio = new And()
			                      .add(Where.ge("salario", salarioEmpleado))
			                      .add(Where.equal("departamento.nombre", nombreDepartamento));
			IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
			consulta.orderByAsc("nombre");
			Objects<Empleado> coleccionEmpleados = odb.getObjects(consulta);
			if (coleccionEmpleados.size() == 0) {
				System.out.println("No hay empleados con ese criterio en la base de datos.");
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
