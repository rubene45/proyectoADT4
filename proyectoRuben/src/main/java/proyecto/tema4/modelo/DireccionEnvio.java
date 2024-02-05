package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "direccionEnvio")
public class DireccionEnvio {
    private int id;
    private String direccion1;
    private String direccion2;
    private String ciudad;
    private int codPostal;
    private String emailCliente;

    public DireccionEnvio() {

    }

    public DireccionEnvio(String direccion1, String direccion2, String ciudad, int codPostal, String emailCliente) {
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.emailCliente = emailCliente;
    }

    public DireccionEnvio(int id, String direccion1, String direccion2, String ciudad, int codPostal,
            String emailCliente) {
        this.id = id;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.emailCliente = emailCliente;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "direccion1")
    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    @XmlElement(name = "direccion2")
    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    @XmlElement(name = "ciudad")
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @XmlElement(name = "codPostal")
    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    @XmlElement(name = "emailCliente")
    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return "DireccionEnvio [id=" + id + ", direccion1=" + direccion1 + ", direccion2=" + direccion2 + ", ciudad="
                + ciudad + ", codPostal=" + codPostal + ", emailCliente=" + emailCliente + "]";
    }

}
