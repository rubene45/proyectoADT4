package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listaProductos")
public class Productos {
    private List<Producto> listaProductos;

    public Productos() {

    }

    public Productos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @XmlElement(name = "producto")
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "Productos [listaProductos=" + listaProductos + "]";
    }
}
