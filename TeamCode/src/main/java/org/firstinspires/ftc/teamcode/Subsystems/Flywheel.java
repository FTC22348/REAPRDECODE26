package org.firstinspires.ftc.teamcode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class Flywheel implements Subsystem {
    public static final Flywheel INSTANCE = new Flywheel();
    private Flywheel() { }
    public static int velocity = 870; //for like 18 inches away
    public final MotorEx shooter = new MotorEx("manesh").reversed();
    public final MotorEx farmer = new MotorEx("ranjit");
    private final MotorGroup flywheel = new MotorGroup(shooter, farmer);
    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .build();

    public Command spinUp() {
        return new RunToVelocity(controller, velocity).requires(this);
    }
    public Command stop() {
        return new RunToVelocity(controller, 0.0).requires(this);
    }

    @Override
    public void periodic() {
        flywheel.setPower(controller.calculate(flywheel.getState()));
    }
}