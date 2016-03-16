// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2016.robot2016.subsystems;

import org.usfirst.frc2016.robot2016.RobotMap;
import org.usfirst.frc2016.robot2016.commands.*;
import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Hook extends Subsystem {
	private boolean deployed;
	private double desiredposition;
	private final double DEPLOY_POSITION = .715;
	private final double HOME_POSITION = .515;
			
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Servo hookServo = RobotMap.hookHookServo;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Hook() {
    	reset();
    }
    public void deploy() {
    desiredposition = DEPLOY_POSITION;
    	hookServo.set(desiredposition);
    	deployed = true;
    }	
    
    public void reset() {
    	desiredposition = HOME_POSITION;
    	hookServo.set(desiredposition);
    	deployed = false;
    }
    
    public boolean isDeployed() {
    	return deployed;
    }
    
    public boolean ispositioned() {
    return (desiredposition == hookServo.get());	
    	
    }	
    
    public void initDefaultCommand() {
      
    	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    
    }
    
}

