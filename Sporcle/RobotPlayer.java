package Sporcle;

import battlecode.common.*;

public class RobotPlayer {
	
	public static void run(RobotController rc) {
		GenericRobot robot = null;
		switch(rc.getType()){
		case HQ:
			robot = new HQRobot(rc);
			break;
		case SOLDIER:
			robot = new SoldierRobot(rc);
			break;
		default:
			robot = new SoldierRobot(rc);
			break;
		}
		robot.functionLoop();
	}
}
