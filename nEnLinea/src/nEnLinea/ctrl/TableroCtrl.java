package nEnLinea.ctrl;

import java.util.Arrays;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import nEnLinea.model.PartidaService;
import nEnLinea.model.PartidaServiceI;



public class TableroCtrl extends BaseCtrl {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n=3;
	private String [][] board = new String[n][n];
	private String name1,name2;
	private int turn=1;
	private String mark="X";
	
	private int draw;
	
	@Wire
	private Grid gridTablero;
	@Wire
	private Label lblTurn;
	@Wire
	private Label lblWinner;
	
	private PartidaService partidaService= new PartidaServiceI();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Session session = Sessions.getCurrent();
		n=(int) session.getAttribute("size");
		System.out.println(n);
		name1= (String) session.getAttribute(name1);
		name2= (String) session.getAttribute(name2);
		board = new String[n][n];
		lblTurn.setValue("Turno jugador " + turn);
		
		gridGenerate();
		
	}
	
	
	private void gridGenerate() {
		
		Rows rows;
		Row row;
		rows = new Rows();
		rows.setStyle("width=100px; padding: 0px; magin: 0px");
		rows.setParent(gridTablero);
		for(int i=0; i<n; i++) {
			row = new Row();
			rows.setStyle("width=100px");
			row.setParent(rows);
			bordersGrid(row);
	    for (int j = 0; j < n; j++) {
	    	 
	    	  nEnLinea.ctrl.Button div= new nEnLinea.ctrl.Button(i,j);
	          Label casilla = new Label();
	          casilla.setValue("-");
	          casilla.setParent(div);
	          
	          div.addEventListener(Events.ON_CLICK, new SerializableEventListener<Event>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 994613688330960891L;

				@Override
				public void onEvent(Event arg0) throws Exception {
					//	boton.setLabel(mark);
						casilla.setValue(mark);
						TableroCtrl.this.fillBoard(div.getX(), div.getY());
											
				}
			});
	          div.setParent(row);
	    }}
		
		//Inicializar campos
		 for(int i=0;i<n;i++){
             for(int j=0;j<n;j++){
                 board[i][j]="-";
             }
         }    
	}
	
	public void bordersGrid( Row row) {
		
			row.setStyle("border: solid 2px;");
			
		
	}
	
	public void fillBoard(int x, int y) {
		if(turn==1 && board[x][y]=="-") {
			board[x][y]="X";
			draw+=1;
			winnerValidate();
			turnChange();
			
			
			lblTurn.setValue("Turno jugador " + turn);
		}else if(board[x][y]=="-"){
			board[x][y]="O";
			draw+=1;
			winnerValidate();
			turnChange();
			lblTurn.setValue("Turno jugador " + turn);
		}
	}
	
	public void winnerValidate() {
		System.out.println(Arrays.deepToString(board));
		int acumH;
		int acumV;
		int acumDP=0;
		int acumDS=0;;
		if(draw==n*n) {
			winnerRecord(3);
			System.out.println("Empate");
		}else {
			for(int i=0; i<n;i++ ) {
				acumH=0;
				acumV=0;
				for(int j=0; j<n; j++) {
					
					if(board[i][j]==mark ) {
						acumH+=1; //Horizontal
					}
					
					if(board[j][i]==mark ) {
					
						acumV+=1; //Vertical
					}
					
					if(i==j && board[i][j]==mark) { //Diagonal principal
						acumDP+=1;
					}
					if(i+j==n-1 && board[i][j]==mark) {//Diagonal secundaria
						acumDS+=1;
						
					}
					if(acumH==n || acumV==n || acumDP==n || acumDS==n ) {
						//System.out.println( "H "+ acumH +" V"+ acumV+" DP "+ acumDP +" DS "+ acumDS);
						//System.out.println(i);
						winnerRecord(turn);
						System.out.println("GANA JUGADOR "+turn);
						break;
				
				}
				
				
			}
				}
			}
			/*for(int i=0; i<n;i++ ) {
				acumH=0;
				acumV=0;
				for(int j=0; j<n; j++) {
					
					if(board[i][j]==turn || board[i][j]==-1) {
						acumH+=board[i][j]; //Horizontal
					}
					System.out.println("turn" +turn);
					if(board[j][i]==turn || board[j][i]==-1) {
					
						acumV+=board[j][i]; //Vertical
					}
					
					if(i==j && board[i][j]==turn || i==j && board[i][j]==-1) { //Diagonal principal
						acumDP+=board[i][j];
					}
					if(i+j==n) {//Diagonal secundaria
						acumDS+=board[i][j];
						
					}
					if(acumH==n || acumV==n || acumDP==n || acumDS==n ) {
						System.out.println( "H "+ acumH +" V"+ acumV+" DP "+ acumDP +" DS "+ acumDS);
						System.out.println(i);
						System.out.println("GANA JUGADOR 1");
						break;
					}else if(acumH==n*2 || acumV==n*2 || acumDP==n*2 || acumDS==n*2 ) {
						System.out.println("GANA JUGADOR 2");
						break;
					}
				}
				System.out.println("Separacion fila");
				//System.out.println("Suma vertical  "+ String.valueOf(i)+" "+  acumV);
				System.out.println("Suma horizontal  "+ String.valueOf(i)+" "+  acumH);
				System.out.println("Suma primera diag  "+ String.valueOf(i)+" "+  acumDP);
				System.out.println("Suma segunda diag  "+ String.valueOf(i)+" "+  acumDS);
			}*/
		
	
	}
	//Onclick
	
	public void turnChange() {
		if (turn==2) {
			turn=1;
			mark="X";
		}else {
			turn=2;
			mark="O";
		}
	}
	
	public void winnerRecord(int winner) {
		String winner1;
		if(winner==1) {
			winner1=name1;
			
		}else if(winner==2) {
			 winner1=name2;
		}else {
			winner1="empate";
		}
		
		 try {
			 
			 partidaService.createPartida(name1, name2,Arrays.deepToString(board), winner1);
			
				lblWinner.setValue("Ganó jugador "+ winner1);
	           
	            Thread.sleep(6*1000);
	            
	      } catch (Exception e) {
	            System.out.println(e);
	         }
		 Executions.sendRedirect("./index.zul");
		
	}
	

	@Listen("onClick=#btnREST")
	public void Init() {
		
		
	}

	
	
	
	

}
