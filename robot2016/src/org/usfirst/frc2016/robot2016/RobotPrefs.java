/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package edu.wpi.first.wpilibj.templates;

package org.usfirst.frc2016.robot2016;
import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author Montagna
 */
public class RobotPrefs {
    // This class handles initializing and reading presets from the cRio NV RAM
    Preferences prefs;
    
    public RobotPrefs() {
                
    }
    
    // This is used to see if the operator wants to load new values from
    // the RobRio NVRAM. If so robotPrefs class will perform the operation.
    void periodic() {
        if (Robot.oi.operatorJoy.getRawButton(RobotMap.PREFS_BUTTON)) {
            doLoadPrefs();
        }
    }

    // Read the values stored in NV RAM and store them in variables
    void doLoadPrefs() {

        // Shooter angle presets
    	Robot.shooter.presetPositions[0] = prefs.getDouble("ANGLE1", Defaults.ANGLE1);
        Robot.shooter.presetPositions[1] = prefs.getDouble("ANGLE2", Defaults.ANGLE2);
        Robot.shooter.presetPositions[2] = prefs.getDouble("ANGLE3", Defaults.ANGLE3);

        Robot.drivetrain.drivetrainVoltageLimit = prefs.getDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
    }
    

    // Used to store initial values and create entries in the cRio NVRAM
    void setupPrefs() {
        //Setup the nv RAM in the Roborio
        prefs = Preferences.getInstance();

        // Shooter angle presets
        if (!prefs.containsKey("ANGLE1")) {
            prefs.putDouble("ANGLE1", Defaults.ANGLE1);
        }
        if (!prefs.containsKey("ANGLE2")) {
        	prefs.putDouble("ANGLE2", Defaults.ANGLE2);
        }
        if (!prefs.containsKey("Angle3"))  {	
        	prefs.putDouble("ANGLE3", Defaults.ANGLE3);
        }
        if (!prefs.containsKey("drivetrainVoltageLimit")) {
            prefs.putDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
        }
        
	}
}