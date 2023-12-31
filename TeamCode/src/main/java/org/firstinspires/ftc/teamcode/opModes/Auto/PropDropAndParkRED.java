package org.firstinspires.ftc.teamcode.opModes.Auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commandGroups.DriveByBlob;
import org.firstinspires.ftc.teamcode.commands.depositor.DepositorClose;
import org.firstinspires.ftc.teamcode.commands.depositor.DepositorOpen;
import org.firstinspires.ftc.teamcode.commands.drive.DriveToPose;
import org.firstinspires.ftc.teamcode.commands.gripper.GripperGrabberToggle;
import org.firstinspires.ftc.teamcode.commands.lift.MoveLiftPreset;
import org.firstinspires.ftc.teamcode.commands.util.Wait;
import org.firstinspires.ftc.teamcode.opModes.base.AutoOpMode;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

@Autonomous(name = "PropDropAndParkRED", group = "Autonomous", preselectTeleOp = "Manual Control")
public class PropDropAndParkRED extends AutoOpMode {
    private VisionSubsystem v;

    @Override
    public void setup() {
        v = new VisionSubsystem(hardwareMap, telemetrySubsystem);

        liftSubsystem.resetEncoder();
        schedule(new SequentialCommandGroup(
                new GripperGrabberToggle(gripperSubsystem),
                new MoveLiftPreset(liftSubsystem,LiftSubsystem.LIFT_POSITIONS.TILT_SAFE, gripperSubsystem),
                new DriveByBlob(d,o,t, v, depositor, lift, gripper),
                new DepositorOpen(depositor),
                new Wait(1.0),
                new DepositorClose(depositor),
                new DriveToPose(d,o,t,new Pose2d(-13.0,24.0, Rotation2d.fromDegrees(0)))

//
        ));
//
    }
}
