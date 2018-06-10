package leff;

import ui.UI;

public class Main {
	public static void main(String[] args) {
		
		  javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	UI ui = new UI();
	            }
	        });
	}
}
