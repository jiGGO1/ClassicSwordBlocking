package com.sword.block.fabric;

import net.minecraft.client.model.HumanoidModel;

public class SwordBlockPlatformImpl {

    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

}