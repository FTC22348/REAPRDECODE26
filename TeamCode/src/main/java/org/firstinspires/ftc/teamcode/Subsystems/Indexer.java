package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class Indexer implements Subsystem {
    public static final Indexer INSTANCE = new Indexer();
    private Indexer() { }
    private final double etpr = 2 * ((((1+(46.0/17.0))) * (1+(46.0/11.0))) * (1+(46.0/11.0)) * 28); //60rpm, and 2:1 bevel gear
    private MotorEx motor = new MotorEx("indexter");
    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .elevatorFF(0)
            .build();
    public Command move60() {
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - etpr/6).requires(this);
    }
    public Command move120() {
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - etpr/3).requires(this);
    }
    public Command back120() {
        return new RunToPosition(controlSystem, motor.getCurrentPosition() + etpr/3).requires(this);
    }
    public Command move2() { //for small adjustments
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - etpr/180).requires(this);
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}