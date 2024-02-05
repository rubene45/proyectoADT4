package proyecto.tema4.modelo;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "producto")
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private String marca;
    private String descripcion;
    private int cantidadEnStock;
    private String categoria;

    public Producto() {

    }

    public Producto(int codigo, String nombre, double precio, String marca, String descripcion, int cantidadEnStock,
            String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.cantidadEnStock = cantidadEnStock;
        this.categoria = categoria;
    }

    @XmlElement(name = "codigo")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlElement(name = "marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlElement(name = "cantidadEnStock")
    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    @XmlElement(name = "categoria")
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca
                + ", descripcion=" + descripcion + ", cantidadEnStock=" + cantidadEnStock + ", categoria=" + categoria
                + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Producto otroProducto = (Producto) obj;

        return codigo == otroProducto.codigo &&
                Double.compare(otroProducto.precio, precio) == 0 &&
                cantidadEnStock == otroProducto.cantidadEnStock &&
                Objects.equals(nombre, otroProducto.nombre) &&
                Objects.equals(marca, otroProducto.marca) &&
                Objects.equals(descripcion, otroProducto.descripcion) &&
                Objects.equals(categoria, otroProducto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, precio, marca, descripcion, cantidadEnStock, categoria);
    }

}