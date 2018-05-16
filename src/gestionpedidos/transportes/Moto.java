package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;
import anotacion.*; 

@Programacion2 (
	nombreAutor1 = "Evelyn Gisel",                 // (del alumno 1)
	apellidoAutor1 = "Flores",  // (del alumno 1)
	emailUPMAutor1 = "eg.flores@alumnos.upm.es",   // (del alumno 1)	
	nombreAutor2 = "Guillermo Jose",                 // (del alumno 2 si lo hay)
	apellidoAutor2 = "Alonso Delgado",  // (del alumno 2 si lo hay)
	emailUPMAutor2 = "gj.alonso@alumnos.upm.es"    // (del alumno 2 si lo hay)
)

public class Moto extends Transporte{

	private double eurosPKm=2;

	public Moto(String codigo, Mapa mapa) {
		 super(codigo,mapa);
	}
	
	@Override
	public double coste(String codOrigen, String codDestino) {
		double costeMoto = getMapa().distancia(codOrigen, codDestino)*eurosPKm;
		return costeMoto;
	}
	public void setEurosPKm(double euros) {
		this.eurosPKm = euros;
	}
	
	public double getEurosPKm() {
		return eurosPKm;
	}
	
}
