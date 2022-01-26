package com.example.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestRedWarehouse {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(55, 55, Math.toRadians(300.704976), Math.toRadians(300.704976), 14.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .splineTo(new Vector2d(22,23.5), Math.toRadians(0))
                                .setReversed(true)
                                .splineTo(new Vector2d(-8,-35), Math.toRadians(267))

                                .setReversed(true)
                                .splineTo(new Vector2d(18.5,20), Math.toRadians(10))
                                .splineTo(new Vector2d(-8,-36), Math.toRadians(270))

                                .setReversed(true)
                                .splineTo(new Vector2d(18.5,20), Math.toRadians(0))
                                .splineTo(new Vector2d(-8,-37), Math.toRadians(270))

                                .setReversed(true)
                                .splineTo(new Vector2d(18.5,20), Math.toRadians(0))
                                .splineTo(new Vector2d(-9,-38), Math.toRadians(270))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}