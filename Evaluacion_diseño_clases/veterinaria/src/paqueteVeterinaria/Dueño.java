package paqueteVeterinaria;

import java.util.List;

public class Dueño {
	private String nombre;
	private String documento;
	private String telefono;
	private List<Mascota> mascotas;
	public Dueño(String nombre, String documento, String telefono, List<Mascota> mascotas) {
		super();
		this.nombre = nombre;
		this.documento = documento;
		this.telefono = telefono;
		this.mascotas = mascotas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Mascota> getMascotas() {
		return mascotas;
	}
	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}
	public void registrarDueño() {
		System.out.println("el dueño se registro exitosamente");
	}
	@Override
	public String toString() {
		return "Dueño [nombre=" + nombre + ", documento=" + documento + ", telefono=" + telefono + ", mascotas="
				+ mascotas + "]";
	}
}
