package paqueteVeterinaria;

public class controlVeterinario {
	private String fecha;
	private TipoControl tipoControl;
	private String observaciones;
	public controlVeterinario(String fecha, TipoControl tipoControl, String observaciones) {
		super();
		this.fecha = fecha;
		this.tipoControl = tipoControl;
		this.observaciones = observaciones;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public TipoControl getTipoControl() {
		return tipoControl;
	}
	public void setTipoControl(TipoControl tipoControl) {
		this.tipoControl = tipoControl;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		return "controlVeterinario [fecha=" + fecha + ", tipoControl=" + tipoControl + ", observaciones="
				+ observaciones + "]";
	}
}
