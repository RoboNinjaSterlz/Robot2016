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

        Robot.drivetrain.drivetrainVoltageLimit = prefs.getDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
    }
    

    // Used to store initial values and create entries in the cRio NVRAM
    void setupPrefs() {
        //Setup the nv RAM in the CRIO
        prefs = Preferences.getInstance();
        if (!prefs.containsKey("drivetrainVoltageLimit")) {
            prefs.putDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
        }

    }


}
