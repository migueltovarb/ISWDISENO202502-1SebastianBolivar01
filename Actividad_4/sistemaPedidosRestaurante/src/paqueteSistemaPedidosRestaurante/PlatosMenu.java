package paqueteSistemaPedidosRestaurante;

public class PlatosMenu {
	private String nombre;
	private Tipo tipo;
	private int precio;
	public PlatosMenu(String nombre, Tipo tipo, int precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "PlatosMenu [nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + "]";
	}
}
