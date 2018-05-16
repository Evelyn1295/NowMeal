package gestionpedidos;

import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import anotacion.*; 

@Programacion2 (
	nombreAutor1 = "Evelyn Gisel",                 // (del alumno 1)
	apellidoAutor1 = "Flores",  // (del alumno 1)
	emailUPMAutor1 = "eg.flores@alumnos.upm.es",   // (del alumno 1)	
	nombreAutor2 = "Guillermo Jose",                 // (del alumno 2 si lo hay)
	apellidoAutor2 = "Alonso Delgado",  // (del alumno 2 si lo hay)
	emailUPMAutor2 = "gj.alonso@alumnos.upm.es"    // (del alumno 2 si lo hay)
)



public class GestionRepartoLocal {	
	// CÓDIGO DE APOYO
	private ArrayList<Moto> motosDisponibles;
	private ArrayList<Furgoneta> furgonetasDisponibles;

	private ArrayList<Pedido> pedidosEsperandoMoto;
	private ArrayList<Pedido> pedidosEsperandoFurgoneta;
	
	// CÓDIGO DE APOYO
	private static ArrayList<String> getCodList(ArrayList<?> disponibles) {
		ArrayList<String> salida = new ArrayList<>();
		for(int i=0; i<disponibles.size(); i++)
			salida.add(salida.size(),((Transporte) disponibles.get(i)).getCodigo());
		return salida;
	}
	
	// CÓDIGO DE APOYO
	private static ArrayList<String[]> getClienteRestauranteList(ArrayList<?> disponibles){
		 ArrayList<String[]> salida = new  ArrayList<>();
		for(int i=0; i<disponibles.size(); i++)
			salida.add(salida.size(),new String[]{((Pedido) disponibles.get(i)).getCliente().getCodigo() ,
					((Pedido) disponibles.get(i)).getRestaurante().getCodigo()});
		return salida;
	}
	
	// CÓDIGO DE APOYO
	private static String myArrayListToString (ArrayList<?> list){
		String salida = "";
		for(int i=0; i<list.size(); i++){
			salida += " " ;
			if (list.get(i) instanceof String[]){
				String[] item = (String[])list.get(i);
				for(int j=0; j<item.length; j++){
					salida += item[j] ;
				}	
			}else if (list.get(i) instanceof String){
				salida += (String)list.get(i);
			}
		}
			
		return salida;
	}
	
	// CÓDIGO DE APOYO
	public String getDisponibles(){
		return "Motos Disponibles:" + myArrayListToString(getCodList(motosDisponibles)) + System.lineSeparator() +
			"Furgonetas Disponibles:" + myArrayListToString(getCodList(furgonetasDisponibles)) + System.lineSeparator();
			
	}
	
	// CÓDIGO DE APOYO
	public String getEsperando(){
		return "Pedidos esperando moto:" + myArrayListToString(getClienteRestauranteList(pedidosEsperandoMoto)) + System.lineSeparator() +
				"Pedidos esperando furgoneta:" + myArrayListToString(getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
	}
	
	// CÓDIGO DE APOYO
	public ArrayList<String> getCodMotosDisponibles(){
		return getCodList(motosDisponibles);
	}	
	
	// CÓDIGO DE APOYO
	public ArrayList<String> getCodFurgoDisponibles(){
		return getCodList(furgonetasDisponibles);
			
	}
	
	// CÓDIGO DE APOYO
	public ArrayList<String[]> getCodEsperandoMoto(){
		return getClienteRestauranteList(pedidosEsperandoMoto);
	}
	
	public ArrayList<String[]> getCodEsperandoFurgo(){
		return getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESOMAXMOTO = 20;

	// CÓDIGO DE APOYO
	public GestionRepartoLocal(){		
		
		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new ArrayList<>();
		this.pedidosEsperandoMoto = new ArrayList<>();
	}

	public void add(Transporte transporte){
		//TO-DO
		if(transporte instanceof Moto) {
			Moto mot = (Moto) transporte;
			motosDisponibles.add(motosDisponibles.size(), mot);
		}
		else if(transporte instanceof Furgoneta){
			Furgoneta furg = (Furgoneta) transporte;
			furgonetasDisponibles.add(furgonetasDisponibles.size(), furg);
		}
		
		
	}
	
	public Transporte minTransporte(Pedido pedido, ArrayList<?> vector, int pos) {
		Transporte res = null;
		
		if(pos == vector.size()-1) {
			res = (Transporte) vector.get(pos);
		}
		else {
			Transporte k = minTransporte(pedido, vector, pos + 1);
			if(pedido.coste((Transporte)vector.get(pos))< pedido.coste(k)) {
				res = (Transporte) vector.get(pos);
				
			}
			else {
				res = k;
			}
		}
		return res;
	}
	
	public Furgoneta taraMax(ArrayList<Furgoneta> furgonetas) {
		
		double taraMax = furgonetas.get(0).getTara();
		int j =0;
		for(int i = 0; i<furgonetas.size();i++) {
			if(furgonetas.get(i).getTara()>taraMax) {
				taraMax = furgonetas.get(i).getTara();
				j=i;
			}
		}
		
		return furgonetas.get(j);
	}
			
	public void asignarPedido(Pedido pedido){
		if(pedido.getPeso()<=PESOMAXMOTO) {
			if(motosDisponibles.size()>0) {
				Moto moto = (Moto) minTransporte(pedido,motosDisponibles,0);
				pedido.setTransporte(moto);
				motosDisponibles.remove(moto);
				pedidosEsperandoMoto.remove(pedido);
			}
			else if (pedidosEsperandoMoto.remove(pedido)==false) {
				pedidosEsperandoMoto.add(pedidosEsperandoMoto.size(), pedido);
			}	
		}
		if(pedido.getPeso()> PESOMAXMOTO){
			if(furgonetasDisponibles.size()>0 && pedido.getPeso()< taraMax(furgonetasDisponibles).getTara()){
				if(furgonetasDisponibles.size()>0) {
					Furgoneta furgo = (Furgoneta) minTransporte(pedido,furgonetasDisponibles,0);
					pedido.setTransporte(furgo);
					furgonetasDisponibles.remove(furgo);
					pedidosEsperandoFurgoneta.remove(pedido);
				}
				else if(pedidosEsperandoFurgoneta.remove(pedido)==false) {
					pedidosEsperandoFurgoneta.add(pedidosEsperandoFurgoneta.size(), pedido);
				}
			}
		}
	}
	public void notificarEntregaPedido(Pedido pedido) {	
		//TO-DO
		if(pedido.getTransporte()!=null) {
			if(pedido.getTransporte() instanceof Moto) {
				Moto moto= (Moto) pedido.getTransporte(); 
				motosDisponibles.add(motosDisponibles.size(), moto);
			
				if(pedidosEsperandoMoto.size()>0) {
					for(int i=0; i<motosDisponibles.size();i++){
						asignarPedido(pedidosEsperandoMoto.get(i));
					}
				}
			}
			else if(pedido.getTransporte() instanceof Furgoneta) {
				Furgoneta furgoneta = (Furgoneta) pedido.getTransporte();
				furgonetasDisponibles.add(furgonetasDisponibles.size(), furgoneta);
			
				if(pedidosEsperandoFurgoneta.size()>0) {
					for(int i=0; i<furgonetasDisponibles.size();i++) {
						asignarPedido(pedidosEsperandoFurgoneta.get(i));
					}
				}
			}
		}
		else{
			throw new IllegalArgumentException("El pedido no tiene transporte asignado");
		}
		
	}
}