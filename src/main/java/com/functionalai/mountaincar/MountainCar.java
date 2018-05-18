package com.functionalai.mountaincar;

import com.functionalai.mountaincar.reinforcementlearning.State;

public class MountainCar {
	
	public static final double MIN_POS = -1.2;
	public static final double MAX_POS = 0.6;
	public static final double MAX_SPEED = 0.07;
	public static final double GOAL_POS = 0.5;
	
	private double position;
	private double velocity;
	
	public MountainCar() {
		randomInit();
	}

	public void apply(int force) {
		if (!(force==0 || force==1 || force==2)) 
			System.out.println("Please pick only -1, 0 or 1 as actions.");
		velocity += (force-1)*0.001 + Math.cos(3*position)*(-0.0025);
        velocity = Math.min(MAX_SPEED, Math.max(-MAX_SPEED,velocity));
        position += velocity;
        position = Math.min(MAX_POS, Math.max(MIN_POS,position));
        if (position==MIN_POS && velocity<0) velocity = 0;
	}

	public State getState() {
		return State.from(position, velocity);
	}
	
	public double getReward() {
		if (position>GOAL_POS)
			return 0.0;
		else 
			return -1.0;
	}
	
	public boolean done() {
		return (position>GOAL_POS);
	}
	
	public double getPosition() {
		return position;
	}

	public double getVelocity() {
		return velocity;
	}

	public void randomInit() {
		this.position = -0.6 + Math.random()*0.2;
		this.velocity = 0.0;
	}
	
}