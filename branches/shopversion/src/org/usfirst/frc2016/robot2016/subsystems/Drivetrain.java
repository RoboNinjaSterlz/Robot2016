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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
/**
import.edu.wpi.first.wpilibj.SpeedController;


*
*
 */
public class Drivetrain extends Subsystem {

    /*
     * The following block of variables are used to hold values loaded from
     * NV RAM by RobotPrefs.
    */
	public double drivetrainVoltageLimit;
	
    /*
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     * End of values set by RobotPrefs
     */
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftdrive = RobotMap.drivetrainLeftdrive;
    private final SpeedController rightdrive = RobotMap.drivetrainRightdrive;
    private final RobotDrive robotDrive = RobotMap.drivetrainRobotDrive;
    private final Encoder leftEncoder = RobotMap.drivetrainLeftEncoder;
    private final Encoder rightEncoder = RobotMap.drivetrainRightEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void arcadeDrive(Joystick joy) {
    	robotDrive.arcadeDrive(joy, true);
    }
    
    public void arcadeDrive(double speed, double direction) {
    	robotDrive.arcadeDrive(speed, direction);
    }
    
    public void tankDrive(Joystick leftJoy, Joystick rightJoy) {
    	robotDrive.tankDrive(leftJoy, rightJoy, true);
    }	
    
    public void driveStop() {
    
    	robotDrive.tankDrive(0,0);
    }
    

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

