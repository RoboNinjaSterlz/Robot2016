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
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ShootElevator extends Subsystem {
	
	public double presetPositions[] = new double[5];
	// Desired encoder count for positioning the lifter.
	private double desiredPosition = 0;

	// keeps track of when the lift is calibrated
	private boolean needsCalibrate;

	// How good does the position need to be
	private final double AbsoluteTolerance = 0;
	
	// Labels for presets in robot prefs on dashboard
	public final String[] ScoopPositionLables = { 
		"Low",
		"Medium",
		"High"
	};
	
	public final int
		LOW = 0,
		MEDIUM = 1,
		HIGH = 2;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon lift = RobotMap.shootElevatorLift;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public ShootElevator() {
    	needsCalibrate = true;

    	lift.setProfile(0);
    	lift.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	lift.reverseSensor(false);
    	lift.reverseOutput(true);
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
    		lift.changeControlMode(TalonControlMode.Position);
    		// reset the encoder
    		lift.setPosition(0);
    		lift.set(0);
    		lift.reverseSensor(false);
    		lift.reverseOutput(true);
    		lift.enable();
    		needsCalibrate = false;
    	} else {
    		needsCalibrate = true;
    		lift.set(.3); // Run back at 30% power
    	}
    }

    	// Returns true if the shooter is in the home position
    	public boolean isShooterAtHome() {
        	return (lift.isFwdLimitSwitchClosed());
        }

    	// Goes to the encoder count that is passed
        public void goTo(double angle) {
    		desiredPosition = angle;
    		lift.set(angle);
    		//lift.enableControl();
        	}
        public void goToPreset(int position) {
        	
        	if ((position >= 1) && (position <= presetPositions.length)) {
        		goTo(presetPositions[position-1]);
          	}
        }
    	public void incrementAngle() {
    		desiredPosition++;
    		goTo(desiredPosition);
    	}
    	
    	public void decremntAngle() {
    		desiredPosition--;
    		goTo(desiredPosition);
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
    	
    	public boolean safeToHomeScoop() {
    		return (isShooterAtHome() || (getPosition() > presetPositions[LOW]));
    	}
    	
        
    	// mostly for deugging updates the smart dashboard with position info
    	public void periodic() {
    		SmartDashboard.putNumber("ShootLift Desired Pos", lift.getSetpoint());
    		SmartDashboard.putNumber("Shooter Position", getPosition());
    		SmartDashboard.putNumber("Shooter Position Error", getPositionError());
//    		SmartDashboard.putBoolean("limit sensor", shooterLowerLimit.get());
    	}
        
    	public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

