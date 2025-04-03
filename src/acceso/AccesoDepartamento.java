package acceso;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import java.util.Map;
import java.util.LinkedHashMap;
import modelo.Departamento;

public class AccesoDepartamento {
    private static final String NOMBRE_BBDD_PERSONAL = "data/personal.db";
    
    public static void insertar(Departamento departamento) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            odb.store(departamento);
            System.out.println("Se ha insertado un departamento en la base de datos.");
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
    
    public static Departamento consultarUno(int codigo) throws ODBRuntimeException {
        ODB odb = null;
        OID oid = null;
        Departamento departamento = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            oid = OIDFactory.buildObjectOID(codigo);
            Object objeto = odb.getObjectFromId(oid);
            if (objeto instanceof Departamento) {
                departamento = (Departamento) objeto;
            }
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
        return departamento;
    }
    
    public static Map<String, Departamento> consultarTodos() throws ODBRuntimeException {
        ODB odb = null;
        Map<String, Departamento> mapaDepartamentos = new LinkedHashMap<>();
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            Objects<Departamento> objects = odb.getObjects(Departamento.class);
            while (objects.hasNext()) {
                Departamento departamento = objects.next();
                OID oid = odb.getObjectId(departamento);
                mapaDepartamentos.put(oid.toString(), departamento);
            }
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
        return mapaDepartamentos;
    }

    public static boolean actualizar(int codigo, String nombre, String ubicacion) throws ODBRuntimeException {
        ODB odb = null;
        try {
            odb = ODBFactory.open(NOMBRE_BBDD_PERSONAL);
            OID oid = OIDFactory.buildObjectOID(codigo);
            Object objeto = odb.getObjectFromId(oid);
            if (objeto instanceof Departamento) {
                Departamento departamento = (Departamento) objeto;
                departamento.setNombre(nombre);
                departamento.setUbicacion(ubicacion);
                odb.store(departamento);
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
            if (objeto instanceof Departamento) {
                // TODO: Check if department is referenced by any employee
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
}
