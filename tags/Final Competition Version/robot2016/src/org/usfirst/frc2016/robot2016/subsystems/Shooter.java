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

import org.usfirst.frc2016.robot2016.Robot;
import org.usfirst.frc2016.robot2016.RobotMap;
import org.usfirst.frc2016.robot2016.commands.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */

public class Shooter extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Compressor compressor = RobotMap.shooterCompressor;
    private final SpeedController vacuumTalon = RobotMap.shooterVacuumTalon;
    private final Solenoid shootSolenoid = RobotMap.shooterShootSolenoid;
    private final Solenoid pushSolenoid = RobotMap.shooterPushSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


	// Starts the compressor
    public void startCompressor() {
        	compressor.start();
    }

    // Drives the piston forward (shooting)
    public void shoot() {
//  	if (Robot.scoop.isScoopInSafeZone()) {
    		shootSolenoid.set(true);
//    	}
    }
    
    public void push() {
    pushSolenoid.set(true);
    }
    public void pushOff() {
    	pushSolenoid.set(false);
    }

    public void shootOff() {
    	shootSolenoid.set(false);
    }
    
    // Enables the vacuum pump
    public void vacuumOn() {
    	vacuumTalon.set(1);
    }

    // Disables the vacuum pump
    public void vacuumOff() {
    	vacuumTalon.set(0);
    }

	public void periodic() {
		SmartDashboard.putBoolean("Compressor Ready", compressor.getPressureSwitchValue());
	}
    public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

