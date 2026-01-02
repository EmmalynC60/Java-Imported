// Makenzie 3/14/2024

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  // motors
  WPI_TalonSRX shootMotor1;
  WPI_TalonSRX shootMotor2;

  



public Shooter(){
  shootMotor1 = new WPI_TalonSRX(Constants.shootMotor1ID);
  shootMotor2 = new WPI_TalonSRX(Constants.shootMotor2ID);

}
public void Shoot(double power){
  // set power going inverse each other
  shootMotor1.set(power);
  shootMotor2.set(-power);
}

public Command shootCommand(double power) {
    return runEnd(() -> {
      Shoot(power);
    }, () -> {
        stop();
    });
  }

  public void stop() {
    shootMotor1.set(0);
    shootMotor2.set(0);
  }

  // Emmalyn 01/02/2026
  /**
   * Clears sticky faults on all shooter motor controllers.
   */
  public void clearStickyFaults() {
    shootMotor1.clearStickyFaults(); // S1
    shootMotor2.clearStickyFaults(); // S2
  }

}
