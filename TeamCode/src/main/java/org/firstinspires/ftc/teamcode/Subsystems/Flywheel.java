package org.firstinspires.ftc.teamcode;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class Flywheel implements Subsystem {
    public static final Flywheel INSTANCE = new Flywheel();
    private Flywheel() { }

    public final MotorEx shooter = new MotorEx("manesh");
    public final MotorEx farmer = new MotorEx("ranjit").reversed();

    private final MotorGroup flywheel = new MotorGroup(shooter, farmer);

    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .basicFF(0.01, 0.02, 0.03)
            .build();

    public Command spinUp() {
        return new RunToVelocity(controller, 2500).requires(this);
    }
    public Command stop() {
        return new RunToVelocity(controller, 0.0).requires(this);
    }

    @Override
    public void periodic() {
        flywheel.setPower(controller.calculate(flywheel.getState()));
    }
}