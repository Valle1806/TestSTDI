package nEnLinea.ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import nEnLinea.model.Partida;
import nEnLinea.model.PartidaService;
import nEnLinea.model.PartidaServiceI;



public class TableroCtrl extends BaseCtrl {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int [][] board = new int[3][3];
	private int turn=1;
	private int n=3;
	private int draw;
	private Grid gridTablero;
	@Wire
	private Label lblTurn;
	
	private PartidaService partidaService= new PartidaServiceI();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		gridGenerate();
		
	}
	
	private void gridGenerate() {
		
		Rows rows;
		Row row;
		nEnLinea.ctrl.Button boton;
		
		rows = new Rows();
		rows.setStyle("width=100px");
		rows.setParent(gridTablero);
		for(int j=0; j<n; j++) {
		row = new Row();
		rows.setStyle("width=100px");
		row.setParent(rows);
	    for (int i = 0; i < n; i++) {
	          boton= new nEnLinea.ctrl.Button(j,i);	       
	          boton.setParent(row);
	    }}
		
		//Inicializar campos
		 for(int i=0;i<n;i++){
             for(int j=0;j<n;j++){
                 board[i][j]=-1;
             }
         }    
	}
	
	public void fillBoard(int x, int y) {
		if(turn==1 && board[x][y]==-1) {
			board[x][y]=1;
			draw+=1;
			turnChange();
			lblTurn.setValue("Turno jugador" + turn);
			
		}else if(board[x][y]==-1){
			board[x][y]=2;
			draw+=1;
			turnChange();
			lblTurn.setValue("Turno jugador" + turn);
		}
	}
	
	public void winValidate() {
		
		int acumH;
		int acumV;
		int acumDP=0;
		int acumDS=0;;
		if(draw==n*n) {
			//Empate
		}else {
			for(int i=0; i<n;i++ ) {
				acumH=0;
				acumV=0;
				for(int j=0; j<n; j++) {
					acumH+=board[i][j]; //Horizontal
					acumV+=board[j][i]; //Vertical
					if(i==j) { //Diagonal principal
						acumDP+=board[i][j];
					}
					if(i+j==n) {//Diagonal secundaria
						acumDS+=board[i][j];
						
					}
					if(acumH==n || acumV==n || acumDP==n || acumDS==n) {
						//gana jugador 1
						break;
					}else if(acumH==n*2 || acumV==n*2 || acumDP==n*2 || acumDS==n*2) {
						//gana jugador 2
						break;
					}
				}
			}
		}
	
	}
	//Onclick
	
	public void turnChange() {
		if (turn==1) {
			turn=2;
		}else {
			turn=2;
		}
	}
	
	public void winnerRecord() {
		partidaService.createPartida("pepe", "pedro", "[xoxoxoxox]", "pepe");
		Executions.sendRedirect("./index.zul");
		
	}

	
	
	
	

}
