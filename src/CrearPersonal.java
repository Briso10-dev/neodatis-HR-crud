import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class CrearPersonal {

	// Crea una base de datos personal.db con 7 departamentos y 11 empleados.
	public static void main(String[] args) {
		ODB odb = null;
		try {
			odb = ODBFactory.open("data\\personal.db");
			Departamento dep1 = new Departamento("Dirección", "Planta4");
			Departamento dep2 = new Departamento("Contabilidad", "Planta1");
			Departamento dep3 = new Departamento("Recursos Humanos", "Planta1");
			Departamento dep4 = new Departamento("Compras", "Planta2");
			Departamento dep5 = new Departamento("Producción", "Planta2");
			Departamento dep6 = new Departamento("Marketing", "Planta3");
			Departamento dep7 = new Departamento("Ventas", "Planta3");
			Empleado emp01 = new Empleado("Alejandro Ruiz", "03/05/1985", 4025.77, dep1);
			Empleado emp02 = new Empleado("María Latorre", "27/11/2004", 2010.05, dep2);
			Empleado emp03 = new Empleado("Diego García", "14/07/2005", 1995.90, dep3);
			Empleado emp04 = new Empleado("Isabel Hidalgo", "01/09/1992", 3105.82, dep4);
			Empleado emp05 = new Empleado("Carlos Pérez", "25/01/2009", 2407.01, dep4);
			Empleado emp06 = new Empleado("Pilar Martínez", "30/03/1993", 3010.37, dep5);
			Empleado emp07 = new Empleado("Francisco Cruz", "07/04/2010", 2465.67, dep5);
			Empleado emp08 = new Empleado("Lucía Serrano", "10/06/1994", 3050.11, dep6);
			Empleado emp09 = new Empleado("Enrique Sierra", "17/10/2011", 2572.12, dep6);
			Empleado emp10 = new Empleado("Sofía Navarro", "20/12/1995", 3120.24, dep7);
			Empleado emp11 = new Empleado("Roberto Marín", "23/02/2012", 2524.30, dep7);
			odb.store(emp01);
			odb.store(emp02);
			odb.store(emp03);
			odb.store(emp04);
			odb.store(emp05);
			odb.store(emp06);
			odb.store(emp07);
			odb.store(emp08);
			odb.store(emp09);
			odb.store(emp10);
			odb.store(emp11);
			System.out.println("Se han insertado 7 departamentos en la base de datos.");
			System.out.println("Se han insertado 11 empleados en la base de datos.");
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
