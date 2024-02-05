package proyecto.tema4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import proyecto.tema4.modelo.*;

public class Main {
    private static BBDDManager bbdd = new BBDDManager();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Consultar tabla");
            System.out.println("2. Realizar nuevo pedido");
            System.out.println("3. Modificar stock producto");
            System.out.println("4. Cargar XML a BBDD");
            System.out.println("5. Backup de BBDD a XML");
            System.out.println("6. Salir");
            System.out.println();
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mostrarTabla();
                    break;
                case 2:
                    realizarPedido();
                    break;
                case 3:
                    modificarStock();
                    break;
                case 4:
                    insertarDesdeXML();
                    break;
                case 5:
                    backupXML();
                    break;

                case 6:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void modificarStock() {
        System.out.println("Catálogo:");
        System.out.println(bbdd.listarProductos());
        System.out.println();
        System.out.println("Introduzca código de producto a modificar el stock:");
        int codigoProducto = sc.nextInt();
        Producto productoSeleccionado = bbdd.buscarProductoPorId(codigoProducto);
        if (productoSeleccionado != null) {
            System.out.println("Introduzca cuanto stock desea añadir al producto: ");
            int masStock = sc.nextInt();
            bbdd.actualizarStock(productoSeleccionado, masStock);
            System.out.println("Stock añadido con exito.");
        } else {
            System.out.println("Codigo de producto no encontrado.");
        }
    }

    public static void insertarDesdeXML() {
        sc.nextLine();
        System.out.println("Introduzca la tabla que desee insertar desde un XML: clientes, productos");
        String tabla = sc.nextLine();
        if (tabla.equalsIgnoreCase("clientes")) {
            bbdd.insertarClientesXML();
        } else if (tabla.equalsIgnoreCase("productos")) {
            bbdd.insertarProductosXML();
        }
    }

    public static void backupXML() {
        sc.nextLine();
        System.out.println("Introduzca la tabla que desee guardar en un XML: clientes, productos, pedidos");
        String tabla = sc.nextLine();
        if (tabla.equalsIgnoreCase("clientes")) {
            LecturaXML.guardarClientes(bbdd.listarClientes());
        } else if (tabla.equalsIgnoreCase("productos")) {
            LecturaXML.guardarProductos(bbdd.listarProductos());
        } else if (tabla.equalsIgnoreCase("pedidos")) {
            LecturaXML.guardarPedidos(bbdd.listarPedidos());
        }
    }

    public static void mostrarTabla() {
        sc.nextLine();
        System.out.println("Introduzca el nombre de la tabla: categorias, productos, pedidos, clientes, detallePedidos, direccionesEnvio:");
        String tabla = sc.nextLine();
        if (tabla.equalsIgnoreCase("categorias")
            || tabla.equalsIgnoreCase("productos") 
            || tabla.equalsIgnoreCase("pedidos")
            || tabla.equalsIgnoreCase("clientes")
            || tabla.equalsIgnoreCase("detallePedidos")
            || tabla.equalsIgnoreCase("direccionesEnvio")) {
            System.out.println();
            bbdd.consultarTabla(tabla);
            System.out.println();
        } else {
            System.out.println("Tabla no válida.");
        }
    }

