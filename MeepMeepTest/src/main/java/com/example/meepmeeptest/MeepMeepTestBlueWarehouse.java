package com.example.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestBlueWarehouse {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(57, 57, Math.toRadians(300.704976), Math.toRadians(300.704976), 14.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .splineTo(new Vector2d(23.5,   -20.5), Math.toRadians(0))

                                .setReversed(true)
                                .splineTo(new Vector2d(-5,39), Math.toRadians(90))
                                .forward(5)

                                .setReversed(true)
                                .splineTo(new Vector2d(21,   -18), Math.toRadians(0))

                                .splineTo(new Vector2d(-5,39), Math.toRadians(90))
                                .forward(5)

                                .setReversed(true)
                                .splineTo(new Vector2d(21,   -18), Math.toRadians(0))

                                .splineTo(new Vector2d(-5,39), Math.toRadians(90))
                                .forward(5)

                                .setReversed(true)
                                .splineTo(new Vector2d(21,   -18), Math.toRadians(0))

                                .splineTo(new Vector2d(-5,39), Math.toRadians(90))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}