package paqueteSistemaPedidosRestaurante;

import java.util.List;

public class Pedidos {
	private List<PlatosMenu> platos;

	public Pedidos(List<PlatosMenu> platos) {
		super();
		this.platos = platos;
	}

	public List<PlatosMenu> getPlatos() {
		return platos;
	}

	public void setPlatos(List<PlatosMenu> platos) {
		this.platos = platos;
	}

	@Override
	public String toString() {
		return "Pedidos [platos=" + platos + "]";
	}
}
