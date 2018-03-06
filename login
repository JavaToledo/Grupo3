import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author raexmon
 */
 
@ManagedBean
@SessionScoped
public class login implements Serializable {
    
   private String usuario;
   private String paswword;
   private String mensaje;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPaswword() {
        return paswword;
    }

    public void setPaswword(String paswword) {
        this.paswword = paswword;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
   //validar el login
	public String validarLogin() {
		boolean valido = ManejadorBd.validar(usuario, paswword);
		if (valido) {
			HttpSession session = ManejadorSesiones.getSession();
			session.setAttribute("usuario", usuario);
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario y contraseña incorrectos",
							"Introduzca usuario y contraseña correctos"));
			return "login";
		}
	}

	//evento de deslogueo, sesion invalidada
	public String logout() {
		HttpSession session = ManejadorSesiones.getSession();
		session.invalidate();
		return "login";
	}
    
}
