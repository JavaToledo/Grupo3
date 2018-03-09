package dao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@Entity
@Table(catalog = "annexfactura", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p")
    , @NamedQuery(name = "Provincias.findByCodProvincia", query = "SELECT p FROM Provincias p WHERE p.codProvincia = :codProvincia")
    , @NamedQuery(name = "Provincias.findByProvincia", query = "SELECT p FROM Provincias p WHERE p.provincia = :provincia")})
public class Provincias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer codProvincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String provincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codProvincia")
    private List<Cliente> clienteList;
    public Provincias() { }
    public Provincias(Integer codProvincia) { this.codProvincia = codProvincia; }
    public Provincias(Integer codProvincia, String provincia) 
    { this.codProvincia = codProvincia; this.provincia = provincia; }
    public Integer getCodProvincia() { return codProvincia; }
    public void setCodProvincia(Integer codProvincia) { this.codProvincia = codProvincia; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    @XmlTransient
    public List<Cliente> getClienteList() { return clienteList; }
    public void setClienteList(List<Cliente> clienteList) { this.clienteList = clienteList; }
    @Override
    public int hashCode() 
    { int hash = 0; hash += (codProvincia != null ? codProvincia.hashCode() : 0); return hash; }
    @Override
    public boolean equals(Object object) 
    { if (!(object instanceof Provincias)) { return false; }
        Provincias other = (Provincias) object;
       if ((this.codProvincia == null && other.codProvincia != null) || 
           (this.codProvincia != null && !this.codProvincia.equals(other.codProvincia))) 
        { return false; }
          return true; }
    @Override
    public String toString() { return "dao.Provincias[ codProvincia=" + codProvincia + " ]"; }   
}