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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Scoop extends Subsystem {

	// keeps track of when the lift is calibrated
	private boolean needsCalibrate;

	// How good does the position need to be
	private final double AbsoluteTolerance = 0;

	// Where we want the lift to be
	private double desiredPosition;
	
	// Contains the encoder counts for preset positions
	public double presetPositions[] = new double[3];
	
	
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
    	//lift.configMaxOutputVoltage(12);
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
//    		lift.disable();
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
    		lift.set(.3); // Run back at 85% power
    	}
    }
    
	// Returns true if the shooter is in the home position
	public boolean isScoopAtZero() {
    	return (lift.isFwdLimitSwitchClosed());
    }

	// Goes to the encoder count that is passed
    public void goTo(double angle) {
		desiredPosition = angle;
		lift.set(desiredPosition);
   	}

    public void goToPreset(int position) {
    	
    	if ((position >= 1) && (position <= presetPositions.length)) {
    		goTo(presetPositions[position-1]);
      	}
    }

	
	public void adjustAngle( double adjust) {
		desiredPosition+=adjust;
		goTo(desiredPosition);
	}	
	
	// Returns true of the lift is at the desired position (done moving)
	public boolean isPositioned() {
		int position;
		position = lift.getClosedLoopError();
		return (AbsoluteTolerance >= Math.abs(position));
	}
	
	// Returns the current postion error
	public int getPositionError() {
		return lift.getClosedLoopError();
	}

	// Returns the current position
	public int getPosition() {
		return (int) lift.getPosition();
	}
	
	// Returns true if the lift has been calibrated
	public boolean isCalibrated(){
		return !needsCalibrate;
	}
	
	public boolean isInSafeZone() {
		return safeZoneDetector.get();
	}
    
	// mostly for deugging updates the smart dashboard with position info
	public void periodic() {
		SmartDashboard.putNumber("ScoopLift Desired Pos", lift.getSetpoint());
		SmartDashboard.putNumber("ScoopLift Current Position", getPosition());
		SmartDashboard.putNumber("ScoopLift Position Error", getPositionError());
	}


    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
