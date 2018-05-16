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

public abstract class Furgoneta extends Transporte{
	private double tara;
	
	public Furgoneta(String codigo,Mapa mapa,double tara) {
		super(codigo,mapa);
		this.tara = tara;
	}
	public double getTara() {
		return tara;
	}
	
	
}
