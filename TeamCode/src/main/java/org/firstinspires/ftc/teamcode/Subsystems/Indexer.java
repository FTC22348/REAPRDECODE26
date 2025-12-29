package org.firstinspires.ftc.teamcode;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class Indexer implements Subsystem {
    public static final Indexer INSTANCE = new Indexer();
    private Indexer() { }

    private MotorEx motor = new MotorEx("indexter");

    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .elevatorFF(0)
            .build();

//    public Command move60() {  //for 43rpm
//        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 649.3).requires(this);
//    }
//    public Command move120() { //for 43rpm
//        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 1298.6).requires(this);
//    }
//    public Command move180() { //for 43rpm
//        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 1947.9).requires(this);
//    }

    public Command move60() {  //for 312rpm
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 89.61497326203207).requires(this);
    }
    public Command move120() { //for 312rpm
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 179.2299465240641).requires(this);
    }
    public Command move180() { //for 312rpm
        return new RunToPosition(controlSystem, motor.getCurrentPosition() - 268.8449197860962).requires(this);
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}