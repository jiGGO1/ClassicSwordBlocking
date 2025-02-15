package com.sword.block.mixin;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.*;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(HumanoidModel.ArmPose.class)
@Unique
public class MixinHumanoidModelArmPose {

    @Shadow
    @Final
    @Mutable
    private static HumanoidModel.ArmPose[] $VALUES;

    private static final HumanoidModel.ArmPose SWORD = create("SWORD:BLOCK", true);

    public MixinHumanoidModelArmPose(String name, int id, boolean twoHanded) {

    }

    @Unique
    private static HumanoidModel.ArmPose create(String poseName, boolean twoHanded) {
        ArrayList<HumanoidModel.ArmPose> poses = new ArrayList<>(Arrays.asList(MixinHumanoidModelArmPose.$VALUES));
        HumanoidModel.ArmPose pose = (HumanoidModel.ArmPose)(Object)new MixinHumanoidModelArmPose(poseName, poses.get(poses.size() - 1).ordinal() + 1, twoHanded);
        poses.add(pose);
        MixinHumanoidModelArmPose.$VALUES = poses.toArray(new HumanoidModel.ArmPose[0]);
        return pose;
    }

}