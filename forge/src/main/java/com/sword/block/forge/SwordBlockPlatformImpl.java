package com.sword.block.forge;

import net.minecraft.client.model.HumanoidModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SwordBlockPlatformImpl {

    @OnlyIn(Dist.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

}