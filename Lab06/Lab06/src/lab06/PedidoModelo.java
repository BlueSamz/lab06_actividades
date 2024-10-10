package lab06;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    private List<Pedido> pedidos;
    private List<Pedido> historial;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void marcarPedidoComoCompleto(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido && pedido.getEstado().equals("pendiente")) {
                pedido.setEstado("completo");
                historial.add(pedido);
                System.out.println("Pedido con ID " + idPedido + " marcado como completo.");
                return;
            }
        }
        System.out.println("Pedido no encontrado o ya está completo.");
    }
    
    public List<Pedido> filtrarPedidosPorEstado(String estado) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equals(estado)) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }
    
    public void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede estar vacio."); 
        }
        
        // Validar que no haya otro pedido con el mismo ID
        for (Pedido p : pedidos) {
            if (p.getIdPedido() == pedido.getIdPedido()) {
                throw new IllegalArgumentException("Ya existe un pedido con el ID: " + pedido.getIdPedido());
            }
        }
        
        pedidos.add(pedido);
        historial.add(pedido);
    }
    
    
    public void eliminarPedido(int idPedido) throws PedidoNoEncontradoException { 
        boolean encontrado = false;
        
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getIdPedido() == idPedido) { // Usamos el número de pedido
                pedidos.remove(i);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new PedidoNoEncontradoException("El pedido con el número " + idPedido + " no se encontró.");
        }
    }

    public void actualizarPedido(String nombrePlatoAnt, String nuevoPedido) {
        if (nombrePlatoAnt == null || nuevoPedido == null) {
            throw new IllegalArgumentException("El nombre del plato antiguo y el nuevo no pueden estar vacíos.");
        }
        if (!nuevoPedido.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("El nuevo nombre del plato solo puede contener caracteres alfabéticos.");
        }

        boolean pedidoEncontrado = false;
        for (Pedido pedido : pedidos) {
            if (pedido.getNombrePlato().equalsIgnoreCase(nombrePlatoAnt)) {
                pedido.setNombrePlato(nuevoPedido);
                pedidoEncontrado = true;
                break;
            }
        }

        if (!pedidoEncontrado) {
            throw new IllegalArgumentException("El plato con el nombre '" + nombrePlatoAnt + "' no se encontró.");
        }
    }

    public List<Pedido> buscarPedidoPorId(int idPedido) {
        List<Pedido> pedidosEncontrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                pedidosEncontrados.add(pedido);
            }
        }

        if (pedidosEncontrados.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron pedidos con el ID: " + idPedido);
        }

        return pedidosEncontrados;
    }

   
    public int contarPedidos() {
        return pedidos.size();
    }
    
    public int contarPedidosPendientes() {
        int contador = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equals("pendiente")) {
                contador++;
            }
        }
        return contador;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public List<Pedido> obtenerHistorial() {
        return historial;
    }
}