// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2016.robot2016;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Button Defines
	public static final int
	
    // Button to load prefs
	PREFS_BUTTON = 2
	;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Compressor shooterCompressor;
    public static CANTalon shooterLift;
    public static SpeedController shooterVacuumTalon;
    public static DigitalInput shooterShooterLowerLimit;
    public static Solenoid shooterShootSolenoid;
    public static Solenoid shooterPushSolenoid;
    public static SpeedController drivetrainLeftdrive;
    public static SpeedController drivetrainRightdrive;
    public static RobotDrive drivetrainRobotDrive;
    public static Encoder drivetrainLeftEncoder;
    public static Encoder drivetrainRightEncoder;
    public static CANTalon scoopLift;
    public static SpeedController scoopRollerIntake;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shooterCompressor = new Compressor(0);
        
        
        shooterLift = new CANTalon(1);
        LiveWindow.addActuator("Shooter", "Lift", shooterLift);
        
        shooterVacuumTalon = new Spark(3);
        LiveWindow.addActuator("Shooter", "VacuumTalon", (Spark) shooterVacuumTalon);
        
        shooterShooterLowerLimit = new DigitalInput(4);
        LiveWindow.addSensor("Shooter", "ShooterLowerLimit", shooterShooterLowerLimit);
        
        shooterShootSolenoid = new Solenoid(0, 0);
        LiveWindow.addActuator("Shooter", "ShootSolenoid", shooterShootSolenoid);
        
        shooterPushSolenoid = new Solenoid(0, 1);
        LiveWindow.addActuator("Shooter", "PushSolenoid", shooterPushSolenoid);
        
        drivetrainLeftdrive = new Spark(0);
        LiveWindow.addActuator("Drivetrain", "Leftdrive", (Spark) drivetrainLeftdrive);
        
        drivetrainRightdrive = new Spark(1);
        LiveWindow.addActuator("Drivetrain", "Rightdrive", (Spark) drivetrainRightdrive);
        
        drivetrainRobotDrive = new RobotDrive(drivetrainLeftdrive, drivetrainRightdrive);
        
        drivetrainRobotDrive.setSafetyEnabled(true);
        drivetrainRobotDrive.setExpiration(0.1);
        drivetrainRobotDrive.setSensitivity(0.5);
        drivetrainRobotDrive.setMaxOutput(0.95);
        drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drivetrainLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "LeftEncoder", drivetrainLeftEncoder);
        drivetrainLeftEncoder.setDistancePerPulse(1.0);
        drivetrainLeftEncoder.setPIDSourceType(PIDSourceType.kRate);
        drivetrainRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "RightEncoder", drivetrainRightEncoder);
        drivetrainRightEncoder.setDistancePerPulse(1.0);
        drivetrainRightEncoder.setPIDSourceType(PIDSourceType.kRate);
        scoopLift = new CANTalon(0);
        LiveWindow.addActuator("Scoop", "Lift", scoopLift);
        
        scoopRollerIntake = new Spark(2);
        LiveWindow.addActuator("Scoop", "RollerIntake", (Spark) scoopRollerIntake);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
