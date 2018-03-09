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
    @NamedQuery(name = "Municipios.findAll", query = "SELECT m FROM Municipios m")
    , @NamedQuery(name = "Municipios.findByCodMunicipio", query = "SELECT m FROM Municipios m WHERE m.codMunicipio = :codMunicipio")
    , @NamedQuery(name = "Municipios.findByMunicipio", query = "SELECT m FROM Municipios m WHERE m.municipio = :municipio")})
public class Municipios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer codMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codMunicipio")
    private List<Cliente> clienteList;
    public Municipios() { }
    public Municipios(Integer codMunicipio) { this.codMunicipio = codMunicipio; }
    public Municipios(Integer codMunicipio, String municipio) 
    { this.codMunicipio = codMunicipio; this.municipio = municipio; }
    public Integer getCodMunicipio() { return codMunicipio; }
    public void setCodMunicipio(Integer codMunicipio) { this.codMunicipio = codMunicipio; }
    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    @XmlTransient
    public List<Cliente> getClienteList() { return clienteList; }
    public void setClienteList(List<Cliente> clienteList) { this.clienteList = clienteList; }
    @Override
    public int hashCode() 
    { int hash = 0; hash += (codMunicipio != null ? codMunicipio.hashCode() : 0); return hash; }
    @Override
    public boolean equals(Object object) 
    { if (!(object instanceof Municipios)) { return false; }
        Municipios other = (Municipios) object;
       if ((this.codMunicipio == null && other.codMunicipio != null) || 
           (this.codMunicipio != null && !this.codMunicipio.equals(other.codMunicipio))) 
        { return false; }
          return true; }
    @Override
    public String toString() { return "dao.Municipios[ codMunicipio=" + codMunicipio + " ]"; }   
}

// Municipios   municipio    codMunicipio    