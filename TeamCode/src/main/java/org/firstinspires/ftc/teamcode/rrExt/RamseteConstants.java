package org.firstinspires.ftc.teamcode.rrExt;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RamseteConstants {
    //horizontal error adjustment
    public static double b = 15;
    public static double zeta = 0.001;

    //minimize overshoot
    public static double kLinear = 0;
    public static double kHeading = 3;
}