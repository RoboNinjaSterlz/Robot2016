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

//import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 *
 */
public class Gyro extends Subsystem {
    //ADIS16448_IMU imu;
	ADXRS450_Gyro digGyro;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Gyro() {
    	digGyro = new ADXRS450_Gyro();
    	digGyro.calibrate();
    	/*
        DriverStation.reportError("About to init ADIS16448", false);
        imu = new ADIS16448_IMU();

        if (imu == null) {
        	// init failed
            DriverStation.reportError("could not find ADIS16448", false);
        }
    	 */
    }
    
    public void periodic() {
        //SmartDashboard.putData("IMU", imu);
    	SmartDashboard.putNumber("Gyro Heading", digGyro.getAngle());
    }
    
    public void calibrate() {
    	//imu.calibrate();
    	digGyro.calibrate();
    }
    
    public double getAngle() {
    	return digGyro.getAngle();
    }
    
    public void reset() {
    	digGyro.reset();
    }
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

