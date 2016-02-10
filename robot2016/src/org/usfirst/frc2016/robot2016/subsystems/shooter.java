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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class shooter extends Subsystem {


	public enum ShooterPosition {
		down,
		low,
		mid,
		high
	}
	
	public double PRESET_POSITIONS[];

	// Desired encoder count for positioning the lifter.
	private double localAngle = 0;

	// multiplier for counts to degrees (not used with lead screw
	private final double countPerDegree = 1;
//	private final double countPerDegree = (7*1188)/360;

	// keeps track of when the lift is calibrated
	private boolean needsCalibrate;

	// How good does the position need to be
	private final double AbsoluteTolerance = 0;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Compressor compressor = RobotMap.shooterCompressor;
    private final DoubleSolenoid shootSolenoid = RobotMap.shootershootSolenoid;
    private final CANTalon lift = RobotMap.shooterLift;
    private final SpeedController vacuumTalon = RobotMap.shooterVacuumTalon;
    private final DigitalInput shooterLowerLimit = RobotMap.shootershooterLowerLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

public shooter() {
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
//	if (lift.isRevLimitSwitchClosed()) {
	if (!shooterLowerLimit.get()) {
		lift.set(0); // Turn off output
		localAngle = 0;
//		lift.disable();
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
		lift.set(.7); // Run back at 85% power
	}
}

	// Returns true if the shooter is in the home position
	public boolean isShooterAtZero() {
    	return (!shooterLowerLimit.get());
    }

	// Starts the compressor
    public void startCompressor() {
        	compressor.start();
    }

    // Drives the piston forward (shooting)
    public void shoot() {
    	shootSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    /*
     *  Retracts the piston (does nothing now since front of piston is disconnected
     *  It is needed to switch the double valve
     */
    public void retract() {
    	shootSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    // 
    public void off() {
    	shootSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    // Enables the vacuum pump
    public void vacuumOn() {
    	vacuumTalon.set(1);
    }

    // Disables the vacuum pump
    public void vacuumOff() {
    	vacuumTalon.set(0);
    }

	// Goes to the encoder count that is passed
    public void goTo(double angle) {
		localAngle = angle;
		lift.set(angle * countPerDegree);
		//lift.enableControl();
	}

    public void goTo(ShooterPosition position) {
    	int index;
    	switch (position) {
    	case down:
    		index = 0;
    		break;
    	case low:
    		index = 1;
    		break;
    	case mid:
    		index = 2;
    		break;
    	case high:
    		index = 3;
    		break;
    	default:
    		index = 0;
    		break;
    	}

    	goTo(PRESET_POSITIONS[index]);
    }

	public void incrementAngle() {
		localAngle++;
		goTo(localAngle);
	}
	
	public void decremntAngle() {
		localAngle--;
		goTo(localAngle);
	}
	
	public void adjustAngle( double adjust) {
		localAngle+=adjust;
		goTo(localAngle);
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
    
	// mostly for deugging updates the smart dashboard with position info
	public void periodic() {
		SmartDashboard.putNumber("ShootLift Desired Pos", lift.getSetpoint());
		SmartDashboard.putNumber("Shaft Position", getPosition());
		SmartDashboard.putNumber("Position Error", getPositionError());
//		SmartDashboard.putBoolean("limit sensor", shooterLowerLimit.get());
	}
    
    public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

