package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import dev.nextftc.hardware.impl.MotorEx;

@Autonomous(name = "test")
public class AutonTester extends LinearOpMode {

    private MotorEx frontLeftMotor;
    private MotorEx frontRightMotor;
    private MotorEx backLeftMotor;
    private MotorEx backRightMotor;

    @Override
    public void runOpMode() {

        frontLeftMotor = new MotorEx("kumar").reversed();
        frontRightMotor = new MotorEx("dexter");
        backLeftMotor = new MotorEx("relocator").reversed();
        backRightMotor = new MotorEx("teleporter");

        waitForStart();

        frontLeftMotor.setPower(1);
        frontRightMotor.setPower(1);
        backLeftMotor.setPower(1);
        backRightMotor.setPower(1);

        sleep(1000);

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}