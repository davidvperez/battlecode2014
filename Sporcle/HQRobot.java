package Sporcle;

import battlecode.common.Direction;
import battlecode.common.RobotController;

public class HQRobot extends GenericRobot{
	public HQRobot(RobotController rc){
		super(rc);
	}
	
	public void run(){
		try {
			//Check if a robot is spawnable and spawn one if it is
			if (rc.isActive() && rc.senseRobotCount() < 25) {
				Direction toEnemy = rc.getLocation().directionTo(
						rc.senseEnemyHQLocation());
				if (rc.senseObjectAtLocation(rc.getLocation().add(
						toEnemy)) == null) {
					rc.spawn(toEnemy);
				}
			}
		} catch (Exception e) {
			System.out.println("HQ Exception");
		}
	}
}
