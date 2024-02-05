package proyecto.tema4.modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listaPedidosProductos")
public class PedidosProductos {
    private List<PedidoProducto> listaPedidosProductos;

    public PedidosProductos() {
        
    }

    public PedidosProductos(List<PedidoProducto> listaPedidosProductos) {
        this.listaPedidosProductos = listaPedidosProductos;
    }

    @XmlElement(name = "pedidoProducto")
    public List<PedidoProducto> getListaPedidosProductos() {
        return listaPedidosProductos;
    }

    public void setListaPedidosProductos(List<PedidoProducto> listaPedidosProductos) {
        this.listaPedidosProductos = listaPedidosProductos;
    }

    @Override
    public String toString() {
        return "PedidosProductos [listaPedidosProductos=" + listaPedidosProductos + "]";
    }
    
}
