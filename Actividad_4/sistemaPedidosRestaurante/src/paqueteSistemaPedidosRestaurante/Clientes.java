package paqueteSistemaPedidosRestaurante;

public class Clientes {
	private String nombre;
	private int numeroContacto;
	public Clientes(String nombre, int numeroContacto) {
		super();
		this.nombre = nombre;
		this.numeroContacto = numeroContacto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumeroContacto() {
		return numeroContacto;
	}
	public void setNumeroContacto(int numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
	@Override
	public String toString() {
		return "Clientes [nombre=" + nombre + ", numeroContacto=" + numeroContacto + "]";
	}
}
