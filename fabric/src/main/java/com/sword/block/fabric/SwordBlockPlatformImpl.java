package com.sword.block.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.UseAnim;

public class SwordBlockPlatformImpl {

    public static UseAnim getUseAnim() {
        return UseAnim.valueOf("SWORD:BLOCK");
    }

    @Environment(EnvType.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

    public static boolean isCompatible() {
        return false;
    }

}