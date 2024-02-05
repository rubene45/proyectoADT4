package proyecto.tema4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import proyecto.tema4.modelo.*;

public class BBDDManager {
    private static final String url = "jdbc:mysql://localhost:3306/ComponentesMarey";
    private static final String user = "ruben";
    private static final String password = "0123456789";

    private static DatabaseConnection BBDDCon = new DatabaseConnection(url, user, password);

    public BBDDManager() {

    }

    public void consultarTabla(String tabla) {

        if (tabla.equalsIgnoreCase("categorias")) {
            System.out.println(listarCategorias());
        } else if (tabla.equalsIgnoreCase("productos")) {
            System.out.println(listarProductos());
        } else if (tabla.equalsIgnoreCase("pedidos")) {
            System.out.println(listarPedidos());
        } else if (tabla.equalsIgnoreCase("clientes")) {
            System.out.println(listarClientes());
        } else if (tabla.equalsIgnoreCase("detallePedidos")) {
            System.out.println(listarDetallePedidos());
        } else if (tabla.equalsIgnoreCase("direccionesEnvio")) {
            System.out.println(listarDireccionesEnvio());
        }
    }

    public Connection getCon() {
        Connection con = BBDDCon.getCon();
        return con;
    }

    public Categorias listarCategorias() {

        List<Categoria> listaCategorias = new ArrayList<>();
        Categorias result = new Categorias();

        try {
            String sql = "SELECT * FROM producto;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            String nombre;

            while (rs.next()) {
                nombre = rs.getString("nombre");

                Categoria categoria = new Categoria(nombre);

                listaCategorias.add(categoria);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaCategorias(listaCategorias);
        return result;
    }

    public Productos listarProductos() {

        List<Producto> listaProductos = new ArrayList<>();
        Productos result = new Productos();

        try {
            String sql = "SELECT * FROM producto;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int codigo;
            String nombre;
            Double precio;
            String marca;
            String descripcion;
            int stock;
            String categoria;

            while (rs.next()) {
                codigo = rs.getInt("codigo");
                nombre = rs.getString("nombre");
                marca = rs.getString("marca");
                descripcion = rs.getString("descripción");
                precio = rs.getDouble("precio");
                stock = rs.getInt("cantidadEnStock");
                categoria = rs.getString("categoria");

                Producto producto = new Producto(codigo, nombre, precio, marca, descripcion, stock, categoria);

                listaProductos.add(producto);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaProductos(listaProductos);
        return result;
    }

    public Pedidos listarPedidos() {

        List<Pedido> listaPedidos = new ArrayList<>();
        Pedidos result = new Pedidos();

        try {
            String sql = "SELECT * FROM pedido;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int id;
            String estado;
            String fecha;
            String email;
            int idDireccion;

            while (rs.next()) {
                id = rs.getInt("ID");
                estado = rs.getString("EstadoPedido");
                fecha = rs.getString("FechaPedido");
                email = rs.getString("Email_Cliente");
                idDireccion = rs.getInt("ID_Direccion");

                Pedido pedido = new Pedido(id, estado, fecha, email, idDireccion);

                listaPedidos.add(pedido);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaPedidos(listaPedidos);
        return result;
    }

    public Clientes listarClientes() {

        List<Cliente> listaClientes = new ArrayList<>();
        Clientes result = new Clientes();

        try {
            String sql = "SELECT * FROM cliente;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            String email;
            String nombre;
            String pass;
            String apellidos;
            String tlf;

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("contraseña");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                tlf = rs.getString("teléfono");

                Cliente cliente = new Cliente(email, pass, nombre, apellidos, tlf);

                listaClientes.add(cliente);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaClientes(listaClientes);
        return result;
    }

    public PedidosProductos listarDetallePedidos() {

        List<PedidoProducto> listaPedidoProductos = new ArrayList<>();
        PedidosProductos result = new PedidosProductos();

        try {
            String sql = "SELECT * FROM pedidoproducto;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int id;
            int codigo;
            int cantidad;

            while (rs.next()) {
                id = rs.getInt("ID_Pedido");
                codigo = rs.getInt("Codigo_Producto");
                cantidad = rs.getInt("cantidad");

                PedidoProducto pedidoProducto = new PedidoProducto(id, codigo, cantidad);

                listaPedidoProductos.add(pedidoProducto);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaPedidosProductos(listaPedidoProductos);
        return result;
    }

    public DireccionesEnvios listarDireccionesEnvio() {

        List<DireccionEnvio> listaDireccionesEnvios = new ArrayList<>();
        DireccionesEnvios result = new DireccionesEnvios();

        try {
            String sql = "SELECT * FROM direccionesenvio;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int id;
            String dir1;
            String dir2;
            String ciudad;
            int codPostal;
            String emailCliente;

            while (rs.next()) {
                id = rs.getInt("ID");
                dir1 = rs.getString("dirección1");
                dir2 = rs.getString("direccion2");
                ciudad = rs.getString("ciudad");
                codPostal = rs.getInt("codigoPostal");
                emailCliente = rs.getString("email_cliente");

                DireccionEnvio direccionEnvio = new DireccionEnvio(id, dir1, dir2, ciudad, codPostal, emailCliente);

                listaDireccionesEnvios.add(direccionEnvio);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaEnvios(listaDireccionesEnvios);
        return result;
    }

    public Cliente buscarClienteEmail(String email) {
        Cliente clienteBuscado = null;

        try {
            String sql = "SELECT * FROM cliente WHERE email = "+'"'+email+'"'+";";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            String emailCliente;
            String pass;
            String nombre;
            String apellidos;
            String tlf;

            if (rs.next()) {
                emailCliente = rs.getString("email");
                pass = rs.getString("contraseña");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                tlf = rs.getString("teléfono");
                clienteBuscado = new Cliente(emailCliente, pass, nombre, apellidos, tlf);
            }

            rs.close();
            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return clienteBuscado;
    }

    public void introducirCliente(Cliente clienteAIntroducir) {
        try {
            String sql = "INSERT INTO cliente (email, contraseña, nombre, apellidos, teléfono) VALUES ("+clienteAIntroducir.getEmail()+
                      ", "+clienteAIntroducir.getPassword()+", "+clienteAIntroducir.getNombre()+", "+clienteAIntroducir.getApellidos()+", "+
                      clienteAIntroducir.getTelefono()+");";
            Statement sentencia = getCon().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void introducirDirEnvio(DireccionEnvio dirEnvio) {
        try {
            String sql = "INSERT INTO direccionesenvio (dirección1, dirección2, ciudad, codigoPostal, Email_cliente) VALUES ("+dirEnvio.getDireccion1()+
                      ", "+dirEnvio.getDireccion2()+", "+dirEnvio.getCiudad()+", "+dirEnvio.getCodPostal()+", "+dirEnvio.getEmailCliente()+");";
            Statement sentencia = getCon().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int comprobarStockProducto(Producto producto) {
        int stockDisponible = 0;

        try {
            String sql = "SELECT cantidadEnStock FROM producto WHERE codigo = "+producto.getCodigo()+";";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            if (rs.next()) {
                stockDisponible = rs.getInt("cantidadEnStock");
            }

            rs.close();
            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stockDisponible;
    }

    public DireccionesEnvios listarDireccionesEnvioCliente(Cliente cliente) {

        List<DireccionEnvio> listaDireccionesEnvios = new ArrayList<>();
        DireccionesEnvios result = new DireccionesEnvios();

        try {
            String sql = "SELECT * FROM direccionesenvio WHERE email_Cliente = "+'"'+cliente.getEmail()+'"'+" ;";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int id;
            String dir1;
            String dir2;
            String ciudad;
            int codPostal;
            String emailCliente;

            while (rs.next()) {
                id = rs.getInt("ID");
                dir1 = rs.getString("dirección1");
                dir2 = rs.getString("direccion2");
                ciudad = rs.getString("ciudad");
                codPostal = rs.getInt("codigoPostal");
                emailCliente = rs.getString("email_cliente");

                DireccionEnvio direccionEnvio = new DireccionEnvio(id, dir1, dir2, ciudad, codPostal, emailCliente);

                listaDireccionesEnvios.add(direccionEnvio);
            }
            rs.close();
            sentencia.close();
            getCon().close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setListaEnvios(listaDireccionesEnvios);
        return result;
    }

    public Producto buscarProductoPorId(int id) {
        Producto producto = null;

        try {
            String sql = "SELECT * FROM producto WHERE codigo = "+id+";";
            Statement sentencia = getCon().createStatement();
            ResultSet rs = sentencia.executeQuery(sql);

            int codigo;
            String nombre;
            Double precio;
            String marca;
            String descripcion;
            int stock;
            String categoria;

            if (rs.next()) {
                codigo = rs.getInt("codigo");
                nombre = rs.getString("nombre");
                marca = rs.getString("marca");
                descripcion = rs.getString("descripción");
                precio = rs.getDouble("precio");
                stock = rs.getInt("cantidadEnStock");
                categoria = rs.getString("categoria");

                producto = new Producto(codigo, nombre, precio, marca, descripcion, stock, categoria);
            }

            rs.close();
            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return producto;
    }

    public void realizarPedido(Cliente cliente, HashMap<Producto, Integer> productosCantidad, DireccionEnvio dirEnvio) {
        try {
            LocalDate fechaActual = LocalDate.now();

            String sqlInsertPedido = "INSERT INTO pedido (EstadoPedido, FechaPedido, Email_Cliente, ID_Direccion) VALUES ("+"'"+"Pendiente"+"'"+
                      ", "+fechaActual.toString()+", "+'"'+cliente.getEmail()+'"'+", "+dirEnvio.getId()+");";

            Statement sentencia = getCon().createStatement();
            sentencia.executeUpdate(sqlInsertPedido);


            int idPedido = 0;
            ResultSet resultSet = sentencia.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                idPedido = resultSet.getInt(1);
            }

            for (Map.Entry<Producto, Integer> cantidadProductos : productosCantidad.entrySet()) {
                Producto producto = cantidadProductos.getKey();
                int cantidad = cantidadProductos.getValue();
    
                String sqlUpdateStock = "UPDATE producto SET CantidadEnStock = CantidadEnStock - " + cantidad + " WHERE Codigo = " + producto.getCodigo();
                sentencia.executeUpdate(sqlUpdateStock);

                String sqlInsertDetallePedido = "INSERT INTO pedidoproducto (ID_Pedido, Codigo_producto, Cantidad) VALUES (" + idPedido + ", " + producto.getCodigo() + ", " + cantidad + ")";
                sentencia.executeUpdate(sqlInsertDetallePedido);
            }

            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertarProductosXML() {
        Productos productos = LecturaXML.cargarProductos();
    
        try {
            Statement sentencia = getCon().createStatement();
            for (Producto producto : productos.getListaProductos()) {
                String sqlInsert = "INSERT INTO producto (codigo, nombre, precio, marca, descripción, cantidadEnStock, categoria) VALUES ('" +
                    producto.getCodigo() + "', '" +
                    producto.getNombre() + "', '" +
                    producto.getPrecio() + "', '" +
                    producto.getMarca() + "', '" +
                    producto.getDescripcion() + "', '" +
                    producto.getCantidadEnStock() + "', '" +
                    producto.getCategoria() + "')";

                sentencia.executeUpdate(sqlInsert);
            }
            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void insertarClientesXML() {
        Clientes clientes = LecturaXML.cargarClientes();

        try {
            Statement sentencia = getCon().createStatement();
            for (Cliente cliente : clientes.getListaClientes()) {
                String sqlInsert = "INSERT INTO clientes (email, contraseña, nombre, apellidos, teléfono) VALUES ('" +
                cliente.getEmail() + "', '" +
                cliente.getPassword() + "', '" +
                cliente.getNombre() + "', '" +
                cliente.getApellidos() + "', '" +
                cliente.getTelefono() + "')";

                sentencia.executeUpdate(sqlInsert);
            }
            sentencia.close();
            getCon().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void actualizarStock(Producto producto, int cantidad) {
        try {
            Statement sentencia = getCon().createStatement();
            String sqlUpdate = "UPDATE producto SET cantidadEnStock = cantidadEnStock + " + cantidad +
                           " WHERE codigo = " + producto.getCodigo();

            sentencia.executeUpdate(sqlUpdate);
            sentencia.close();
            getCon().close(); 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
