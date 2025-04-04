package acceso;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import java.util.Map;
import java.util.LinkedHashMap;
import modelo.Empleado;
import modelo.Departamento;

public class AccesoEmpleado {
    private static final String NOMBRE_BBDD_PERSONAL = "data/personal.db";
    
    public static boolean insertar(Empleado empleado) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            // Verify department exists
            OID oidDpto = odb.getObjectId(empleado.getDepartamento());
            if (oidDpto != null) {
                odb.store(empleado);
                return true;
            }
            return false;
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
    
    public static Empleado consultarUno(int codigo) throws ODBRuntimeException {
        ODB odb = null;
        OID oid = null;
        Empleado empleado = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            oid = OIDFactory.buildObjectOID(codigo);
            Object objeto = odb.getObjectFromId(oid);
            if (objeto instanceof Empleado) {
                empleado = (Empleado) objeto;
            }
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
        return empleado;
    }
    
    public static Map<String, Empleado> consultarTodos() throws ODBRuntimeException {
        ODB odb = null;
        Map<String, Empleado> mapaEmpleados = new LinkedHashMap<>();
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            Objects<Empleado> objects = odb.getObjects(Empleado.class);
            while (objects.hasNext()) {
                Empleado empleado = objects.next();
                OID oid = odb.getObjectId(empleado);
                mapaEmpleados.put(oid.toString(), empleado);
            }
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
        return mapaEmpleados;
    }

    public static boolean actualizar(int codigo, String nombre, String fechaAlta, 
                                   double salario, Departamento nuevoDepartamento) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            OID oid = OIDFactory.buildObjectOID(codigo);
            Object objeto = odb.getObjectFromId(oid);
            if (objeto instanceof Empleado) {
                Empleado empleado = (Empleado) objeto;
                empleado.setNombre(nombre);
                empleado.setFechaAlta(fechaAlta);
                empleado.setSalario(salario);
                empleado.setDepartamento(nuevoDepartamento);
                odb.store(empleado);
                return true;
            }
            return false;
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public static boolean eliminar(int codigo) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            OID oid = OIDFactory.buildObjectOID(codigo);
            Object objeto = odb.getObjectFromId(oid);
            if (objeto instanceof Empleado) {
                odb.delete(objeto);
                return true;
            }
            return false;
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
    
    public static int contarEmpleadosPorDepartamento(OID oidDepartamento) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            ICriterion criterio = Where.equal("departamento", odb.getObjectFromId(oidDepartamento));
            IQuery consulta = new CriteriaQuery(Empleado.class, criterio);
            Objects<Empleado> coleccionEmpleados = odb.getObjects(consulta);
            return coleccionEmpleados.size();
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
