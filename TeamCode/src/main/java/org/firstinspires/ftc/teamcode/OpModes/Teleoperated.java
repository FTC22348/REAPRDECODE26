package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.Flicker;
import org.firstinspires.ftc.teamcode.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.Subsystems.Indexer;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.extensions.pedro.PedroDriverControlled;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "let's fry this fish")
public class Teleoperated extends NextFTCOpMode {
    public Teleoperated() {
        addComponents(
                new SubsystemComponent(Indexer.INSTANCE, Flicker.INSTANCE, Flywheel.INSTANCE, Intake.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    private final MotorEx aarav = new MotorEx("malhotra");
    @Override
    public void onStartButtonPressed() {
        DriverControlledCommand driverControlled = new PedroDriverControlled(
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX().negate(),
                Gamepads.gamepad1().rightStickX().negate()
        );
        driverControlled.schedule();

        Gamepads.gamepad2().a().whenBecomesTrue(() -> Indexer.INSTANCE.move60().schedule());

        Gamepads.gamepad2().b().whenBecomesTrue(() -> Indexer.INSTANCE.move2().schedule());

        Gamepads.gamepad2().leftBumper().whenBecomesTrue(() -> Indexer.INSTANCE.back120().schedule());

        Gamepads.gamepad2().rightBumper().whenBecomesTrue(() -> Indexer.INSTANCE.move120().schedule());

        Gamepads.gamepad2().rightStickY().greaterThan(0.2).whenBecomesTrue(Flywheel.INSTANCE.stop());

        Gamepads.gamepad2().rightStickY().lessThan(-0.2).whenBecomesTrue(Flywheel.INSTANCE.spinUp());

        Gamepads.gamepad1().rightTrigger().greaterThan(0.2).whenBecomesTrue(Intake.INSTANCE.swallow());

        Gamepads.gamepad1().rightTrigger().greaterThan(0.2).whenBecomesFalse(Intake.INSTANCE.off());

        Gamepads.gamepad1().rightBumper().whenBecomesTrue(Intake.INSTANCE.spit());

        Gamepads.gamepad1().rightBumper().whenBecomesFalse(Intake.INSTANCE.off());

        Gamepads.gamepad2().leftTrigger().greaterThan(0.2).whenBecomesTrue(Intake.INSTANCE.swallow());

        Gamepads.gamepad2().leftTrigger().greaterThan(0.2).whenBecomesFalse(Intake.INSTANCE.off());

        Gamepads.gamepad1().leftBumper().whenBecomesTrue(Intake.INSTANCE.spit());

        Gamepads.gamepad1().leftBumper().whenBecomesFalse(Intake.INSTANCE.off());

        Gamepads.gamepad2().rightTrigger().greaterThan(0.2).whenBecomesTrue(Flicker.INSTANCE.smackThat);

        Gamepads.gamepad2().rightTrigger().greaterThan(0.2).whenBecomesFalse(Flicker.INSTANCE.allOnTheFloor);
    }
}