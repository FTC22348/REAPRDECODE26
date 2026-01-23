package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class Flicker implements Subsystem {
    public static final Flicker INSTANCE = new Flicker();
    private Flicker() { }
    private ServoEx akon = new ServoEx("akon");
    public Command smackThat = new SetPosition(akon, 0.9);
    public Command allOnTheFloor = new SetPosition(akon, 0);
}