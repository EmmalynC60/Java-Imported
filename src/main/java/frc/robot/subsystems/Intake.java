package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import frc.robot.Helpers;


public class Intake extends SubsystemBase {
  private static final double k_pivotMotorP = 0.12;
  private static final double k_pivotMotorI = 0.0;
  private static final double k_pivotMotorD = 0.001;


  private final PIDController m_pivotPID = new PIDController(k_pivotMotorP, k_pivotMotorI, k_pivotMotorD);


  private final DutyCycleEncoder m_pivotEncoder = new DutyCycleEncoder(Constants.k_pivotEncoderId);
  private final DigitalInput m_IntakeLimitSwitch = new DigitalInput(Constants.k_intakeLimitSwitchId);






  /*-------------------------------- Private instance variables ---------------------------------*/
  private static Intake mInstance;
  private PeriodicIO m_periodicIO;


  public static Intake getInstance() {
    if (mInstance == null) {
      mInstance = new Intake();
    }
    return mInstance;
  }


    private WPI_TalonSRX mPivotMotor;


  public Intake() {
    super("Intake");


    mPivotMotor = new WPI_TalonSRX(Constants.kPivotMotorId);
   // mPivotMotor.restoreFactoryDefaults
   // mPivotMotor.setIdleMode(WPI_TalonSRX.IdleMode.kBrake);
  // mPivotMotor.setSmartCurrentLimit(10);


    m_periodicIO = new PeriodicIO();
  }


  private static class PeriodicIO {
    // Input: Desired state
    PivotTarget pivot_target = PivotTarget.STOW;
    IntakeState intake_state = IntakeState.NONE;


    // Output: Motor set values
    double intake_pivot_voltage = 0.0;
    double intake_speed = 0.0;
  }


  public enum PivotTarget {
    NONE,
    GROUND,
    SOURCE,
    AMP,
    STOW
  }


  public enum IntakeState {
    NONE,
    INTAKE,
    EJECT,
    PULSE,
    FEED_SHOOTER,
  }


  /*-------------------------------- Generic Subsystem Functions --------------------------------*/


  //@Override
  public void periodic() {
   // checkAutoTasks();


    // Pivot control
    double pivot_angle = pivotTargetToAngle(m_periodicIO.pivot_target);
   // m_periodicIO.intake_pivot_voltage = m_pivotPID.calculate(getPivotAngleDegrees(), pivot_angle);


    // If the pivot is at exactly 0.0, it's probably not connected, so disable it
    if (m_pivotEncoder.get() == 0.0) {
      m_periodicIO.intake_pivot_voltage = 0.0;
    }


    // Intake control
   // m_periodicIO.intake_speed = intakeStateToSpeed(m_periodicIO.intake_state);
    //putString("State", m_periodicIO.intake_state.toString());
  }


  //@Override
  public void writePeriodicOutputs() {
    mPivotMotor.setVoltage(m_periodicIO.intake_pivot_voltage);


    }


  //@Override
  public void stop() {


  }


  //@Override
  public void outputTelemetry() {


    putNumber("Pivot/Abs Enc (get)", m_pivotEncoder.get());
    putNumber("Pivot/Abs Enc (getAbsolutePosition)", m_pivotEncoder.getAbsolutePosition());
    //putNumber("Pivot/Abs Enc (getPivotAngleDegrees)", getPivotAngleDegrees());
    putNumber("Pivot/Setpoint", pivotTargetToAngle(m_periodicIO.pivot_target));


    putNumber("Pivot/Power", m_periodicIO.intake_pivot_voltage);
    putNumber("Pivot/Current", mPivotMotor.getOutputCurrent());


    putBoolean("Limit Switch", getIntakeHasNote());
  }


  private void putBoolean(String string, boolean intakeHasNote) {
  }


  private void putNumber(String string, double absolutePosition) {
  }

  //@Override
  public void reset() {
  }


  public double pivotTargetToAngle(PivotTarget target) {
    switch (target) {
      case GROUND:
        return Constants.k_pivotAngleGround;
      case SOURCE:
        return Constants.k_pivotAngleSource;
      case AMP:
        return Constants.k_pivotAngleAmp;
      case STOW:
        return Constants.k_pivotAngleStow;
      default:
        // "Safe" default
        return 180;
    }
  }

  public void pivot(double speed){
     mPivotMotor.set(speed);
  }

 
 
  /*---------------------------------- Custom Public Functions ----------------------------------*/


  public IntakeState getIntakeState() {
    return m_periodicIO.intake_state;
  }


  /*public double getPivotAngleDegrees() {
    double value = m_pivotEncoder.getAbsolutePosition() -
        Constants.k_pivotEncoderOffset + 0.5;


    return Units.rotationsToDegrees(Helpers.modRotations(value));
  }
/* */

  public boolean getIntakeHasNote() {
    // NOTE: this is intentionally inverted, because the limit switch is normally
    // closed
    return !m_IntakeLimitSwitch.get();
  }


  // Pivot helper functions
  public void goToGround() {
    m_periodicIO.pivot_target = PivotTarget.GROUND;
    m_periodicIO.intake_state = IntakeState.INTAKE;
   
  }


  public void goToSource() {
    m_periodicIO.pivot_target = PivotTarget.SOURCE;
    m_periodicIO.intake_state = IntakeState.NONE;
  }


  public void goToAmp() {
    m_periodicIO.pivot_target = PivotTarget.SOURCE;
    m_periodicIO.intake_state = IntakeState.NONE;
  }


  public void goToStow() {
    m_periodicIO.pivot_target = PivotTarget.STOW;
    m_periodicIO.intake_state = IntakeState.NONE;
  }












  public void setState(IntakeState state) {
    m_periodicIO.intake_state = state;
  }


  public void setPivotTarget(PivotTarget target) {
    m_periodicIO.pivot_target = target;
  }


  /*---------------------------------- Custom Private Functions ---------------------------------*/
  /*private void checkAutoTasks() {
    // If the intake is set to GROUND, and the intake has a note, and the pivot is
    // close to it's target
    // Stop the intake and go to the SOURCE position
    if (m_periodicIO.pivot_target == PivotTarget.GROUND && getIntakeHasNote() && isPivotAtTarget()) {
      m_periodicIO.pivot_target = PivotTarget.STOW;
      m_periodicIO.intake_state = IntakeState.NONE;
     
    }
  }
*/

 /*  private boolean isPivotAtTarget() {
    return Math.abs(getPivotAngleDegrees() - pivotTargetToAngle(m_periodicIO.pivot_target)) < 5;
  }
   */
}


