package com.sword.block.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;

public class SwordBlockPlatformImpl {

    @Environment(EnvType.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

}