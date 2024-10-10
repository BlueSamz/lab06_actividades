package lab06;

import java.util.List;
import java.util.Scanner;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    // Método para solicitar el id del pedido
    public int solicitarIdPedido() {
        System.out.print("Ingrese el ID del pedido: ");
        return Integer.parseInt(scanner.nextLine());  // Convertimos la entrada en un entero
    }

    public String solicitarNombrePlato() {
        System.out.print("Ingrese el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipoPlato() {
        System.out.print("Ingrese el tipo del plato: ");
        return scanner.nextLine();
    }

    public String solicitarNombrePlatoAnt() {
        System.out.print("Ingrese el nombre del plato a actualizar: ");
        return scanner.nextLine();
    }

    public String solicitarNuevoNombre() {
        System.out.print("Ingrese el nuevo nombre del plato: ");
        return scanner.nextLine();
    }

    public int solicitarCriterio() {
        System.out.print("Ingrese el ID del pedido a buscar: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String solicitarTipoParaContar() {
        System.out.print("Ingrese el tipo para contar: ");
        return scanner.nextLine();
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
    
    public String solicitarEstado() {
        System.out.print("Ingrese el estado del pedido (pendiente/completo): ");
        return scanner.nextLine();
    }

    public void mostrarHistorial(List<Pedido> historial) {
        System.out.println("Historial de Pedidos (Completados/Eliminados):");
        mostrarPedidos(historial);
    }

    public void mostrarMenu() {
        System.out.println("\nOpciones:");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Pedidos");
        System.out.println("3. Eliminar Pedido");
        System.out.println("4. Actualizar Pedido");
        System.out.println("5. Buscar Pedido");
        System.out.println("6. Contar Pedidos Pendientes");  
        System.out.println("7. Marcar Pedido como Completo");  
        System.out.println("8. Mostrar Pedidos por Estado"); 
        System.out.println("9. Mostrar Historial de Pedidos");  
        System.out.println("10. Salir");  
    }


    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
