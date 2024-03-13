// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    // Port numbers for driver and operator gamepads. These correspond with the numbers on the USB
    // tab of the DriverStation
    public static final int kOperatorControllerPort = 1;
    //public static final int kRightDriverControllerPort = 1;
    //public static final int kOperatorControllerPort = 2;
    public static final int kDriverControllerPort = 0;
  }

  public static class DrivetrainConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kLeftRearID = 4;
    public static final int kLeftFrontID = 3;
    public static final int kRightRearID = 6;
    public static final int kRightFrontID = 5;

    //public static final int kPigeonID = 7;

    //public static final double kGearRatio = 8.46;
    //public static final double kWheelDiameter = Units.inchesToMeters(6);

    // Current limit for drivetrain motors
    public static final int kCurrentLimit = 75;
  }

  public static class LauncherConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kFeederID = 10;
    public static final int kLauncherID = 11;
    // Feeder left, launcher right
    // Current limit for launcher and feed wheels
    public static final int kLauncherCurrentLimit = 80;
    public static final int kFeedCurrentLimit = 80;

    // Speeds for wheels when intaking and launching. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kLauncherSpeed = 0.1;
    public static final double kLaunchFeederSpeed = 0.1;
    public static final double kIntakeLauncherSpeed = -0.1;
    public static final double kIntakeFeederSpeed = -0.1; // Changed from -.2

    public static final double kLauncherDelay = 1;
  }
  
  public static class AmpConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kAmpID = 8;
    public static final int kAmpCurrentLimit = 80;
    
    public static final double kAmpIntakeSpeed = 0.1;
    public static final double kAmpOuttakeSpeed = -0.1;
  }

   // Intake
    public static final int k_pivotEncoderId = 0; // change to actual
    public static final int k_intakeLimitSwitchId = 1; // change to actual
    // pivot
    public static final int kPivotMotorId = 2; // change to actual
    // angles
    public static final double k_pivotAngleGround = 0.25; // change to actual
    public static final double k_pivotAngleSource = 0.50; // change to actual
    public static final double k_pivotAngleAmp = 0.75;  // change to actual
    public static final double k_pivotAngleStow = 1.00;  // change to actual
    // encoder offset
    public static final double k_pivotEncoderOffset = 4.25;  // change to actual

    // autonomous
    public static final double autoSpeed = 0.5; // change accordingly


public static final String Intake = null;
}


