package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class robot extends TimedRobot {
    // Assign CAN IDs for your Spark Maxes (update to match your wiring)
    private final CANSparkMax leftMain = new CANSparkMax(1, MotorType.kBrushed);
    private final CANSparkMax leftFollower = new CANSparkMax(2, MotorType.kBrushed);
    private final CANSparkMax rightMain = new CANSparkMax(3, MotorType.kBrushed);
    private final CANSparkMax rightFollower = new CANSparkMax(4, MotorType.kBrushed);

    private final DifferentialDrive drive = new DifferentialDrive(leftMain, rightMain);
    private final XboxController controller = new XboxController(0);

    @Override
    public void robotInit() {
        leftFollower.follow(leftMain);
        rightFollower.follow(rightMain);

        leftMain.setInverted(false); // Change to true if needed for wiring
        rightMain.setInverted(true); // Typically invert one side
    }

    private double applyDeadband(double value, double deadband) {
        return Math.abs(value) > deadband ? value : 0.0;
    }

    @Override
    public void teleopPeriodic() {
        // Tank drive control
        double leftSpeed = applyDeadband(-controller.getLeftY(), 0.05) * 0.5;
        double rightSpeed = applyDeadband(-controller.getRightY(), 0.05) * 0.5;
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    // Required empty overrides for all robot modes
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