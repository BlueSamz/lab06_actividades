package lab06;

public class Pedido {
	private int idPedido;
    private String nombrePlato;
    private String tipoP;
    private String estado;

    public Pedido(int idPedido, String nombrePlato, String tipoP) {
    	this.idPedido = idPedido;
        this.nombrePlato = nombrePlato;
        this.tipoP = tipoP;
        this.estado = "pendiente";
        
    }
    
    public int getIdPedido() {
    	return idPedido;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }
    
    public String getTipoP() {
    	return tipoP;
    }
    
    public String getEstado() { 
    	return estado; 
    }
    
    public void setIdPedido(int idPedido) {
    	this.idPedido = idPedido;
    }
    
    public void setNombrePlato(String nombrePlato) {
    	if(!nombreValido(nombrePlato)) {
    		throw new IllegalArgumentException("El nombre del plato solo puede ser caracteres alfabeticos");
    	}
    }
    
    public void setTipoP (String tipoP) {
    	this.tipoP = tipoP;
    }
    
    public void setEstado(String estado) {
        if (estado.equals("pendiente") || estado.equals("completo") || estado.equals("eliminado")) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado inválido");
        }
    }
    
    private boolean nombreValido(String nombrePlato) {
    	return nombrePlato.matches("[a-zA-Z]+");
    }
    
}