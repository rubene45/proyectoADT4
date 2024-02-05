package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listaClientes")
public class Clientes {
    private List<Cliente> listaClientes;

    public Clientes() {

    }

    public Clientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @XmlElement(name = "cliente")
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public String toString() {
        return "Clientes [listaClientes=" + listaClientes + "]";
    }

    
}
