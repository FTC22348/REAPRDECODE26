package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class Indexer implements Subsystem {
    public static final Indexer INSTANCE = new Indexer();
    private Indexer() { }
    private final double etpr = ((((1+(46/17))) * (1+(46/11))) * (1+(46/11)) * 28); //60rpm
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
    public Command move180() {
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - etpr/2).requires(this);
    }
    public Command move360() { //why? well why not ig
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - etpr).requires(this);
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}