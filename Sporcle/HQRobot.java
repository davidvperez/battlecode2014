package Sporcle;

import java.util.Random;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;

public class HQRobot extends GenericRobot{
	
	int[] standingOrders;
	int[] robotIDs;
	int[] orderComputationDelay;
	boolean hasBroadcasted;
	Random rand = null;
	
	public HQRobot(RobotController rc){
		super(rc);
		rand = new Random();
		hasBroadcasted = false;
		standingOrders = new int[25];
		robotIDs = new int[25];
		orderComputationDelay = new int[25];
		for(int i=0; i<25; i++)
			standingOrders[i] = -1;
		for(int i=0; i<25; i++)
			robotIDs[i] = -1;
		for(int i=0; i<25; i++)
			orderComputationDelay[i] = 100;
	}
	
	public void run(){
		try {
			//Check if a robot is spawnable and spawn one if it is
			if (rc.isActive() && rc.senseRobotCount() < 25) {
				Direction toEnemy = rc.getLocation().directionTo(
						rc.senseEnemyHQLocation());
				if (rc.senseObjectAtLocation(rc.getLocation().add(
						toEnemy)) == null) {
					rc.broadcast(0, determineNextRobotID());
					rc.spawn(toEnemy);
				}
			}
			if (!hasBroadcasted) 
				for (int i = 0; i < 25; i++)
					//if(robotIDs[i] != -1 && Clock.getRoundNum() % orderComputationDelay[i] == 0)
					computeOrders(i);
			hasBroadcasted = true;
			
		} catch (Exception e) {
			System.out.println("HQ Exception");
		}
	}
	
	private int determineNextRobotID(){
		return rc.senseRobotCount();
	}
	
	private void computeOrders(int robotTag){
		try {
			MapLocation loc = new MapLocation(1+rand.nextInt(rc.getMapWidth()),1+rand.nextInt(rc.getMapHeight()));
			rc.broadcast(robotTag+1, Order.generate(loc.x,loc.y,Order.TYPE.ATK));
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
