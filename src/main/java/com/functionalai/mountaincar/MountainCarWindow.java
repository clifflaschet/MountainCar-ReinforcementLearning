package com.functionalai.mountaincar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MountainCarWindow extends JFrame {

	private MountainCar mc;
	private MountainCarViewer view;

	public MountainCarWindow(MountainCar mc) {
		super("Pendulum Environment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mc = mc;
		//Add a MountainCarViewer
		view = new MountainCarViewer(mc);
		add(view, BorderLayout.CENTER);
		//Add a button to reset the car
		JButton randomButton = new JButton();
		randomButton.setText("Random");
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {setRandomAngle();}
		    });
		//This is ugly, but easy ...
		//JPanel buttonPanel = new JPanel();
		//buttonPanel.add(randomButton, null);
		//add(buttonPanel, BorderLayout.SOUTH);
		//Open in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = view.getPreferredSize(); 
 		int left = (screenSize.width - frameSize.width)/ 2;
		int top  = (screenSize.height - frameSize.height)/ 2;
		setLocation(left, top);
		
		pack();
	}

	public void paintCar() {
		setVisible(true);
		view.repaint();
		try {Thread.sleep(15);} catch (InterruptedException e) {}
	}
	
	private void setRandomAngle() {
		mc.randomInit();
	}
	
	public static void main(String[] args) {
		MountainCar mc = new MountainCar();
		MountainCarWindow pw = new MountainCarWindow(mc);
		for (int i=0; i<10; i++) {
			mc.randomInit();
			int stepcounter = 0;
			while (!mc.done()) {
				pw.paintCar();
				//mc.apply(0);
				mc.apply((int)(Math.random()*3));
				stepcounter++;
			}
			System.out.println("Episode " + i + " took " + stepcounter + " steps.");
		}
		pw.dispose();
	}
	
}