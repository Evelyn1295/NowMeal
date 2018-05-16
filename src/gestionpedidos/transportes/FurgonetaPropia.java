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

public class FurgonetaPropia extends Furgoneta{
	private double velocidadMedia = 30;
	private static final double EUROSPHORA = 40;
	
	public FurgonetaPropia(String codigo, Mapa mapa, double tara) {
		super(codigo,mapa,tara);
	}
	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		if(getTara() < 1000) {
			return getMapa().distancia(codPosOrigen,codPosDestino)*EUROSPHORA/velocidadMedia;
		}
			return getMapa().distancia(codPosOrigen,codPosDestino)*EUROSPHORA/velocidadMedia*1.10;
	}
	public void setVelocidadMedia(double velocidad){
		this.velocidadMedia=velocidad;
	}
}
