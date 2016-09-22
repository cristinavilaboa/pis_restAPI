package Controladores;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataListaDataProblema;
import Datatypes.DataProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.CargarDatos;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;

import Persistencia.CargarDatosBD;

import Modelo.Problema;

@RestController
public class ControladorSistemaJuego implements IControladorSistemaJuego {
	@RequestMapping(value="/cargardatos", method=RequestMethod.POST)
	public void CargarDatos() throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException{
			CargarDatosBD.Cargar();
	}
	
	//METODOS A IMPLEMENTAR
	public void avanzarJuego(String id_jugador, int id_problema, int id_mundo){
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		if (mp.ultimaNivel(id_problema)){//Si es el ultimo problema del nivel, paso de nivel
			ManejadorMundo mm = ManejadorMundo.getInstancia();
			EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
			Mundo mundo = mm.obtenerMundo(id_mundo);
			estado.agregarNivelActivo(mundo);
			Nivel nivel = mp.buscarProblema(id_problema).getNivel();
			if(mundo.ultimoNivelMundo(nivel)){//Si es el ultimo problema del mundo, avanzo el mundo
				List<Mundo> mundos_siguientes = mundo.getMundos_siguientes();
				if(!estado.getMundos_completos().contains(mundo)){
					estado.getMundos_completos().add(mundo);
					estado.ganarExperiencia(mundo.getPuntos_exp());
					Logro mundo_terminado = new Logro("Mundo "+mundo.getNombre()+" completado");
					estado.ganarLogro(mundo_terminado);		
				}
				
				
				for(Mundo m: mundos_siguientes){//Desbloqueo todos los mundos siguientes
					if(!estado.getNiveles_actuales().containsKey(m.getId())){//Solo se desbloque si no esta desbloqueado de antes
						estado.agregarMundoActivo(m);
					}
					
				}
			}
		}
	}
	
	
	
	
	@RequestMapping(value="/siguienteProblema", method=RequestMethod.GET)
	public DataProblema siguienteProblema(@RequestParam(value="nick") String nick,@RequestParam(value="nivel") int nivel, @RequestParam(value="id_mundo") int id_mundo){
		
		
		ManejadorProblema mp=ManejadorProblema.getInstancia();	
		ManejadorMundo mm=ManejadorMundo.getInstancia();
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		
		Mundo m= mm.obtenerMundo(id_mundo);	
		Jugador j=mu.buscarJugador(nick);
		List<Nivel> lista=m.getNiveles();
		
	
		Nivel nivel_nuevo=lista.get(nivel); 
		List<Problema> lista_problema=nivel_nuevo.getProblemas();
		
		Problema p=lista_problema.get(0);
		
		return p.getDataProblema();
	
	}
	
	
	
	@RequestMapping(value="/siguienteProblemaGeneral", method=RequestMethod.GET)
	public DataListaDataProblema siguienteProblemaGeneral(@RequestParam(value="nick") String nick,@RequestParam(value="nivel") int nivel, @RequestParam(value="id_mundo") int id_mundo){
		// la id_jugador en esta iteracion no se usa
		// para esta iteracion se devuelve el problema siguiente, siendo id_problema el problema resuelto correctamente.
		ManejadorProblema mp=ManejadorProblema.getInstancia();	
		ManejadorMundo mm=ManejadorMundo.getInstancia();
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		
		
		Mundo m= mm.obtenerMundo(id_mundo);	
		Jugador j=mu.buscarJugador(nick);
		List<Nivel> lista=m.getNiveles();
		
	
		Nivel n1=lista.get(nivel-1); 
		List<Problema> lista_problema=n1.getProblemas(); //busco la cantidad de problemas del nivel donde estoy
		
		
		EstadoJugador estado=j.getEstado();  //resueltos por nivel 
		List<Problema> lista_resueltos= estado.getProblemas_resueltos(); //resueltos del nivel actual
		
		List<DataProblema> resultado=new ArrayList<DataProblema>();
		/*
		if (!(lista_problema.size()==lista_resueltos.size())){   // faltan responder problemas de este nivel	
			for (Problema p: lista_problema){
				if (!lista_resueltos.contains(p)){
					 resultado.add(p.getDataProblema());
				}
			}		
		}else{ // avanzo de nivel	
			*/
			Nivel n2=m.siguienteNivel(n1);
			List<Problema> lista_problema2=n2.getProblemas();
			
			for (Problema p: lista_problema2){
					 resultado.add(p.getDataProblema());
				
			}				
		//}
		
		return new  DataListaDataProblema(resultado);	
	}
	

	@RequestMapping(value="/iniciarjuego", method=RequestMethod.POST)
	public void iniciarJuego()
	{
		CargarDatos cd = new CargarDatos();
	}


}
