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
import org.usfirst.frc2016.robot2016.Robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/*
 *
 */
public class Scoop extends Subsystem {

	// keeps track of when the lift is calibrated
	private boolean needsCalibrate;
	
	// Special flag to keep track of transition to safe zone
	// Only used duing initial calibration
	private boolean inSafeZoneFlag = false;

	// How good does the position need to be
	private final double AbsoluteTolerance = 5;

	// Where we want the lift to be
	private double desiredPosition;
	
	// Contains the encoder counts for preset positions
	public double presetPositions[] = new double[4];
	
	// Labels for presets in robot prefs on dashboard
	public final String[] ScoopPositionLables = { 
		"Floor",
		"ClearLowBar",
		"Shoot",
		"ClearShooter"
	};
	
	public final int
		FLOOR = 0,
		CLEAR_LOW_BAR = 1,
		SHOOT = 2,
		CLEAR_SHOOTER = 3;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon lift = RobotMap.scoopLift;
    private final DigitalInput safeZoneDetector = RobotMap.scoopSafeZoneDetector;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Scoop() {
    	needsCalibrate = true;

    	// used by Robot during initial calibration
    	inSafeZoneFlag = isScoopInSafeZone();
    	
    	lift.setProfile(0);
    	lift.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	lift.reverseSensor(true);
    	lift.reverseOutput(false);
    	lift.setAllowableClosedLoopErr(0);
    	// Keep off until we are calibrated.
    	// Hard limits should protect everything.
    	lift.enableForwardSoftLimit(false);
    	lift.enableReverseSoftLimit(false);
    	lift.clearStickyFaults();
    	lift.ClearIaccum();
    	//lift.setVoltageRampRate(0);
    	//lift.setCloseLoopRampRate(.00);
    	//lift.configMaxOutputVoltage(8);
    	// Needed for calibrate
    	lift.changeControlMode(TalonControlMode.PercentVbus);	
    }

    public void startCalibrate() {
    	lift.changeControlMode(TalonControlMode.PercentVbus);	
    	needsCalibrate = true;
    }
    // Put methods for controlling this subsystem
        // here. Call these from Commands.
    public void doCalibrate() {
    	// Zero out the encoder by running the trucks backwards
    	// until they reach the optical sensor on the back of the elevator
    	if (lift.isFwdLimitSwitchClosed()) {
    		lift.set(0); // Turn off output
    		desiredPosition = 0;
    		lift.changeControlMode(TalonControlMode.Position);
    		// reset the encoder
    		lift.setPosition(0);
    		lift.set(0);
    		lift.reverseSensor(true);
    		lift.reverseOutput(false);
    		lift.enable();
    		needsCalibrate = false;
    	} else {
    		needsCalibrate = true;
    		lift.set(.4); // Run back at 30% power
    	}
    }
    
	// Returns true if the shooter is in the home position
	public boolean isScoopAtHome() {
    	return (lift.isFwdLimitSwitchClosed());
    }

	// Goes to the encoder count that is passed
    public void goTo(double angle) {
		desiredPosition = angle;
		if (Robot.robotIsCalibrated) {
			lift.set(desiredPosition);
		}
   	}

    public void goToPreset(int position) {
       	if ((position >= 0) && (position <= presetPositions.length-1)) {
    		goTo(presetPositions[position]);
      	}
    }

	public void adjustAngle( double adjust) {
		desiredPosition+=adjust;
		if (desiredPosition > 0) {
			desiredPosition = 0;
		}

		goTo(desiredPosition);
	}	
	
	// Returns true of the lift is at the desired position (done moving)
	public boolean isPositioned() {
		double position;
		position = Math.abs(desiredPosition - getPosition());
    	if (lift.isFwdLimitSwitchClosed() && getPosition()==0) {
    		lift.setPosition(0);
    	}
		return (position <= AbsoluteTolerance);
		/*position = getPositionError();
				//lift.getClosedLoopError();
		return (AbsoluteTolerance >= Math.abs(position));
		 */
		}
	
	// Returns the current position error
	public double getPositionError() {
		return lift.getClosedLoopError();
	}

	// Returns the current position
	public double getPosition() {
		return lift.getPosition();
	}
	
	// Returns true if the lift has been calibrated
	public boolean isCalibrated(){
		return !needsCalibrate;
	}
	
	// Returns true if the scoop is out enough for the elevator to pass behind
	// This is only valid after calibration
/*	public boolean safeToMoveShooter() {
		return (getPosition() < presetPositions[CLEAR_SHOOTER]-AbsoluteTolerance);
	}
*/	
	// This is the safe zone detection can be used before calibration
	public boolean isScoopInSafeZone() {
		return safeZoneDetector.get();
	}
	
	public boolean getSafeZoneFlag() {
		return inSafeZoneFlag;
	}
	
    // run the motor out until we see we are in the safe zone
	public void moveToSafeZone() {
    	// Zero out the encoder by running the trucks backwards
    	// until they reach the optical sensor on the back of the elevator
    	if (isScoopInSafeZone()) {
    		lift.set(0); // Turn off output
    		inSafeZoneFlag = true;
    	} 
    	else {
    		lift.set(-.3); // Run back at 30% power
    	}
    }
	
    
	// mostly for deugging updates the smart dashboard with position info
	public void periodic() {
		SmartDashboard.putNumber("ScoopLift Desired Pos", lift.getSetpoint());
		SmartDashboard.putNumber("ScoopLift Current Position", getPosition());
		SmartDashboard.putNumber("ScoopLift Position Error", getPositionError());
		//SmartDashboard.putBoolean("IS Positioned", isPositioned());
		}


    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

