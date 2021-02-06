package nEnLinea.ctrl;

import org.zkoss.zk.ui.event.Event;

public class Button extends org.zkoss.zul.Button{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int valor;
	
	public Button(int x, int y) {
		super();
		this.x=x;
		this.y=y;
		
	}
	public void onClick(Event event, int[][] tablero) {
		
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	

}
