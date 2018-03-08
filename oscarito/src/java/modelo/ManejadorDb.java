package modelo;
import dao.Cliente;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
public class ManejadorDb {
    static SessionFactory sf;
    static { sf = HibernateUtil.getSessionFactory(); }
    static Session creaSesion() { return sf.openSession(); }
    public static int grabarCliente(Cliente c) {
        Session se = creaSesion();
        Cliente cold = (Cliente) se.get(Cliente.class, c.getDni());
        if (cold == null) {
            try {
                se.beginTransaction();
                se.save(c);
                se.getTransaction().commit();
            } catch (HibernateException ex) {
                System.out.println(ex.getMessage());
                se.getTransaction().rollback();
                return Controlador.ERROR_BD;
            } finally {
                se.close();
            }
        } else {
            return Controlador.ERROR_CLAVE;
        }
        return Controlador.SIN_ERROR;
    }
    public static List<Cliente> buscarCliente(String dni) {
        List<Cliente> listadoClientes;
        Session se = creaSesion();
        try {
            Criteria crt = se.createCriteria(Cliente.class);
            se.beginTransaction();
            if (!dni.equals("")) {
                crt.add(Restrictions
                        .like("dni", dni, MatchMode.ANYWHERE));
            }
            listadoClientes = crt.list();
            se.getTransaction().commit();
            se.close();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return listadoClientes;
    }
    public static int actualizarCliente(Cliente c) {
        Session se = creaSesion();
        try {
            se.beginTransaction();
            se.saveOrUpdate(c);
            se.getTransaction().commit();
            return Controlador.SIN_ERROR;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            se.getTransaction().rollback();
            return Controlador.ERROR_BD;
        } finally {
            se.close();
        }
    }
    public static int borrarCliente(String dni) {
        Session se = creaSesion();
        try {
            se.beginTransaction();
            se.delete(se.get(Cliente.class, dni));
            se.getTransaction().commit();
            return Controlador.SIN_ERROR;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            se.getTransaction().rollback();
            return Controlador.ERROR_BD;
        } finally {
            se.close();
        }
    }
}