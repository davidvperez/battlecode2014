package Sporcle;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public abstract class GenericRobot {
	public RobotController rc;
	public int tag;
	
	public GenericRobot(RobotController rc){
		this.rc = rc;
		tag = -1;
		try {
			tag = rc.readBroadcast(0);
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public void run();
	
	public void functionLoop(){
		 while (true){
             try{
                     run();
             }catch (Exception e){
                     e.printStackTrace();
             }
             rc.yield();
         }
	}
}
