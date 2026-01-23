package org.firstinspires.ftc.teamcode.OpModes;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Flicker;
import org.firstinspires.ftc.teamcode.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.Subsystems.Indexer;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "red dream")
@Configurable
public class Red_Dream_Auton extends NextFTCOpMode {
    public Red_Dream_Auton() {
        addComponents(
                new SubsystemComponent(Indexer.INSTANCE, Flicker.INSTANCE, Flywheel.INSTANCE, Intake.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }
    private PathChain path1, path2, path3, path4;
    private Command spinIt = new InstantCommand(()->{Indexer.INSTANCE.move120().schedule();});
    @Override
    public void onInit() {
        path1 = follower().pathBuilder().addPath(
                        new BezierLine(
                                new Pose(122.579, 124.055),
                                new Pose(108.017, 113.475)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(36))
                .build();

        path2 = follower().pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(108.017, 113.475),
                                new Pose(69.936, 82.232),
                                new Pose(95.745, 83.529)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(0))
                .build();

        path3 = follower().pathBuilder().addPath(
                        new BezierLine(
                                new Pose(95.745, 83.529),
                                new Pose(130.000, 83.529)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        path4 = follower().pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(130.000, 83.529),
                                new Pose(102.127, 87.172),
                                new Pose(108.017, 113.475)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(36))
                .build();
    }
    private Command autonomousRoutine() {
        return new SequentialGroup(
                //move backwards to shooting position
                new ParallelGroup(
                        new FollowPath(path1, true, 0.5),
                        Flywheel.INSTANCE.spinUp().withDeadline(new Delay(1))
                ),
                new Delay(0.75),

                //shoot three times
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor,
                        new Delay(0.5),
                        spinIt,
                        new Delay(1.5)
                ),
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor,
                        new Delay(0.5),
                        spinIt,
                        new Delay(1.5)
                ),
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor
                ),

                //move to intake first three balls
                new FollowPath(path2, true, 0.5),

                //pick up three balls
                Intake.INSTANCE.swallow().withDeadline(new Delay(0.2)),
                new FollowPath(path3, true, 0.5).withDeadline(new Delay(2)),
                new Delay(1),
                spinIt,
                new Delay(2),
                spinIt,
                new Delay(1),
                Intake.INSTANCE.off().withDeadline(new Delay(0.2)),

                //shooting position again
                new FollowPath(path4, true, 0.5),

                //shoot three times
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor,
                        new Delay(0.5),
                        spinIt,
                        new Delay(1.5)
                ),
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor,
                        new Delay(0.5),
                        spinIt,
                        new Delay(1.5)
                ),
                new SequentialGroup(
                        Flicker.INSTANCE.smackThat,
                        new Delay(0.5),
                        Flicker.INSTANCE.allOnTheFloor
                ),
                Flywheel.INSTANCE.stop()
        );
    }
    @Override
    public void onStartButtonPressed() {
        follower().setStartingPose(new Pose(122.579, 124.055, Math.toRadians(36)));
        autonomousRoutine().schedule();
    }
}