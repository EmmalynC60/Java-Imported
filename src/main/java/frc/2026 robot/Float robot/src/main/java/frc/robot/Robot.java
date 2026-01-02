// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {
    // Assign CAN IDs for your Spark Maxes (update to match your wiring)
    private final CANSparkMax leftMain = new CANSparkMax(1, MotorType.kBrushed);
    private final CANSparkMax leftFollower = new CANSparkMax(2, MotorType.kBrushed);
    private final CANSparkMax rightMain = new CANSparkMax(3, MotorType.kBrushed);
    private final CANSparkMax rightFollower = new CANSparkMax(4, MotorType.kBrushed);


    private final DifferentialDrive drive = new DifferentialDrive(leftMain, rightMain);
    private final XboxController controller = new XboxController(0);


    @Override
    public void robotInit() {
        // Set up motor follower relationships
        leftFollower.follow(leftMain);
        rightFollower.follow(rightMain);


        // Set motor inversions as appropriate for your drivetrain
        leftMain.setInverted(true);  // Change to true if needed for wiring
        rightMain.setInverted(false);  // Typically one side inverted for correct motion
    }


    private double applyDeadband(double value, double deadband) {
        return Math.abs(value) > deadband ? value : 0.0;
    }


    @Override
    public void teleopPeriodic() {
        
        double leftSpeed = applyDeadband(controller.getLeftY(), 0.05);
        double rightSpeed = applyDeadband(controller.getRightY(), 0.05);

        // Arcade drive logic with DifferentialDrive
        drive.tankDrive(leftSpeed, rightSpeed);
    }


    // Empty overrides for other periods for clarity & completeness
    @Override public void robotPeriodic() {}
    @Override public void autonomousInit() {}
    @Override public void autonomousPeriodic() {}
    @Override public void teleopInit() {}
    @Override public void disabledInit() {}
    @Override public void disabledPeriodic() {}
    @Override public void testInit() {}
    @Override public void testPeriodic() {}
    @Override public void simulationInit() {}
    @Override public void simulationPeriodic() {}
}

