package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import physics.Charge;


public class CanvasM extends JPanel{
	public static ArrayList<Charge> data;
	public static final int strength = 20;
	@Override
	public synchronized void paintComponent(Graphics g) {
		
	
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(Charge c:data) {
			System.out.println("x : "+c.x+" y:"+c.y);
			if(c.positive==0) {
				int a=0;
				int radius=c.strength*strength+Charge.radius;
				g.setColor(Color.BLUE);
				int odd=0;
				
				for(int i = 0 ; i < radius;i+=2) {
					
					g.setColor(new Color(0,0,255,(int)(120-(i/(float)radius)*120)));
					g.drawOval(c.x+Charge.radius/2-i/2,c.y+Charge.radius/2-i/2,i,i);
				}
//				g.setColor(new Color(0,0,255,70));
//				g.drawOval(c.x+Charge.radius/2-radius/2,c.y+Charge.radius/2-radius/2,radius,radius);
	
			
				
				
				
			}else {
				g.setColor(Color.RED);
				int a=0;
				int radius=c.strength*strength+Charge.radius;
				g.setColor(Color.RED);
				
				for(int i = 0 ; i < radius;i+=2) {
					
					g.setColor(new Color(255,0,0,(int)(120-(i/(float)radius)*120)));
					g.drawOval(c.x+Charge.radius/2-i/2,c.y+Charge.radius/2-i/2,i,i);
				}
//				g.setColor(new Color(255,0,0,70));
//				g.drawOval(c.x+Charge.radius/2-radius/2,c.y+Charge.radius/2-radius/2,radius,radius);
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
	public synchronized static void setData(ArrayList<Charge> data1) {
		data=data1;
	}
}
