package proyecto.tema4;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import proyecto.tema4.modelo.*;

public class LecturaXML {
    private static final String RUTA_XML = "src//main//resources//";

    public static Productos cargarProductos() {
        File fichero = new File(RUTA_XML+"productos.xml");

        JAXBContext jaxbContext;
        try {

            jaxbContext = JAXBContext.newInstance(Productos.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (fichero.exists()) {
                return (Productos) jaxbUnmarshaller.unmarshal(fichero);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new Productos();
    }

    public static Pedidos cargarPedidos() {
        File fichero = new File(RUTA_XML+"pedidos.xml");

        JAXBContext jaxbContext;
        try {

            jaxbContext = JAXBContext.newInstance(Pedidos.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (fichero.exists()) {
                return (Pedidos) jaxbUnmarshaller.unmarshal(fichero);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new Pedidos();
    }

    public static Clientes cargarClientes() {
        File fichero = new File(RUTA_XML+"clientes.xml");

        JAXBContext jaxbContext;
        try {

            jaxbContext = JAXBContext.newInstance(Clientes.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (fichero.exists()) {
                return (Clientes) jaxbUnmarshaller.unmarshal(fichero);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new Clientes();
    }

    public static void guardarProductos(Productos producto) {
        LocalDateTime fecha = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        String fechaFormato = fecha.format(formatter);

        File fichero = new File(RUTA_XML+fechaFormato.toString()+"exportedProductos.xml");
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Productos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(producto, fichero);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPedidos(Pedidos pedido) {
        LocalDateTime fecha = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        String fechaFormato = fecha.format(formatter);

        File fichero = new File(RUTA_XML+"exportedPedidos.xml");
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(pedido, fichero);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void guardarClientes(Clientes cliente) {
        LocalDateTime fecha = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        String fechaFormato = fecha.format(formatter);

        File fichero = new File(RUTA_XML+fechaFormato.toString()+"exportedClientes.xml");
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Clientes.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(cliente, fichero);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
