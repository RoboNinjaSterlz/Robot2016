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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import org.usfirst.frc2016.robot2016.commands.*;
import org.usfirst.frc2016.robot2016.subsystems.*;

import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    private final double DELAYPERCOUNT = .02;
    private final double CAL_TIME_LIMIT = 4;
    private double calAttemptTimer;
    private boolean calTimerExpired;
    
    
    /*
     * Labels for auto defenses 
     */
	final String lowBarAuto = "Low Bar Auto";
    final String portcullisAuto = "Portcullis Auto";
    final String chevaldeFriseAuto = "Cheval de Frise Auto";
    final String moatAuto = "Moat Auto";
    final String rampartsAuto = "Ramparts Auto";
    final String drawbridgeAuto = "Drawbridge Auto";
    final String sallyPortAuto = "Sally Port Auto";
    final String rockWallAuto = "Rock Wall Auto";
    final String roughTerrainAuto = "Rough Terrain Auto";
    final String highGoalAuto = "High Goal Auto";

    /*
     * What autonomous command to run
     * and options on the smart dashboard for auto
     */
    Command autonomousCommand;
    
    SendableChooser positionChooser;
    SendableChooser defenseChooser;
    
    /*
     * Flag to indicate that all systems are calibrated and ready
     */
    public static boolean robotIsCalibrated = false;
    
    public static OI oi;
    public static PowerDistributionPanel pdPanel;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Shooter shooter;
    public static Drivetrain drivetrain;
    public static Vision vision;
    public static Scoop scoop;
    public static Gyro gyro;
    public static ShootElevator shootElevator;
    public static Intake intake;
    public static Hook hook;
    public static LiftRelease liftRelease;
    public static Flashlight flashlight;
    public static Winch winch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    RobotPrefs robotPrefs; 
    public int startingPosition;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	robotIsCalibrated = false; //!!!!!!!!!!!!
    	robotPrefs = new RobotPrefs();
    	calAttemptTimer = 0;
    	calTimerExpired = false;
    	CameraServer server = CameraServer.getInstance();
    	server.setQuality(150);
    	server.startAutomaticCapture("cam0");
    	
    	/*
    	 * The following line loads the grip program on the roborio
    	 * comment out the line while debugging the filters on the pc.
    	 */
    	//loadGrip();

        SmartDashboard.putData(Scheduler.getInstance());
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shooter = new Shooter();
        drivetrain = new Drivetrain();
        vision = new Vision();
        scoop = new Scoop();
        gyro = new Gyro();
        shootElevator = new ShootElevator();
        intake = new Intake();
        hook = new Hook();
        liftRelease = new LiftRelease();
        flashlight = new Flashlight();
        winch = new Winch();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        pdPanel = new PowerDistributionPanel();
        
        // Set up the position choices
    	positionChooser = new SendableChooser();
        positionChooser.addDefault("Position 1", 1);
        positionChooser.addObject("Position 2", 2);
        positionChooser.addObject("Position 3", 3);
        positionChooser.addObject("Position 4", 4);
        positionChooser.addObject("Position 5", 5);
        SmartDashboard.putData("Auto position choices", positionChooser);

        // Set up defense choices
        defenseChooser = new SendableChooser();
        defenseChooser.addDefault("Low Bar Auto",  new AutoLowBar());
        defenseChooser.addObject("Moat Auto", new AutoMote());
        defenseChooser.addObject("Ramparts Auto", new AutoRamparts());
        defenseChooser.addObject("Rock Wall Auto", new AutoRockWall());
        defenseChooser.addObject("Rough Terrain Auto", new AutoRoughTerrain());
        defenseChooser.addObject("Portcullis Auto",  new AutoPortcullis());
        defenseChooser.addObject("Cheval de Frise Auto", new AutoChevaldeFrise());
        //defenseChooser.addObject("Drawbridge Auto", new AutoDrawbridge());
        defenseChooser.addObject("Do Nothing Auto", new AutoSallyPort());
        defenseChooser.addObject("High Goal Auto", new AutoHighGoal());
        SmartDashboard.putData("Auto defense choices", defenseChooser);
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        robotPrefs.setupPrefs();
        robotPrefs.doLoadPrefs();
        shooter.startCompressor();
        loadGrip();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        
    	startingPosition = (int) positionChooser.getSelected();
    	//SmartDashboard.putNumber("Starting Position", startingPosition);

        autonomousCommand = (Command) defenseChooser.getSelected();
        // schedule the autonomous command (example)
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
		if (!robotIsCalibrated) {
			calibrateRobot();
			if (calTimerExpired) {
		    	Scheduler.getInstance().run();
			}
		}
		else {
	    	Scheduler.getInstance().run();
		}
        shootElevator.periodic();
        scoop.periodic();
        gyro.periodic();
        updateDashboard();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running whens
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
		robotPrefs.periodic();

		if (!robotIsCalibrated) {
			calibrateRobot();
			if (calTimerExpired) {
		    	Scheduler.getInstance().run();
			}
		}
		else {
	    	Scheduler.getInstance().run();
		}
        shootElevator.periodic();
        scoop.periodic();
        gyro.periodic();
        updateDashboard();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void loadGrip() {
        /* Run GRIP in a new process */
        try {
            new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }    	
    }
    
    private void calibrateRobot() {
    	/*
    	 * 1. make sure the scoop is out of the way
    	 * 2. calibrate and home the shooter elevator
    	 * 3. calibrate and home the scoop 
    	 */
    	
    	if (!robotIsCalibrated){
    		if ((calAttemptTimer) <= CAL_TIME_LIMIT/DELAYPERCOUNT) {
    			calAttemptTimer ++;
    		}
    		else {
    			calTimerExpired = true;
    		}
    		
    		// Perform quick check in case everyone is home
    		if (scoop.isScoopAtHome() && shootElevator.isShooterAtHome()) {
    			// Need to make the calls to switch mode to position
    			shootElevator.doCalibrate();
				scoop.doCalibrate();
				robotIsCalibrated = true;
				// Calibration done
				return;
    		}
    		// Next simple case is elevator is at home doesn't matter where
    		// scoop is
    		if (!shootElevator.isCalibrated() && shootElevator.isShooterAtHome()) {
    			// Call to set to position mode and get is cal set
    			shootElevator.doCalibrate();
    		}
    		
    		/*
    		 *  Now it gets complicated we need to do something with the scoop
    		 *  before we can safely move the shooter
    		 *  Two possibilities exist
    		 *  1. Scoop is out enough to cal the shooter
    		 *  2. Shooter is in the way.
    		 *  
    		 *  For case to just move the scoop to the safe zone and it will become
    		 *  case 1.
    		 *  There isn't much advantage to doing the scoop first since at the end
    		 *  it will need to be in the home position. This will just add another step that
    		 *  calibrate will fix.
    		 */
    		// Case 1 scoop is clear of the shooter
    		if (!shootElevator.isCalibrated() && scoop.getSafeZoneFlag() ){
    			// Call to set to position mode and get is cal set
    				shootElevator.doCalibrate();
   			}

    		/* Case 2 scoop is in the way
    		 * Note using the flag of safe zone so we don't detect
    		 * transition before moveToSafeZone does and never call to stop the motor.
    		 */
    		if (!shootElevator.isCalibrated() && !scoop.getSafeZoneFlag() ){
    			// This will be called every 20 ms until we get into case 1 above
    			scoop.moveToSafeZone();
    		}
    		
    		// By now the shoot elevator should be at home and we can finally do the scoop
    		if (shootElevator.isCalibrated()) {
   
    			if (!scoop.isCalibrated() ){
    				scoop.doCalibrate();
    			}
    			else {
    				// May never get here since the first if may handle this
    				// depends on timing.
    				robotIsCalibrated = true;
    			}
    		}
    	}
    }
    
    public boolean isCalibrated() {
    	return robotIsCalibrated;
    }
    
    private void updateDashboard() {
    	SmartDashboard.putBoolean("Shooter Calibrated", shootElevator.isCalibrated());
        SmartDashboard.putBoolean("Shooter At Zero", shootElevator.isShooterAtHome());
    	SmartDashboard.putBoolean("Shoop Calibrated", scoop.isCalibrated());
        SmartDashboard.putBoolean("Scoop At Zero", scoop.isScoopAtHome());
    	SmartDashboard.putBoolean("Robot Calibrated",robotIsCalibrated);
    	SmartDashboard.putBoolean("Scoop in safe Zone", scoop.isScoopInSafeZone());
		SmartDashboard.putNumber("PD Port 5 Current", pdPanel.getCurrent(5));
		//SmartDashboard.putBoolean("Is Deployed", Robot.hook.isDeployed());
    	
    	}
}
