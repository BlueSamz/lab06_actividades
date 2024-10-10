package lab06;

import java.util.List;

public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarPedido(String nombrePlato, String tipoP) {
        if (nombrePlato.isEmpty() || tipoP.isEmpty()) {
            vista.mostrarMensaje("El nombre y tipo del plato no pueden estar vacíos.");
            return;
        }

        if (!nombrePlato.matches("[a-zA-Z ]+")) {
            vista.mostrarMensaje("El nombre del plato es inválido.");
            return;
        }

        if (!tipoP.matches("[a-zA-Z ]+")) {
            vista.mostrarMensaje("El tipo del plato es inválido.");
            return;
        }

        try {
            int idPedido = vista.solicitarIdPedido();  // Se añade para generar un número de pedido único
            modelo.agregarPedido(new Pedido(idPedido, nombrePlato, tipoP));
            vista.mostrarMensaje("Pedido agregado: " + nombrePlato + " con número " + idPedido);
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public void eliminarPedido() {
        int idPedido = vista.solicitarIdPedido(); // Solicita el número de pedido en lugar del nombre
        try {
            modelo.eliminarPedido(idPedido);  // Se pasa el número de pedido
            vista.mostrarMensaje("Pedido eliminado con número: " + idPedido);
        } catch (PedidoNoEncontradoException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public void actualizarPedido() {
        String nombrePlatoAnt = vista.solicitarNombrePlatoAnt();
        String nuevoNombre = vista.solicitarNuevoNombre();
        try {
            modelo.actualizarPedido(nombrePlatoAnt, nuevoNombre);
            vista.mostrarMensaje("Pedido actualizado: " + nuevoNombre);
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage()); // si el pedido no existe
        }
    }

    public void buscarPedido() {
        int idPedido = vista.solicitarCriterio();
        try {
            List<Pedido> pedidosEncontrados = modelo.buscarPedidoPorId(idPedido);
            vista.mostrarPedidos(pedidosEncontrados);
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }
    
    public void marcarPedidoComoCompleto() {
        int idPedido = vista.solicitarIdPedido();
        modelo.marcarPedidoComoCompleto(idPedido);
        vista.mostrarMensaje("Pedido marcado como completo.");
    }
    
    public void contarPedidosPendientes() {
        int cantidadPendientes = modelo.contarPedidosPendientes();
        vista.mostrarMensaje("Total de pedidos pendientes: " + cantidadPendientes);
    }

    public void mostrarPedidosPorEstado() {
        String estado = vista.solicitarEstado();
        List<Pedido> pedidos = modelo.filtrarPedidosPorEstado(estado);
        vista.mostrarPedidos(pedidos);
    }

    // Mostrar pedidos con ID incluido
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la lista.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println("- ID: " + pedido.getIdPedido() + ", Nombre: " + pedido.getNombrePlato() + " (Tipo: " + pedido.getTipoP() + ")");
            }
        }
    }
    
    public void mostrarPedidos() {
        List<Pedido> pedidos = modelo.getPedidos();
        vista.mostrarPedidos(pedidos);
    }
    
    public void mostrarHistorial() {
        List<Pedido> historial = modelo.obtenerHistorial();
        vista.mostrarPedidos(historial);
    }
    
    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":
                    String nombrePlato = vista.solicitarNombrePlato();
                    String tipoP = vista.solicitarTipoPlato();
                    agregarPedido(nombrePlato, tipoP);
                    break;
                case "2":
                    mostrarPedidos();
                    break;
                case "3":
                    eliminarPedido();
                    break;
                case "4":
                    actualizarPedido();
                    break;
                case "5":
                    buscarPedido();
                    break;
                case "6":
                    contarPedidosPendientes();
                    break;
                case "7":
                    marcarPedidoComoCompleto();
                    break;
                case "8":
                    mostrarPedidosPorEstado();
                    break;
                case "9":
                    mostrarHistorial();
                    break;
                case "10":
                    vista.mostrarMensaje("Saliendo...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("10"));
    }
}
