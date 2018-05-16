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

	public abstract class Transporte {
		private String codigo;
		private Mapa mapa;
	
	

	public Transporte(String codTransporte, Mapa map) {
		this.codigo = codTransporte;
		this.mapa = map;
		
	}
	
	public double coste(String posDestino) {
		return coste(codigo,posDestino);
	}
	
	public abstract double coste(String cod1, String cod2);
		
	
	public String getCodigo(){
		return codigo;
	}
	
	public Mapa getMapa() {
		return mapa;
	}
}
