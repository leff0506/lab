package physics;

import java.util.ArrayList;

import ui.CanvasM;
import ui.UI;

public class Display {
	private ArrayList<Charge> data ;
	public static CanvasM canv;
	public Display(ArrayList<Charge> data) {
		this.data = data;
		display();
	}
	public void display() {
		canv = new CanvasM();
		canv.setBounds(50, 50, UI.panel.getWidth()-100,UI.panel.getHeight()-100);
		canv.setData(data);
		UI.addToB(canv);
	}
}
