/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package edu.wpi.first.wpilibj.templates;
package org.usfirst.frc2016.robot2016;

/**
 *
 * @author Montagna
 */
// This class provides the system defaults
// These should be updated after tuning the cRio NV RAM incase we need to change
// the cRio
public class Defaults {
    
    // Drive train voltage limit
    protected static final double DRIVETRAIN_VOLTAGE_LIMIT_DEFAULT = .95;

// Autonomous Time Defaults
    protected static final double AUT_SHOOTING_DELAY = .5;

// Autonomous Other Defaults
    protected static final double AUT_ENCODER_DISTANCE_10FT = 100; //Distance in inches
    protected static final double AUT_RIGHT_Y = -.8;
    protected static final double AUT_LEFT_Y = .8;

    
// Shooter angle presets
    protected static final double SHOOTERANGLE1 = 2409;
    protected static final double SHOOTERANGLE2 = 5000;
    protected static final double SHOOTERANGLE3 = 6100;

    protected static final double SCOOPER_FLOOR = -3190;
    protected static final double SCOOPER_CLEAR_OF_SHOOTER = -650;
    protected static final double SCOOPER_LOW_BAR = -2960;
    protected static final double SCOOPER_SHOOT = -2500;
}

