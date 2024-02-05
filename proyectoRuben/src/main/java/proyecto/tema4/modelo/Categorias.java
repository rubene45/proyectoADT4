package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listaCategorias")
public class Categorias {
    private List<Categoria> listaCategorias;

    public Categorias() {

    }

    public Categorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @XmlElement(name = "categoria")
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public String toString() {
        return "Categorias [listaCategorias=" + listaCategorias + "]";
    }
}
