package proyecto.tema4.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pedidoProducto")
public class PedidoProducto {
    private int IDPedido;
    private int codProducto;
    private int cantidad;

    public PedidoProducto() {

    }

    public PedidoProducto(int iDPedido, int codProducto, int cantidad) {
        IDPedido = iDPedido;
        this.codProducto = codProducto;
        this.cantidad = cantidad;
    }

    @XmlElement(name = "idPedido")
    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int iDPedido) {
        IDPedido = iDPedido;
    }

    @XmlElement(name = "codProducto")
    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    @XmlElement(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PedidoProducto [IDPedido=" + IDPedido + ", codProducto=" + codProducto + ", cantidad=" + cantidad + "]";
    }

    

}
