package nEnLinea.ctrl;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import nEnLinea.model.Partida;
import nEnLinea.model.PartidaService;
import nEnLinea.model.PartidaServiceI;

public class IndexCtrl extends SelectorComposer<Component>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox name1Input;
	@Wire
	private Textbox name2Input;
	@Wire
	private Intbox tamanoM;
	@Wire
	private Listbox lbxPartidas;
	
	private PartidaService partidaService= new PartidaServiceI();

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		//Method to execute actions after to render a component
		super.doAfterCompose(comp);
		LoadData();
		
	}
	
	public void LoadData() {
		List<Partida> part= partidaService.findAll();
		lbxPartidas.setModel(new ListModelList<Partida>(part));
		
	}
	
	@Listen("onClick=#botonInit")
	public void Init() {
		System.out.println(name1Input.getValue());
	
		Executions.sendRedirect("/tablero.zul");
		
	}
	
	

}
