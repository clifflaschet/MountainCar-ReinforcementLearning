package com.functionalai.mountaincar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MountainCarViewer extends JPanel{
	
	private MountainCar mc;
	private double worldwidth;
	private int carX, carY;
	private double scaleX, scaleY;

	public MountainCarViewer(MountainCar mc) {
		this.mc = mc;
		updatePoints();
	}
	
    public void updatePoints() {
    	worldwidth = MountainCar.MAX_POS - MountainCar.MIN_POS;
    	//scale makes the drawing grow/shrink as the window does
    	scaleX = getSize().getWidth()/worldwidth*0.9;
    	scaleY = getSize().getHeight()/worldwidth*0.9;
    	carX = getXFor(mc.getPosition());
    	carY = getYFor(mc.getPosition());
    }

    public Dimension getPreferredSize() {
    	return new Dimension(500,500);
    }
    
    private int getXFor(double val) {
    	return (int) (10+scaleX*(val-MountainCar.MIN_POS));
    }
    private int getYFor(double val) {
    	return (int) (getSize().getHeight()*0.55 - scaleY*0.75*(Math.sin(val*3)));
    }
    
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//Get rid of the old
		g2d.setColor(super.getBackground());
		//g2d.drawLine(centerX,centerY,massX,massY);
		Ellipse2D.Double circle = new Ellipse2D.Double(carX-7, carY-7, 14, 14);
		g2d.fill(circle);
		//Change
		updatePoints();
		//In with the new
		g.setColor(Color.black);
		//g.drawLine(centerX,centerY,massX,massY);
		circle = new Ellipse2D.Double(carX-7, carY-7, 14, 14);
		g2d.fill(circle);
		
		//Env
		int np=50;
		int[] xpnts = new int[np];
		int[] ypnts = new int[np];
		for (int i=0; i<np; i++) {
			xpnts[i] = getXFor(1.0*i*(worldwidth)/(np-1)+MountainCar.MIN_POS);
			ypnts[i] = getYFor(1.0*i*(worldwidth)/(np-1)+MountainCar.MIN_POS);
		}
		g2d.setStroke(new BasicStroke(2));
		g2d.drawPolyline(xpnts,ypnts,np);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(getXFor(MountainCar.GOAL_POS),getYFor(MountainCar.GOAL_POS), 
				getXFor(MountainCar.GOAL_POS), getYFor(MountainCar.GOAL_POS)-50);
		g2d.setColor(Color.green);
		int[] triangleX = new int[3];
		int[] triangleY = new int[3];
		triangleX[0] = getXFor(MountainCar.GOAL_POS);
		triangleX[1] = getXFor(MountainCar.GOAL_POS);
		triangleX[2] = getXFor(MountainCar.GOAL_POS)-40;
		triangleY[0] = getYFor(MountainCar.GOAL_POS)-50;
		triangleY[1] = getYFor(MountainCar.GOAL_POS)-30;
		triangleY[2] = getYFor(MountainCar.GOAL_POS)-40;
		g2d.fillPolygon(triangleX, triangleY, 3);
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(10, getYFor(MountainCar.MIN_POS)-40, 
				10, getYFor(MountainCar.MIN_POS)+15);
	}
}