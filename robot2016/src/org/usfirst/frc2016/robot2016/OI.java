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

import org.usfirst.frc2016.robot2016.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shootButton;
    public Joystick driveLeft;
    public Joystick driveRight;
    public JoystickButton shooterHomeButton;
    public JoystickButton shootPreset1Button;
    public JoystickButton shootPreset2Button;
    public JoystickButton shootPreset3Button;
    public JoystickButton shootOperatorButton;
    public JoystickButton scoop2Floor;
    public JoystickButton scoop2Home;
    public JoystickButton lowGoalButton;
    public Joystick operatorJoy;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operatorJoy = new Joystick(2);
        
        lowGoalButton = new JoystickButton(operatorJoy, 4);
        lowGoalButton.whenPressed(new CompDoLowGoal());
        scoop2Home = new JoystickButton(operatorJoy, 3);
        scoop2Home.whenPressed(new ScoopHome());
        scoop2Floor = new JoystickButton(operatorJoy, 5);
        scoop2Floor.whenPressed(new ScoopToFloor());
        shootOperatorButton = new JoystickButton(operatorJoy, 1);
        shootOperatorButton.whenPressed(new Shoot());
        shootPreset3Button = new JoystickButton(operatorJoy, 10);
        shootPreset3Button.whenPressed(new ShooterPreset3());
        shootPreset2Button = new JoystickButton(operatorJoy, 9);
        shootPreset2Button.whenPressed(new ShooterPreset2());
        shootPreset1Button = new JoystickButton(operatorJoy, 8);
        shootPreset1Button.whenPressed(new ShooterPreset1());
        shooterHomeButton = new JoystickButton(operatorJoy, 7);
        shooterHomeButton.whenPressed(new Shooter2Home());
        driveRight = new Joystick(1);
        
        driveLeft = new Joystick(0);
        
        shootButton = new JoystickButton(driveLeft, 1);
        shootButton.whenPressed(new Shoot());


        // SmartDashboard Buttons
        SmartDashboard.putData("ApplyVacuum", new ApplyVacuum());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("TankDrive", new TankDrive());
        SmartDashboard.putData("CalibrateShooter", new CalibrateShooter());
        SmartDashboard.putData("Shooter2Home", new Shooter2Home());
        SmartDashboard.putData("CalGyro", new CalGyro());
        SmartDashboard.putData("ResetGyro", new ResetGyro());
        SmartDashboard.putData("ManualAim", new ManualAim());
        SmartDashboard.putData("AutoMote", new AutoMote());
        SmartDashboard.putData("AutoSallyPort", new AutoSallyPort());
        SmartDashboard.putData("AutoLowBar", new AutoLowBar());
        SmartDashboard.putData("AutoPortcullis", new AutoPortcullis());
        SmartDashboard.putData("AutoRamparts", new AutoRamparts());
        SmartDashboard.putData("AutoDrawbridge", new AutoDrawbridge());
        SmartDashboard.putData("AutoRoughTerrain", new AutoRoughTerrain());
        SmartDashboard.putData("AutoRockWall", new AutoRockWall());
        SmartDashboard.putData("AutoChevaldeFrise", new AutoChevaldeFrise());
        SmartDashboard.putData("lineFind", new lineFind());
        SmartDashboard.putData("ShooterPushBall", new ShooterPushBall());
        SmartDashboard.putData("PushBall", new PushBall());
        SmartDashboard.putData("HookReset", new HookReset());
        SmartDashboard.putData("HookDeploy", new HookDeploy());
        SmartDashboard.putData("ShooterPreset1", new ShooterPreset1());
        SmartDashboard.putData("ShooterPreset2", new ShooterPreset2());
        SmartDashboard.putData("ShooterPreset3", new ShooterPreset3());
        SmartDashboard.putData("ScoopHome", new ScoopHome());
        SmartDashboard.putData("ScoopToFloor", new ScoopToFloor());
        SmartDashboard.putData("ManualScoop", new ManualScoop());
        SmartDashboard.putData("RollerPOV", new RollerPOV());
        SmartDashboard.putData("ScoopToClearOfShooter", new ScoopToClearOfShooter());
        SmartDashboard.putData("ScoopToShoot", new ScoopToShoot());
        SmartDashboard.putData("ScoopToClearLowBar", new ScoopToClearLowBar());
        SmartDashboard.putData("CompScoopHome", new CompScoopHome());
        SmartDashboard.putData("CheckScoopClearance", new CheckScoopClearance());
        SmartDashboard.putData("CheckShooterClearence", new CheckShooterClearence());
        SmartDashboard.putData("CompShootHome", new CompShootHome());
        SmartDashboard.putData("CompDoLowGoal", new CompDoLowGoal());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // Adding special case here that robot builder can't handle
        lowGoalButton.whenReleased(new RollerOff());

        
        
        // putting buttons in by hand due to bug in new robotbuilder
        //SmartDashboard.putData("shoot fwd", new ShooterForward());        
        //SmartDashboard.putData("shooter retract", new ShooterRetract());
        //SmartDashboard.putData("apply vacuum", new ApplyVacuum());
        SmartDashboard.putData("display Contour data", new DisplayCameraResults());        
        //SmartDashboard.putData("calibrate shooter", new CalibrateShooter());        
        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());
        
        //SmartDashboard.putData("decrementLift", new DecrementShooterLift());        
    
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveLeft() {
        return driveLeft;
    }

    public Joystick getDriveRight() {
        return driveRight;
    }

    public Joystick getOperatorJoy() {
        return operatorJoy;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

