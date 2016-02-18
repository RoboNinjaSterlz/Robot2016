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
		Robot.shooter.presetPositions[0] = prefs.getDouble("ShooterAngle1", Defaults.SHOOTERANGLE1);
		Robot.shooter.presetPositions[1] = prefs.getDouble("ShooterAngle2", Defaults.SHOOTERANGLE2);
		Robot.shooter.presetPositions[2] = prefs.getDouble("ShooterAngle3", Defaults.SHOOTERANGLE3);
		Robot.scoop.presetPositions[0] = prefs.getDouble("ScooperAngle1", Defaults.SCOOPERANGLE1);
		Robot.scoop.presetPositions[1] = prefs.getDouble("ScooperAngle2", Defaults.SCOOPERANGLE2);
		Robot.scoop.presetPositions[2] = prefs.getDouble("ScooperAngle3", Defaults.SCOOPERANGLE3);
		Robot.drivetrain.drivetrainVoltageLimit = prefs.getDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
	}


	// Used to store initial values and create entries in the cRio NVRAM
	void setupPrefs() {
		//Setup the nv RAM in the Roborio
		prefs = Preferences.getInstance();

		// Shooter angle presets
		if (!prefs.containsKey("ShooterAngle1")) {
			prefs.putDouble("ShooterAngle1", Defaults.SHOOTERANGLE1);
		}
		if (!prefs.containsKey("ShooterAngle2")) {
			prefs.putDouble("Angle2", Defaults.SHOOTERANGLE2);
		}
		if (!prefs.containsKey("ShooterAngle3"))  {	
			prefs.putDouble("ShooterAngle3", Defaults.SHOOTERANGLE3);
		}
		if (!prefs.containsKey("drivetrainVoltageLimit")) {
			prefs.putDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
			if (!prefs.containsKey("ScoopAngle1")) {
				prefs.putDouble("ScoopAngle", Defaults.SCOOPERANGLE1);
			}
			if (!prefs.containsKey("ScoopAngle2")) {
				prefs.putDouble("ScoopAngle2", Defaults.SCOOPERANGLE2);
			}
			if (!prefs.containsKey("ScoopAngle3"))  {	
				prefs.putDouble("ScoopAngle3", Defaults.SCOOPERANGLE3);
			}

		}
	}
}