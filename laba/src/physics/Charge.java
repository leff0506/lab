package physics;

import ui.UI;

public class Charge {
	public int strength;
	public int positive;
	public int oPos;
	public  int x;
	public int y;
	
	public static final int PADDING =120;
	public static final int radius = 65;
	public Charge(int pos,int positive,int strength) {
		this.oPos=pos;
		this.positive=positive;
		this.strength=strength;
		switch(UI.st_am) {
		case 1:
			switch(pos) {
			case 0:x=(UI.panel.getWidth()-100)/2-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;

			}
			break;
		case 2:
			switch(pos) {
			case 0:x=PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			case 1:x=(UI.panel.getWidth()-100)-PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			
			}
			break;
		case 3:
			switch(pos) {
			case 0:x=PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			case 1:x=(UI.panel.getWidth()-100)/2-radius/2;y=PADDING-radius/2;break;
			case 2:x=(UI.panel.getWidth()-100)-PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			
			}
			break;
		case 4:
			switch(pos) {
			case 0:x=PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			case 1:x=(UI.panel.getWidth()-100)/2-radius/2;y=PADDING-radius/2;break;
			case 2:x=(UI.panel.getWidth()-100)-PADDING-radius/2;y=(UI.panel.getHeight()-100)/2-radius/2;break;
			case 3:x=(UI.panel.getWidth()-100)/2-radius/2;y=(UI.panel.getHeight()-100)-PADDING-radius/2;break;
			}
			break;
		}
		
	}
}
