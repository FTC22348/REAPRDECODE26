package org.firstinspires.ftc.teamcode.OpModes;

//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Flicker;
import org.firstinspires.ftc.teamcode.Subsystems.Indexer;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

@TeleOp(name = "let's fry this fish")
public class Teleoperated extends NextFTCOpMode {
    public Teleoperated() {
        addComponents(
                new SubsystemComponent(Indexer.INSTANCE, Flicker.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    private final MotorEx aarav = new MotorEx("malhotra");
    private final MotorEx shooter = new MotorEx("manesh");
    private final MotorEx farmer = new MotorEx("ranjit").reversed();
    private final MotorGroup flywheel = new MotorGroup(shooter, farmer);
    private final MotorEx frontLeftMotor = new MotorEx("kumar").reversed();
    private final MotorEx frontRightMotor = new MotorEx("dexter");
    private final MotorEx backLeftMotor = new MotorEx("relocator").reversed();
    private final MotorEx backRightMotor = new MotorEx("teleporter");
    @Override
        public void onStartButtonPressed() {
            Command driverControlled = new MecanumDriverControlled(
                    frontLeftMotor,
                    frontRightMotor,
                    backLeftMotor,
                    backRightMotor,
                    Gamepads.gamepad1().leftStickY().negate(),
                    Gamepads.gamepad1().leftStickX(),
                    Gamepads.gamepad1().rightStickX()
            );
        driverControlled.schedule();

        Gamepads.gamepad2().a().whenBecomesTrue(() -> Indexer.INSTANCE.move60().schedule());

        Gamepads.gamepad2().b().whenBecomesTrue(() -> Indexer.INSTANCE.move180().schedule());

        Gamepads.gamepad2().rightBumper().whenBecomesTrue(() -> Indexer.INSTANCE.move120().schedule());

        Gamepads.gamepad2().rightStickY().greaterThan(0.2).whenBecomesTrue(new SetPower(flywheel, 0));

        Gamepads.gamepad2().rightStickY().lessThan(-0.2).whenBecomesTrue(new SetPower(flywheel, -1.2/3.0));

        Gamepads.gamepad2().rightStickX().greaterThan(0.2).whenBecomesTrue(new SetPower(aarav, -1));

        Gamepads.gamepad2().rightStickX().lessThan(-0.2).whenBecomesTrue(new SetPower(aarav, 0));

        Gamepads.gamepad2().rightTrigger().greaterThan(0.2).whenBecomesTrue(Flicker.INSTANCE.smackThat);

        Gamepads.gamepad2().rightTrigger().greaterThan(0.2).whenBecomesFalse(Flicker.INSTANCE.allOnTheFloor);
    }
}