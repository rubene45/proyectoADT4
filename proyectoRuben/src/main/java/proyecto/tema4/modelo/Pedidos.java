package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listaPedidos")
public class Pedidos {
    private List<Pedido> listaPedidos;

    public Pedidos() {

    }

    public Pedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @XmlElement(name = "pedido")
    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @Override
    public String toString() {
        return "Pedidos [listaPedidos=" + listaPedidos + "]";
    }
}
