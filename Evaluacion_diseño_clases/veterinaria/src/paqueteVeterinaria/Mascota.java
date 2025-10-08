package paqueteVeterinaria;

import java.util.List;

public class Mascota {
	private String nombre;
	private String especie;
	private float edad;
	private Dueño dueño;
	private List<TipoControl> controles;
	public Mascota(String nombre, String especie, float edad, Dueño dueño, List<TipoControl> controles) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.dueño = dueño;
		this.controles = controles;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public float getEdad() {
		return edad;
	}
	public void setEdad(float edad) {
		this.edad = edad;
	}
	public Dueño getDueño() {
		return dueño;
	}
	public void setDueño(Dueño dueño) {
		this.dueño = dueño;
	}
	public List<TipoControl> getControles() {
		return controles;
	}
	public void setControles(List<TipoControl> controles) {
		this.controles = controles;
	}
    public void registrarMascota() {
        System.out.println("Mascota registrada con éxito:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Dueño: " + dueño.getNombre());
    }
    public void registrarControl(TipoControl control) {
        if (control != null) {
            controles.add(control);
            System.out.println("🩺 Se registró un nuevo control médico:");
            System.out.println(control);
        } else {
            System.out.println("⚠️ No se pudo registrar el control: información incompleta.");
        }
    }
    public void consultarHistorial() {
        System.out.println("📋 Historial médico de " + nombre + ":");
        if (controles.isEmpty()) {
            System.out.println("No hay controles registrados.");
        } else {
            for (TipoControl c : controles) {
                System.out.println("- " + c);
            }
        }
    }
    public void generarResumen() {
        System.out.println("Resumen médico de la mascota:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
        System.out.println("Dueño: " + dueño.getNombre());
        System.out.println("Número de controles registrados: " + controles.size());
    }
	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", especie=" + especie + ", edad=" + edad + ", controles=" + controles
				+ "]";
	}
}
