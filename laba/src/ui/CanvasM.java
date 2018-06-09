package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import physics.Charge;


public class CanvasM extends Canvas{
	ArrayList<Charge> data;
	public static final int strength = 10;
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(Charge c:data) {
			if(c.positive==0) {
				int a=0;
				int radius=c.strength*strength+Charge.radius;
				g.setColor(Color.BLUE);
				
				g.drawOval(c.x+Charge.radius/2-radius/2, c.y+Charge.radius/2-radius/2, radius, radius);
			
				
				
				
			}else {
				g.setColor(Color.RED);
				int a=0;
				int radius=c.strength*strength+Charge.radius;
				g.setColor(Color.RED);
				
				g.drawOval(c.x+Charge.radius/2-radius/2, c.y+Charge.radius/2-radius/2, radius, radius);
			}
			if(c.positive==0) {
				g.setColor(Color.BLUE);
			}else {
				g.setColor(Color.RED);
			}
			g.fillOval(c.x, c.y, Charge.radius, Charge.radius);
			if(c.positive==0) {
				g.setColor(Color.RED);
				g.drawLine(c.x-5+Charge.radius/2, c.y+Charge.radius/2, c.x+5+Charge.radius/2,c.y+Charge.radius/2);
				g.drawLine(c.x-5+Charge.radius/2, c.y+1+Charge.radius/2, c.x+5+Charge.radius/2,c.y+1+Charge.radius/2);
				g.drawLine(c.x-5+Charge.radius/2, c.y-1+Charge.radius/2, c.x+5+Charge.radius/2,c.y-1+Charge.radius/2);
			}else {
				g.setColor(Color.BLUE);
				g.drawLine(c.x-5+Charge.radius/2, c.y+Charge.radius/2, c.x+5+Charge.radius/2,c.y+Charge.radius/2);
				g.drawLine(c.x-5+Charge.radius/2, c.y+1+Charge.radius/2, c.x+5+Charge.radius/2,c.y+1+Charge.radius/2);
				g.drawLine(c.x-5+Charge.radius/2, c.y-1+Charge.radius/2, c.x+5+Charge.radius/2,c.y-1+Charge.radius/2);
				
				g.drawLine(c.x+Charge.radius/2, c.y-5+Charge.radius/2, c.x+Charge.radius/2,c.y+5+Charge.radius/2);
				g.drawLine(c.x-1+Charge.radius/2, c.y-5+Charge.radius/2, c.x-1+Charge.radius/2,c.y+5+Charge.radius/2);
				g.drawLine(c.x+1+Charge.radius/2, c.y-5+Charge.radius/2, c.x+1+Charge.radius/2,c.y+5+Charge.radius/2);
			}
			
		}
	}
	public void setData(ArrayList<Charge> data) {
		this.data=data;
	}
}
