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
		Robot.shootElevator.presetPositions[Robot.shootElevator.LOW] = 
			prefs.getDouble("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.LOW], Defaults.SHOOTERANGLE1);
		Robot.shootElevator.presetPositions[Robot.shootElevator.MEDIUM] = 
			prefs.getDouble("Shooter"+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.MEDIUM], Defaults.SHOOTERANGLE2);
		Robot.shootElevator.presetPositions[Robot.shootElevator.HIGH] = 
			prefs.getDouble("Shooter"+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.HIGH], Defaults.SHOOTERANGLE3);

  		Robot.scoop.presetPositions[Robot.scoop.FLOOR] = 
           prefs.getDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.FLOOR], Defaults.SCOOPER_FLOOR);
 		Robot.scoop.presetPositions[Robot.scoop.CLEAR_SHOOTER] = 
			prefs.getDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_SHOOTER], Defaults.SCOOPER_CLEAR_OF_SHOOTER);
		Robot.scoop.presetPositions[Robot.scoop.CLEAR_LOW_BAR] = 
			prefs.getDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_LOW_BAR], Defaults.SCOOPER_LOW_BAR);
		Robot.scoop.presetPositions[Robot.scoop.SHOOT] = 
			prefs.getDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.SHOOT], Defaults.SCOOPER_SHOOT);

		/*
		 * After setting the value, setMax must be called
		 * Unlike the other prefs, drivetrain does not use the limit in every cycle
		 * instead, it is set once by robotbuilder generated code.
		 *  The call to setMax will update the limit.
		 */
		Robot.drivetrain.drivetrainVoltageLimit = prefs.getDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
        Robot.drivetrain.setMax();
	}


	// Used to store initial values and create entries in the cRio NVRAM
	void setupPrefs() {
		//Setup the nv RAM in the Roborio
		prefs = Preferences.getInstance();

		// Shooter angle presets
		if (!prefs.containsKey("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.LOW])) {
			prefs.putDouble("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.LOW], Defaults.SHOOTERANGLE1);
		}
		if (!prefs.containsKey("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.MEDIUM])) {
			prefs.putDouble("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.MEDIUM], Defaults.SHOOTERANGLE2);
		}
		if (!prefs.containsKey("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.HIGH]))  {	
			prefs.putDouble("Shooter "+Robot.shootElevator.ShootPositionLables[Robot.shootElevator.HIGH], Defaults.SHOOTERANGLE3);
		}

		if (!prefs.containsKey("drivetrainVoltageLimit")) {
			prefs.putDouble("drivetrainVoltageLimit", Defaults.DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT);
		}

//		if (!prefs.containsKey("ScoopAngle1")) {
//			prefs.putDouble("ScoopAngle1", Defaults.SCOOPER_FLOOR);
//		}
		if (!prefs.containsKey("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.FLOOR])) {
			prefs.putDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.FLOOR], Defaults.SCOOPER_FLOOR);
		}
		if (!prefs.containsKey("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_SHOOTER])) {
			prefs.putDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_SHOOTER], Defaults.SCOOPER_CLEAR_OF_SHOOTER);
		}
		if (!prefs.containsKey("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_LOW_BAR]))  {	
			prefs.putDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.CLEAR_LOW_BAR], Defaults.SCOOPER_LOW_BAR);
		}
		if (!prefs.containsKey("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.SHOOT]))  {	
			prefs.putDouble("Scoop "+Robot.scoop.ScoopPositionLables[Robot.scoop.SHOOT], Defaults.SCOOPER_SHOOT);
		}

	}
}
