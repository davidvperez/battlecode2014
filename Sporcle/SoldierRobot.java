package Sporcle;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class SoldierRobot extends GenericRobot{
	private static Random rand;
	private static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};

	
	public SoldierRobot(RobotController rc){
		super(rc);
		rand = new Random();
	}
	
	public void run(){
		try {

			if (rc.isActive()) {
				int action = (rc.getRobot().getID()
						* rand.nextInt(101) + 50) % 101;
				//Construct a PASTR
				if (action < 1
						&& rc.getLocation().distanceSquaredTo(
								rc.senseHQLocation()) > 2) {
					rc.construct(RobotType.PASTR);
					//Attack a random nearby enemy
				} else if (action < 30) {
					Robot[] nearbyEnemies = rc
							.senseNearbyGameObjects(Robot.class,
									10, rc.getTeam().opponent());
					if (nearbyEnemies.length > 0) {
						battlecode.common.RobotInfo robotInfo = rc
								.senseRobotInfo(nearbyEnemies[0]);
						rc.attackSquare(robotInfo.location);
					}
					//Move in a random direction
				} else if (action < 80) {
					Direction moveDirection = directions[rand
							.nextInt(8)];
					if (rc.canMove(moveDirection)) {
						rc.move(moveDirection);
					}
					//Sneak towards the enemy
				} else {
					Direction toEnemy = rc.getLocation()
							.directionTo(rc.senseEnemyHQLocation());
					if (rc.canMove(toEnemy)) {
						rc.sneak(toEnemy);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Soldier Exception");
		}
	}
}
