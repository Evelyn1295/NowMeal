package gestionpedidos;

import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Transporte;

public class GestionReparto {
	// CÓDIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;
	
	
	public GestionReparto(Mapa mapa){
		this.mapa = mapa;
		this.gestoresLocales = new GestionRepartoLocal[4]; 
		for(int i=0; i<4;i++) {
			this.gestoresLocales[i] = new GestionRepartoLocal();
		}
		
	}
	
	//CÓDIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}
	
	// CÓDIGO DE APOYO
	public String getEstadoGestorLocal(int i){
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}
	
	// CÓDIGO DE APOYO
	public String getEstadoGestorLocalNum(int i){
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
	}
	//Este método nos permite saber en que gestor está un objeto 
	public int estaLocalidad(Object o) {
		int coordX = mapa.getMaxCoordX();
		int coordY = mapa.getMaxCoordY();
		int cuadrante=0;
		
		if(o instanceof Transporte) {
			int xObjeto = mapa.getPosicion(((Transporte) o).getCodigo()).getX();
			int yObjeto = mapa.getPosicion(((Transporte) o).getCodigo()).getY();
			
			if (xObjeto <= mapa.getMaxCoordX() / 2)  {
				if (yObjeto<= mapa.getMaxCoordY() / 2) {
					return cuadrante;
				}
				else {
					return cuadrante = 2;
				}
			} else {
				if (yObjeto<= mapa.getMaxCoordY() / 2) {
					return cuadrante = 1;
				}
				else {
					return cuadrante = 3;
				}
					
			}		
		}
		if(o instanceof Pedido) {
			int xObjeto = mapa.getPosicion(((Pedido) o).getRestaurante().getCodigo()).getX();
			int yObjeto = mapa.getPosicion(((Pedido) o).getRestaurante().getCodigo()).getY();
			
			if (xObjeto <= mapa.getMaxCoordX() / 2)  {
				if (yObjeto<= mapa.getMaxCoordY() / 2) {
					return cuadrante;
				}
				else {
					return cuadrante = 2;
				}
			} else {
				if (yObjeto<= mapa.getMaxCoordY() / 2) {
					return cuadrante = 1;
				}
				else {
					return cuadrante = 3;
				}
					
			}
		}
		
		return -1;
	}
	
	public void addTransporteLocalidad(Transporte transporte) {
		//TO-DO	
		if(estaLocalidad(transporte)>-1) {
			gestoresLocales[estaLocalidad(transporte)].add(transporte);
		}
	}
	
	
	public void asignarPedido(Pedido pedido){
		//TO-DO
		if(estaLocalidad(pedido)>-1) {
			gestoresLocales[estaLocalidad(pedido)].asignarPedido(pedido);
		}
	}
	
	public void notificarEntregaPedido(Pedido pedido){
		//TO-DO
		if(estaLocalidad(pedido)>-1){
			gestoresLocales[estaLocalidad(pedido)].notificarEntregaPedido(pedido);
		}
	}
	
}
