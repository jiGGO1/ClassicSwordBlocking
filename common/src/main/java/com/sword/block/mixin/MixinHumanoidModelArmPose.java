package com.sword.block.mixin;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(HumanoidModel.ArmPose.class)
@Unique
public class MixinHumanoidModelArmPose {

    @Shadow
    @Final
    @Mutable
    private static HumanoidModel.ArmPose[] $VALUES;

    private static final HumanoidModel.ArmPose SWORD = armPose$addArmPose("SWORD:BLOCK", true);

    @Invoker("<init>")
    public static HumanoidModel.ArmPose armPose$invokeInit(String name, int id, boolean twoHanded) {
        throw new AssertionError();
    }

    private static HumanoidModel.ArmPose armPose$addArmPose(String poseName, boolean twoHanded) {
        ArrayList<HumanoidModel.ArmPose> poses = new ArrayList<>(Arrays.asList(MixinHumanoidModelArmPose.$VALUES));
        HumanoidModel.ArmPose pose = armPose$invokeInit(poseName, poses.get(poses.size() - 1).ordinal() + 1, twoHanded);
        poses.add(pose);
        MixinHumanoidModelArmPose.$VALUES = poses.toArray(new HumanoidModel.ArmPose[0]);
        return pose;
    }

}