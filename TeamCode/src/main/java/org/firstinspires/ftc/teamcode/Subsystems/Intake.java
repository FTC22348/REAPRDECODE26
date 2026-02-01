package org.firstinspires.ftc.teamcode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }
    public static int velocity = 200;
    private final MotorEx aarav = new MotorEx("malhotra").reversed();
    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .build();

    public Command swallow() {
        return new RunToVelocity(controller, velocity).requires(this);
    }
    public Command off() {
        return new RunToVelocity(controller, 0.0).requires(this);
    }
    public Command spit() { return new RunToVelocity(controller, -100).requires(this); }

    @Override
    public void periodic() {
        aarav.setPower(controller.calculate(aarav.getState()));
    }
}