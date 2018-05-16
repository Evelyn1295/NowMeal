package gestionpedidos.transportes;
import gestionpedidos.mapa.*;
import anotacion.*; 

@Programacion2 (
	nombreAutor1 = "Evelyn Gisel",                 // (del alumno 1)
	apellidoAutor1 = "Flores",  // (del alumno 1)
	emailUPMAutor1 = "eg.flores@alumnos.upm.es",   // (del alumno 1)	
	nombreAutor2 = "Guillermo Jose",                 // (del alumno 2 si lo hay)
	apellidoAutor2 = "Alonso Delgado",  // (del alumno 2 si lo hay)
	emailUPMAutor2 = "gj.alonso@alumnos.upm.es"    // (del alumno 2 si lo hay)
)

public class FurgonetaSubcontratada extends Furgoneta {
	private double eurosPKm = 1;
	
	public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
		super(codigo,mapa,tara);
	}
	
	public double coste(String codPosOrigen, String codPosDestino) {
		
		if(getTara() <1000) {
			return getMapa().distancia(codPosOrigen, codPosDestino)*eurosPKm;
		}
		return getMapa().distancia(codPosOrigen, codPosDestino)*eurosPKm*1.10;
	}
	
	public void setEurosPKm(double dEuros) {
		this.eurosPKm=dEuros;
	}
}
