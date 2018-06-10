package ui;



import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;

import physics.Charge;
import physics.Display;

public class UI {
	private static ArrayList<Component> dataBlack = new ArrayList<>();
	private static ArrayList<Component> dataWhite = new ArrayList<>();
	private static ArrayList<Charge> trans = new ArrayList<>();
	private static JFrame frame ;
	public static boolean keepP = true;
	public static boolean keep1 = true;
	public static boolean keep2 = true;
	public static boolean keep3 = true;
	public static int st_am;
	public static JPanel panel;
	private static JPanel white;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int am;
	
	public UI() {
		frame = new JFrame("Interaction of point charges");
		frame.setSize(900,900);
		frame.setLocation(screen.width/2-frame.getWidth()/2,screen.height/2-frame.getHeight()/2);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		frame.add(panel);
		frame.setVisible(true);
		askAm();
	}
	private void askAm() {
		white = new JPanel();
		white.setSize(300,300);
		white.setLocation(panel.getWidth()/2-white.getWidth()/2,panel.getHeight()/2-white.getHeight()/2);
		white.setLayout(null);
		white.setBackground(Color.WHITE);
		addToB(white);
		
		JLabel lbl = new JLabel("Amount of charges");
		lbl.setBounds(100, 20, 150, 30);
		addToW(lbl);
		
		for(int i =0;i<4;i++) {
			JButton but = new JButton(); 
			but.setFocusable(false);
			but.setText(String.valueOf(i+1));
			but.setBounds(115,i*60+60,70,40);
			but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					askVal(Integer.parseInt(but.getText()));
					
				}
			});
			addToW(but);
		}
	}
	private void askVal(int am){
		clearW();
		JLabel lbl = new JLabel("Value of charges");
		lbl.setBounds(100, 20, 150, 30);
		addToW(lbl);
		st_am = am;
		int[] vals = new int[am] ;
		int[] strength = new int[am] ;
		for(int i = 0 ; i < am;i++) {
			vals[i]=-1;
		}
		for(int i =0;i<am;i++) {
			JButtonM but = new JButtonM(); 
			but.id=i;
			but.setFocusable(false);
			but.setText("+");
			but.setBounds(30,i*60+60,45,45);
			JLabel lbl1 = new JLabel("");
			lbl1.setBounds(200, i*60+60, 80, 30);
			addToW(lbl1);
			but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vals[but.id]=1;
					lbl1.setText("positive");
					frame.repaint();
				}
			});
			addToW(but);
			JButtonM but1 = new JButtonM(); 
			but1.setFocusable(false);
			but1.setText("-");
			but1.setBounds(80,i*60+60,45,45);
			but1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vals[but.id]=0;
					lbl1.setText("negative");
					frame.repaint();
				}
			});
			addToW(but1);
			JTextField f = new JTextField();
			f.setBounds(130,i*60+60,50,45);
			f.setText("Radius");
			f.setHorizontalAlignment(JTextField.CENTER);
		    f.addFocusListener(new FocusListener() {

	            @Override
	            public void focusLost(FocusEvent arg0) {
	                // TODO Auto-generated method stub
	            	if(f.getText().equals("")) {
	            		f.setText("Radius");
	            	}
	            	 
	            }

	            @Override
	            public void focusGained(FocusEvent arg0) {
	                // TODO Auto-generated method stub
	               f.setText("");
	            }

				
	        });
			fields.add(f);
			addToW(f);
		}
		JLabel lbl3 = new JLabel("");
		lbl3.setFont(new Font("Verdana",Font.PLAIN,12));
		lbl3.setForeground(Color.RED);
		lbl3.setBounds(200, 275, 100, 30);
		addToW(lbl3);
		JButton submit = new JButton();
		submit.setText("submit");
		submit.setSize(80, 30);
		submit.setLocation(panel.getWidth()-submit.getWidth()-30, panel.getHeight()-submit.getHeight()-30);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i:vals) {
					if(i==-1) {
						lbl3.setText("Error");
						return;
					}else {
						try {
							for(int j = 0;j<am;j++) {
								Integer.parseInt(fields.get(j).getText());
								
							}
						}catch(Exception e1) {
							
							lbl3.setText("Error");
							return;
						}
						lbl3.setText("Done");
					}
				}
				for(int i = 0;i<am;i++) {
					Charge ch = new Charge(i,vals[i],Integer.parseInt(fields.get(i).getText()));
					trans.add(ch);
				}
				for(Charge c : trans) {
					System.out.println("pos : "+c.oPos+" val : "+c.positive+" strength: "+c.strength);
				}
				clearW();
				clearB();
				
				Display display = new Display(trans);
				animAsk();
			}
		});
		addToB(submit);
		
	}
	public static void addToB(Component c) {
		dataBlack.add(c);
		panel.add(c);
		frame.repaint();
	}
	public static void clearB() {
		for(Component c:dataBlack) {
			panel.remove(c);
		}
		frame.repaint();
	}
	public static void addToW(Component c) {
		if(white!=null) {
			dataWhite.add(c);
			white.add(c);
			frame.repaint();
		}
		
	}
	public static void clearW() {
		if(white!=null) {
			for(Component c:dataWhite) {
				white.remove(c);
			}
			frame.repaint();
		}
		
	}
	private void animAsk() {
		JButton start = new JButton();
		start.setText("start");
		start.setSize(80, 30);
		start.setLocation(panel.getWidth()-start.getWidth()-10, panel.getHeight()-start.getHeight()-10);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				anim();
			}
		});
		addToB(start);
	}
	private void anim() {
		switch(st_am) {
		case 2:
			drawThread();
			updateThread2();
			break;
		case 3:updateThread301();updateThread312();updateThread302();drawThread();stopThread();break;
		
		}
	}
	private synchronized void stopThread() {
		Thread th = new Thread(new Runnable() {
			int temp=0;
			@Override
			public void run() {
				int x1,x2,x3,y1,y2,y3;
				int x11,x21,x31,y11,y21,y31;
				while(keepP) {
					x1=trans.get(0).x;
					x2=trans.get(1).x;
					x3=trans.get(2).x;
					y1=trans.get(0).y;
					y2=trans.get(1).y;
					y3=trans.get(2).y;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x11=trans.get(0).x;
					x21=trans.get(1).x;
					x31=trans.get(2).x;
					y11=trans.get(0).y;
					y21=trans.get(1).y;
					y31=trans.get(2).y;
					if(x1==x11&&x2==x21&&x1==x31&&y1==y11&&y2==y21&&y3==y31) {
						keepP=false;
					}
				}
				
				
			}
		});
		th.start();
	}
	
	private synchronized void drawThread() {
		Thread th = new Thread(new Runnable() {
			int temp=0;
			@Override
			public void run() {
				long prev = System.currentTimeMillis();
				int am=0;
				while (keepP) {
					
					long t = System.currentTimeMillis();
					if(t-prev>=150) {
						frame.repaint();
						
						prev = t;
					}
					if(st_am==3) {
						keepP=keep1||keep2||keep3;
						if(temp ==0) {
							temp++;
							keepP=true;
						}
					}
				}
				
				
			}
		});
		th.start();
	}
	private synchronized void updateThread2() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long prev = System.currentTimeMillis();
				while (keepP) {
					long t = System.currentTimeMillis();
					if(t-prev>150) {
						
						if(trans.get(1).x-trans.get(0).x<=trans.get(0).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
							System.out.println("Intaraction");
							if(trans.get(1).positive==trans.get(0).positive) {
								System.out.println("unforce");
								if(trans.get(1).x-trans.get(0).x<=trans.get(0).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
									if(trans.get(0).x+trans.get(0).strength*CanvasM.strength/2+Charge.radius/2>=trans.get(1).x||trans.get(1).x+Charge.radius/2-trans.get(1).strength*CanvasM.strength/2<trans.get(0).x+Charge.radius) {
										trans.get(1).x+=3;
										trans.get(0).x-=3;
									}else {
										trans.get(1).x++;
										trans.get(0).x--;
									}
									
								}else {
									keepP=false;
								}
							}else {
								System.out.println("force");
								if(trans.get(1).x-trans.get(0).x>=Charge.radius) {
									System.out.println("Change)");
									if(trans.get(0).x+trans.get(0).strength*CanvasM.strength/2+Charge.radius/2>=trans.get(1).x||trans.get(1).x+Charge.radius/2-trans.get(1).strength*CanvasM.strength/2<trans.get(0).x+Charge.radius) {
										trans.get(1).x-=3;
										if(trans.get(1).x>=trans.get(0).x+Charge.radius) {
											trans.get(0).x+=3;
										}else {
											trans.get(1).x=trans.get(0).x+Charge.radius-1;
										}
										
										
									}else {
										trans.get(1).x--;
										trans.get(0).x++;
									}
									
								}else {
									keepP=false;
								}
							}
							CanvasM.setData(trans);
						}else {
							keepP=false;
						}
						prev = t;
					}
				}
				
				
			}
		});
		th.start();
	}
	private synchronized void updateThread301() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long prev = System.currentTimeMillis();
				while (keepP) {
					long t = System.currentTimeMillis();
					if(t-prev>200) {
						
						if(Math.sqrt((trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(0).y-trans.get(1).y)*(trans.get(0).y-trans.get(1).y))<=trans.get(0).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
							System.out.println("Intaraction");
							if(trans.get(1).positive!=trans.get(0).positive) {
								System.out.println("force");
								if(Math.sqrt((trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y))>=Charge.radius) {
									System.out.println("Change)");
									if(Math.sqrt((trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y))<trans.get(0).strength*CanvasM.strength/2||Math.sqrt(trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y)<trans.get(1).strength*CanvasM.strength/2) {
										
											
										if(trans.get(1).x>trans.get(0).x) {
											trans.get(1).x-=6;
										
											trans.get(0).x+=6;
											
										}else if(trans.get(1).x==trans.get(0).x){}else{
											trans.get(1).x+=6;
											
											trans.get(0).x-=6;
											
										}
										
										if(trans.get(1).y<trans.get(0).y) {
											trans.get(1).y+=6;
											trans.get(0).y-=6;
										}else if(trans.get(1).y==trans.get(0).y){}else{
											trans.get(1).y-=6;
											trans.get(0).y+=6;
										}
										
											
									
										
									
									}else {
										
										if(trans.get(1).x>trans.get(0).x) {
											trans.get(1).x-=2;
										
											trans.get(0).x+=2;
											
										}else if(trans.get(1).x==trans.get(0).x){}else{
											trans.get(1).x+=2;
											
											trans.get(0).x-=2;
											
										}
										if(trans.get(1).y<trans.get(0).y) {
											trans.get(1).y+=2;
											trans.get(0).y-=2;
										}else if(trans.get(1).y==trans.get(0).y){}else{
											trans.get(1).y-=2;
											trans.get(0).y+=2;
										}
										
										
									}
									
								}else {
									keep1=false;
								}
							}else {
								System.out.println("unforce");
								if(Math.sqrt((trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y))<=trans.get(0).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
									if(Math.sqrt((trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y))<trans.get(0).strength*CanvasM.strength/2||Math.sqrt(trans.get(1).x-trans.get(0).x)*(trans.get(1).x-trans.get(0).x)+(trans.get(1).y-trans.get(0).y)*(trans.get(1).y-trans.get(0).y)<trans.get(1).strength*CanvasM.strength/2) {
										if(trans.get(1).x>trans.get(0).x) {
											trans.get(1).x+=6;
											trans.get(0).x-=6;
										}else if(trans.get(1).x==trans.get(0).x){}else{
											trans.get(1).x-=6;
											trans.get(0).x+=6;
										}
										if(trans.get(1).y<trans.get(0).y) {
											trans.get(1).y-=6;
											trans.get(0).y+=6;
										}else if(trans.get(1).y==trans.get(0).y){}else{
											trans.get(1).y+=6;
											trans.get(0).y-=6;
										}

										
									}else {
										
										if(trans.get(1).x>trans.get(0).x) {
											trans.get(1).x+=2;
											trans.get(0).x-=2;
										}else if(trans.get(1).x==trans.get(0).x){}else{
											trans.get(1).x-=2;
											trans.get(0).x+=2;
										}
										if(trans.get(1).y<trans.get(0).y) {
											trans.get(1).y-=2;
											trans.get(0).y+=2;
										}else if(trans.get(1).y==trans.get(0).y){}else{
											trans.get(1).y+=2;
											trans.get(0).y-=2;
										}
										
										
									}
									
								}else {
									keep1=false;
								}
							}
							CanvasM.setData(trans);
						}
						
						else {
							keep1=false;
							
						}
						prev = t;
					}
				}
				
				
			}
		});
		th.start();
	}
	private synchronized void updateThread312() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long prev = System.currentTimeMillis();
				
				while (keepP) {
					long t = System.currentTimeMillis();
					if(t-prev>200) {
						
						if(Math.sqrt((trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y))<=trans.get(2).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
							System.out.println("Intaraction");
							if(trans.get(2).positive!=trans.get(1).positive) {
								
								System.out.println("force");
								if(Math.sqrt((trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y))>=Charge.radius) {
									System.out.println("Change)");
									if(Math.sqrt((trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y))<trans.get(2).strength*CanvasM.strength/2||Math.sqrt(trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y)<trans.get(1).strength*CanvasM.strength/2) {
										if(trans.get(2).x>trans.get(1).x) {
											trans.get(2).x-=6;
											trans.get(1).x+=6;
										}else if(trans.get(2).x==trans.get(1).x){}else{
											trans.get(2).x+=6;
											trans.get(1).x-=6;
										}
											
										if(trans.get(1).y<trans.get(2).y) {	

											trans.get(2).y-=6;
											trans.get(1).y+=6;
										
										}else if(trans.get(1).y==trans.get(2).y){}else{
											trans.get(2).y+=6;
											trans.get(1).y-=6;
										}
										
											
										
										
									}else {
										
										if(trans.get(2).x>trans.get(1).x) {
											trans.get(2).x-=2;
											trans.get(1).x+=2;
										}else if(trans.get(2).x==trans.get(1).x){}else{
											trans.get(2).x+=2;
											trans.get(1).x-=2;
										}
											
										if(trans.get(1).y<trans.get(2).y) {	

											trans.get(2).y-=2;
											trans.get(1).y+=2;
										
										}else if(trans.get(1).y==trans.get(2).y){}else{
											trans.get(2).y+=2;
											trans.get(1).y-=2;
										}
										
										
									}
									
								}else {
									keep2=false;
								}
							}else {
								System.out.println("unforce");
								if(Math.sqrt((trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y))<=trans.get(2).strength*CanvasM.strength/2+Charge.radius/2+trans.get(1).strength*CanvasM.strength/2+Charge.radius/2) {
									if(Math.sqrt((trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y))<trans.get(1).strength*CanvasM.strength/2||Math.sqrt(trans.get(2).x-trans.get(1).x)*(trans.get(2).x-trans.get(1).x)+(trans.get(2).y-trans.get(1).y)*(trans.get(2).y-trans.get(1).y)<trans.get(1).strength*CanvasM.strength/2) {
										
										if(trans.get(2).x>trans.get(1).x) {
											trans.get(2).x+=6;
											trans.get(1).x-=6;
										}else if(trans.get(2).x==trans.get(1).x){}else{
											trans.get(2).x-=6;
											trans.get(1).x+=6;
										}
											
										if(trans.get(1).y<trans.get(2).y) {	

											trans.get(2).y+=6;
											trans.get(1).y-=6;
										
										}else if(trans.get(1).y==trans.get(2).y){}else{
											trans.get(2).y-=6;
											trans.get(1).y+=6;
										}
										
											
										
										
									}else {
										
										if(trans.get(2).x>trans.get(1).x) {
											trans.get(2).x+=2;
											trans.get(1).x-=2;
										}else if(trans.get(2).x==trans.get(1).x){}else{
											trans.get(2).x-=2;
											trans.get(1).x+=2;
										}
											
										if(trans.get(1).y<trans.get(2).y) {	

											trans.get(2).y+=2;
											trans.get(1).y-=2;
										
										}else if(trans.get(1).y==trans.get(2).y){}else{
											trans.get(2).y-=2;
											trans.get(1).y+=2;
										}
										
									}
									
								}else {
									keep2=false;
								}
							}
							CanvasM.setData(trans);
						}
						
						else {
							keep2=false;
						}
						prev = t;
					}
				}
				
				
			}
		});
		th.start();
	}
	private synchronized void updateThread302() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long prev = System.currentTimeMillis();
				while (keepP) {
					long t = System.currentTimeMillis();
					if(t-prev>200) {
						
						if(Math.sqrt((trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x))<=trans.get(2).strength*CanvasM.strength/2+Charge.radius/2+trans.get(0).strength*CanvasM.strength/2+Charge.radius/2) {
							System.out.println("Intaraction");
							if(trans.get(2).positive!=trans.get(0).positive) {
								
								System.out.println("force");
								if(Math.sqrt((trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x))>=Charge.radius) {
									System.out.println("Change)");
									if(Math.sqrt((trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x)+(trans.get(2).y-trans.get(0).y)*(trans.get(2).y-trans.get(0).y))<trans.get(2).strength*CanvasM.strength/2||Math.sqrt(trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x)<trans.get(0).strength*CanvasM.strength/2) {
										
										if(trans.get(2).x>trans.get(0).x) {
											trans.get(2).x-=6;
											
											trans.get(0).x+=6;
										}else if(trans.get(2).x==trans.get(0).x) {
											
										}else {
											trans.get(2).x+=6;
											
											trans.get(0).x-=6;
										}
										
										if(trans.get(2).y>trans.get(0).y) {
											trans.get(2).y-=6;
											trans.get(0).y+=6;
										}else if(trans.get(2).y==trans.get(0).y) {
											
										}else {
											trans.get(2).y+=6;
											trans.get(0).y-=6;
										}
											
										
										
											
										
										
										
									}else {
										if(trans.get(2).x>trans.get(0).x) {
											trans.get(2).x-=2;
											
											trans.get(0).x+=2;
										}else if(trans.get(2).x==trans.get(0).x) {
											
										}else {
											trans.get(2).x+=2;
											
											trans.get(0).x-=2;
										}
										
										if(trans.get(2).y>trans.get(0).y) {
											trans.get(2).y-=2;
											trans.get(0).y+=2;
										}else if(trans.get(2).y==trans.get(0).y) {
											
										}else {
											trans.get(2).y+=2;
											trans.get(0).y-=2;
										}
									}
									
								}else {
									keep3=false;
								}
							}else {
								System.out.println("unforce");
								if(Math.sqrt((trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x))<=trans.get(2).strength*CanvasM.strength/2+Charge.radius/2+trans.get(0).strength*CanvasM.strength/2+Charge.radius/2) {
									if(Math.sqrt((trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x)+(trans.get(2).y-trans.get(0).y)*(trans.get(2).y-trans.get(0).y))<trans.get(0).strength*CanvasM.strength/2||Math.sqrt(trans.get(2).x-trans.get(0).x)*(trans.get(2).x-trans.get(0).x)<trans.get(2).strength*CanvasM.strength/2) {
										
										
										if(trans.get(2).x>trans.get(0).x) {
											trans.get(2).x+=6;
											
											trans.get(0).x-=6;
										}else if(trans.get(2).x==trans.get(0).x) {
											
										}else {
											trans.get(2).x-=6;
											
											trans.get(0).x+=6;
										}
										
										if(trans.get(2).y>trans.get(0).y) {
											trans.get(2).y+=6;
											trans.get(0).y-=6;
										}else if(trans.get(2).y==trans.get(0).y) {
											
										}else {
											trans.get(2).y-=6;
											trans.get(0).y+=6;
										}
											
										
										
											
										
										
										
									}else {
										if(trans.get(2).x>trans.get(0).x) {
											trans.get(2).x+=2;
											
											trans.get(0).x-=2;
										}else if(trans.get(2).x==trans.get(0).x) {
											
										}else {
											trans.get(2).x-=2;
											
											trans.get(0).x+=2;
										}
										
										if(trans.get(2).y>trans.get(0).y) {
											trans.get(2).y+=2;
											trans.get(0).y-=2;
										}else if(trans.get(2).y==trans.get(0).y) {
											
										}else {
											trans.get(2).y-=2;
											trans.get(0).y+=2;
										}
									}
									
								}else {
									keep3=false;
								}
							}
							CanvasM.setData(trans);
						}
						
						else {
							keep3=false;
						}
						prev = t;
					}
				}
				
				
			}
		});
		th.start();
	}
}

class JButtonM extends JButton{
	int id;
}
