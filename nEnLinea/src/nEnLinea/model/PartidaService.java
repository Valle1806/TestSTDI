package nEnLinea.model;

import java.util.List;

public interface PartidaService {
	
	public List <Partida> findAll();
	public void createPartida(String name1, String name2, String state, String winner);
	

}
