package proyecto.tema4.modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listaDireccionesEnvios")
public class DireccionesEnvios {
    private List<DireccionEnvio> listaEnvios;

    public DireccionesEnvios() {

    }

    public DireccionesEnvios(List<DireccionEnvio> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }

    @XmlElement(name = "direccionEnvio")
    public List<DireccionEnvio> getListaEnvios() {
        return listaEnvios;
    }

    public void setListaEnvios(List<DireccionEnvio> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }

    @Override
    public String toString() {
        return "DireccionesEnvios [listaEnvios=" + listaEnvios + "]";
    }

    
}
