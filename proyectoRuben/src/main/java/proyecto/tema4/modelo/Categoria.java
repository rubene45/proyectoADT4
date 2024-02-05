package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categoria")
public class Categoria {
    private String nombre;

    public Categoria() {

    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria [nombre=" + nombre + "]";
    }

}
