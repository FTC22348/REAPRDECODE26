package org.firstinspires.ftc.teamcode.OpModes;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class GoalPaths {
    public PathChain path1, path2, path3, path4, path5;
    public GoalPaths() {
        path1 = follower().pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.421, 124.055),
                                new Pose(35.983, 113.475)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(144))
                .build();

        path2 = follower().pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(35.983, 113.475),
                                new Pose(74.064, 82.232),
                                new Pose(48.255, 83.529)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))
                .build();

        path3 = follower().pathBuilder().addPath(
                        new BezierLine(
                                new Pose(48.255, 83.529),
                                new Pose(14.000, 83.529)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        path4 = follower().pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(14.000, 83.529),
                                new Pose(41.873, 87.127),
                                new Pose(35.983, 113.475)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))
                .build();

        path5 = follower().pathBuilder().addPath(
                        new BezierLine(
                                new Pose(35.983, 113.475),
                                new Pose(48.255, 60.529)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))
                .build();
    }
}
