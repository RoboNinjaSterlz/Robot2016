// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2016.robot2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2016.robot2016.Robot;

/**
 *
 */
public class ShooterForward extends Command {

	/*
	 * Can't get less than 1 second resolution from the timer class
	 * Each call to execute or isFinished takes approx 20ms
	 * The variables below are used to track time by the number of calls
	 */
	private int waitCounter;
	private final double DELAYPERCOUNT = .02;
	private final double TIME2WAIT = .500;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ShooterForward() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	waitCounter = 0;	// Reset the timer
    	Robot.shooter.shoot();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	waitCounter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (waitCounter >= (TIME2WAIT/DELAYPERCOUNT)) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.shooter.shootOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
