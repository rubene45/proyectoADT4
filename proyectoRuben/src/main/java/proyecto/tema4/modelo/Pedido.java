package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pedido")
public class Pedido {
    private int id;
    private String estado;
    private String fecha;
    private String emailCliente;
    private int idDireccion;

    public Pedido() {

    }

    public Pedido(int id, String estado, String fecha, String emailCliente, int idDireccion) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.emailCliente = emailCliente;
        this.idDireccion = idDireccion;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlElement(name = "emailCliente")
    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @XmlElement(name = "idDireccion")
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", estado=" + estado + ", fecha=" + fecha + ", emailCliente=" + emailCliente
                + ", idDireccion=" + idDireccion + "]";
    }
    
}