    public static void realizarPedido() {
        Cliente cliente;
        String respuesta;

        sc.nextLine();

        do {
            System.out.println();
            System.out.println("¿Crear nuevo cliente o utilizar uno ya existente? Nuevo/Existente");
            respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("Nuevo") && !respuesta.equalsIgnoreCase("Existente")) {
                System.out.println("Opción no válida.");
                System.out.println();
            }

            if (respuesta.equalsIgnoreCase("Nuevo")) {
                System.out.println("Introduzca email del nuevo usuario");
                String email = sc.nextLine();
                System.out.println("Introduzca contraseña:");
                String password = sc.nextLine();
                System.out.println("Introduzca nombre:");
                String nombre = sc.nextLine();
                System.out.println("Introduzca apellidos:");
                String apellidos = sc.nextLine();
                System.out.println("Introduzca telefono:");
                String tlf = sc.nextLine();

                cliente = new Cliente(email, password, nombre, apellidos, tlf);

                bbdd.introducirCliente(cliente);

                System.out.println("Cliente registrado: "+cliente);
                System.out.println();

                seleccionarDirEnvio(cliente);
                return;

            } else if (respuesta.equalsIgnoreCase("Existente")) {

                System.out.println("Introduzca email del cliente a buscar: ");
                cliente = bbdd.buscarClienteEmail(sc.nextLine());

                if (cliente != null) {

                    System.out.println("Cliente encontrado.");
                    System.out.println();

                    System.out.println("Datos del cliente: "+cliente);
                    seleccionarDirEnvio(cliente);
                    return;
                } else {
                    System.out.println("Cliente no encontrado.");
                    System.out.println();
                }
            }

        } while(!respuesta.equalsIgnoreCase("Nuevo") || !respuesta.equalsIgnoreCase("Existente"));
    }

    public static void seleccionarDirEnvio(Cliente cliente) {
        DireccionEnvio dirEnvio;
        String respuesta;
        
        if (bbdd.listarDireccionesEnvio().getListaEnvios().isEmpty()) {
            System.out.println("No se ha encontrado ninguna dirección de envio para este cliente, creando nueva dirección de envío");

            System.out.println("Introduzca direccion 1 de la nueva dirección:");
            String dir1 = sc.nextLine();
            System.out.println("Introduzca dirección 2:");
            String dir2 = sc.nextLine();
            System.out.println("Introduzca ciudad:");
            String ciudad = sc.nextLine();
            System.out.println("Introduzca código postal:");
            int codPostal = sc.nextInt();
            String email = cliente.getEmail();
            
            dirEnvio = new DireccionEnvio(dir1, dir2, ciudad, codPostal, email);
            bbdd.introducirDirEnvio(dirEnvio);
            pedirProductos(cliente, dirEnvio);
        } else {
            System.out.println();
            System.out.println("Se han encontrado las siguientes direcciones de envio para el cliente: "+bbdd.listarDireccionesEnvioCliente(cliente).getListaEnvios());
            System.out.println();
            System.out.println("¿Crear nueva direccion de envio o utilizar ya una existente? Nuevo/Existente");
            respuesta = sc.nextLine();

            if (!respuesta.equalsIgnoreCase("Nuevo") && !respuesta.equalsIgnoreCase("Existente")) {
                System.out.println("Opción no válida.");
                System.out.println();
            }

            if (respuesta.equalsIgnoreCase("Nuevo")) {
                System.out.println("Introduzca direccion 1 de la nueva dirección:");
                String dir1 = sc.nextLine();
                System.out.println("Introduzca dirección 2:");
                String dir2 = sc.nextLine();
                System.out.println("Introduzca ciudad:");
                String ciudad = sc.nextLine();
                System.out.println("Introduzca código postal:");
                int codPostal = sc.nextInt();
                String email = cliente.getEmail();
                
                dirEnvio = new DireccionEnvio(dir1, dir2, ciudad, codPostal, email);
                bbdd.introducirDirEnvio(dirEnvio);
                pedirProductos(cliente, dirEnvio);

            } else if (respuesta.equalsIgnoreCase("Existente")) {
                System.out.println("Seleccione ID de la direccion de envio a la cual quiere realizar el pedido: ");
                int id = sc.nextInt();

                List<DireccionEnvio> listaDirecciones = bbdd.listarDireccionesEnvioCliente(cliente).getListaEnvios();

                for (DireccionEnvio dirEnvioComprobar : listaDirecciones) {
                    if (dirEnvioComprobar.getId() == id) {
                        System.out.println("Dirección seleccionada: "+dirEnvioComprobar);
                        pedirProductos(cliente, dirEnvioComprobar);
                        return;
                    }
                }
                System.out.println("No coincide la ID de la dirección.");
                sc.nextLine();
            }
        }
    }

    public static void pedirProductos(Cliente cliente, DireccionEnvio dirEnvio) {
        HashMap<Producto, Integer> cantidadProductos = new HashMap<>();

        Producto productoSeleccionado = null;

        System.out.println();
        System.out.println("Catálogo:");
        System.out.println(bbdd.listarProductos());
        System.out.println();

        int codigoProducto = 0;
        do {
            System.out.println();
            System.out.println("Introduzca códigos de productos a pedir (-1 para finalizar compra, -2 para mostrar lista)");
            codigoProducto = sc.nextInt();
            productoSeleccionado = bbdd.buscarProductoPorId(codigoProducto);

            if (codigoProducto == -2) {
                System.out.println(cantidadProductos);
            } else if (codigoProducto >= 0) {
                if (cantidadProductos.containsKey(productoSeleccionado)) {
                        System.out.println("El producto ya está en el carrito.");
                } else {
                    if (productoSeleccionado != null) {
                        System.out.println("Producto seleccionado: " + productoSeleccionado + " cantidad en stock: " + productoSeleccionado.getCantidadEnStock());
                        System.out.println();
                        System.out.println("Introduzca cantidad que desea comprar:");
                        int cantidad = sc.nextInt();
                        if (cantidad <= bbdd.comprobarStockProducto(productoSeleccionado)) {
                            System.out.println("Producto introducido con éxito en el carrito.");
                            cantidadProductos.put(productoSeleccionado, cantidad);
                        } else {
                            System.out.println("No hay suficiente stock.");
                        }
                    
                    } else {
                        System.out.println("ID de producto no encontrado");
                    }
                }
            }

        } while (codigoProducto != -1);

        if (cantidadProductos.isEmpty()) {
            System.out.println("Error: el carrito está vacío, cancelando operación de pedido...");
        } else {
            int precio = 0;

            System.out.println("Resumen del pedido:");
            for (Map.Entry<Producto, Integer> productoCantidad : cantidadProductos.entrySet()) {
                Producto producto = productoCantidad.getKey();
                int cantidad = productoCantidad.getValue();

                int precioProducto = (int) (producto.getPrecio() * cantidad);
                precio += precioProducto;

                System.out.println("Producto: " + producto.getNombre() +
                        ", Cantidad: " + cantidad +
                        ", Precio Unitario: " + producto.getPrecio() +
                        ", Precio Total: " + precioProducto);
            }

            System.out.println("Precio total del pedido: " + precio);
            bbdd.realizarPedido(cliente, cantidadProductos, dirEnvio);
            System.out.println("¡Gracias por realizar un pedido!");

        }
    }
}
