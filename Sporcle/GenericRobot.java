package Sporcle;

import battlecode.common.RobotController;

public abstract class GenericRobot {
	public RobotController rc;
	public int tag;
	
	public GenericRobot(RobotController rc){
		this.rc = rc;
		tag = 0;
		//TODO: Fix ID Generation
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
