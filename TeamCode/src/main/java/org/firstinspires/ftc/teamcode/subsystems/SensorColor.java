package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Color;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.commands.ColorIntakeCommand;

import java.util.logging.Level;

public class SensorColor implements HardwareDevice {

    private ColorSensor colorSensor;
    private Telemetry telemetry;

    public SensorColor(ColorSensor colorSensor) {
        this.colorSensor = colorSensor;
    }

    public SensorColor(HardwareMap hardwareMap, Telemetry tl, String id) {
        this(hardwareMap.get(ColorSensor.class, id));
        this.telemetry = tl;
    }

    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Color Alpha:",colorSensor.alpha());
        Util.logger(this, telemetry, Level.INFO, "Color Red:",colorSensor.red());
        Util.logger(this, telemetry, Level.INFO, "Color Green:",colorSensor.green());
        Util.logger(this, telemetry, Level.INFO, "Color Blue:",colorSensor.blue());
    }

    public int[] HSVtoARGB(int alpha, float[] hsv) {
        int color = Color.HSVToColor(alpha, hsv);
        return new int[]{Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color)};
    }

    public float[] RGBtoHSV(int red, int green, int blue, float[] hsv) {
        Color.RGBToHSV(red, green, blue, hsv);
        return hsv;
    }

    public int[] getARGB() {
        return new int[]{alpha(), red(), green(), blue()};
    }

    public int alpha() {return colorSensor.alpha();}
    public int red() {return colorSensor.red();}
    public int green() {return colorSensor.green();}
    public int blue() {return colorSensor.blue();}

    /*
    public void checkColor(){
        if((colorSensor.red() > 200) && (colorSensor.green() > 200)){
            new ColorIntakeCommand();
        }
    }*/

    @Override
    public void disable() {colorSensor.close();}

    @Override
    public String getDeviceType() {return "Color Sensor";}

}