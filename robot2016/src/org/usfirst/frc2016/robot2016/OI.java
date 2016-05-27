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
    public JoystickButton liftButton;
    public JoystickButton flashlightOnButton;
    public JoystickButton joystickButton1;
    public Joystick driveLeft;
    public JoystickButton driveStraightButton;
    public JoystickButton driveStraightButtonDone;
    public Joystick driveRight;
    public JoystickButton shooterHomeButton;
    public JoystickButton shootPresetLowButton;
    public JoystickButton shootPresetMedButton;
    public JoystickButton shootPresetHighButton;
    public JoystickButton shootOperatorButton;
    public JoystickButton scoop2Floor;
    public JoystickButton scoop2Home;
    public JoystickButton lowGoalButton;
    public JoystickButton scoop2LowBarButton;
    public JoystickButton scoop2ClearOfShooterButton;
    public Joystick operatorJoy;
    public JoystickButton deployHook;
    public Joystick cCI;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        cCI = new Joystick(3);
        
        deployHook = new JoystickButton(cCI, 1);
        deployHook.whenPressed(new CompDeployHook());
        operatorJoy = new Joystick(2);
        
        scoop2ClearOfShooterButton = new JoystickButton(operatorJoy, 6);
        scoop2ClearOfShooterButton.whenPressed(new ScoopToClearOfShooter());
        scoop2LowBarButton = new JoystickButton(operatorJoy, 4);
        scoop2LowBarButton.whenPressed(new ScoopToClearLowBar());
        lowGoalButton = new JoystickButton(operatorJoy, 2);
        lowGoalButton.whenPressed(new CompParallelLowGoal());
        scoop2Home = new JoystickButton(operatorJoy, 5);
        scoop2Home.whenPressed(new CompScoopHome());
        scoop2Floor = new JoystickButton(operatorJoy, 3);
        scoop2Floor.whenPressed(new ScoopToFloor());
        shootOperatorButton = new JoystickButton(operatorJoy, 1);
        shootOperatorButton.whenPressed(new CompDoHighGoal());
        shootPresetHighButton = new JoystickButton(operatorJoy, 11);
        shootPresetHighButton.whenPressed(new CompShooterHigh());
        shootPresetMedButton = new JoystickButton(operatorJoy, 9);
        shootPresetMedButton.whenPressed(new CompShooterMedium());
        shootPresetLowButton = new JoystickButton(operatorJoy, 7);
        shootPresetLowButton.whenPressed(new ShooterPreset1());
        shooterHomeButton = new JoystickButton(operatorJoy, 8);
        shooterHomeButton.whenPressed(new CompShootHome());
        driveRight = new Joystick(1);
        
        driveStraightButtonDone = new JoystickButton(driveRight, 11);
        driveStraightButtonDone.whenReleased(new TankDrive());
        driveStraightButton = new JoystickButton(driveRight, 11);
        driveStraightButton.whenPressed(new GoStraight());
        driveLeft = new Joystick(0);
        
        joystickButton1 = new JoystickButton(driveLeft, 7);
        joystickButton1.whenPressed(new FlashlightOff());
        flashlightOnButton = new JoystickButton(driveLeft, 6);
        flashlightOnButton.whenPressed(new FlashlightOn());
        liftButton = new JoystickButton(driveLeft, 2);
        liftButton.whenPressed(new CompDeployLift());


        // SmartDashboard Buttons
        SmartDashboard.putData("ManualAim", new ManualAim());
        SmartDashboard.putData("ManualScoop", new ManualScoop());
        SmartDashboard.putData("TankDrive", new TankDrive());
        SmartDashboard.putData("CompDeployLift", new CompDeployLift());
        SmartDashboard.putData("Manual Winch", new ManualWinch());
        SmartDashboard.putData("AutoHighGoalSeq", new AutoHighGoalSeq());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // Adding special case here that robot builder can't handle
        
        // putting buttons in by hand due to bug in new robotbuilder
        //SmartDashboard.putData("shoot fwd", new ShooterForward());        
        //SmartDashboard.putData("shooter retract", new ShooterRetract());
        //SmartDashboard.putData("apply vacuum", new ApplyVacuum());
        //SmartDashboard.putData("display Contour data", new DisplayCameraResults());        
        //SmartDashboard.putData("calibrate shooter", new CalibrateShooter());        
        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive(0,0));
        
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

    public Joystick getCCI() {
        return cCI;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

