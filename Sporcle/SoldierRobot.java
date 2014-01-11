package Sporcle;

import java.util.Random;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class SoldierRobot extends GenericRobot{
	private static Random rand;
	private int orderRefreshRate;
	MapLocation target = null;
	Order.TYPE orderType = null;
	
	public SoldierRobot(RobotController rc){
		super(rc);
		rand = new Random();
		orderRefreshRate = 100;
		try {
			refreshOrders();
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			if (rc.isActive()) {
				if (Clock.getRoundNum() % orderRefreshRate == 0) {
					refreshOrders();
				}
				Direction dir = rc.getLocation().directionTo(target);
				System.out.println(target);
				if(rc.canMove(dir))
					rc.move(dir);
			}
		} catch (Exception e) {
			System.out.println("Soldier Exception");
		}
	}
	
	private void refreshOrders() throws GameActionException{
		int orderMessage = rc.readBroadcast(tag + 1);
		target = new MapLocation(Order.getXLoc(orderMessage), Order.getYLoc(orderMessage));
		orderType = Order.getType(orderMessage);
	}
}
