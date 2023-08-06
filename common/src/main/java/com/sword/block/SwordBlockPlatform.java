package com.sword.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.UseAnim;

public class SwordBlockPlatform {

    @ExpectPlatform
    public static UseAnim getUseAnim() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static HumanoidModel.ArmPose getSwordBlocking() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isCompatible() {
        throw new AssertionError();
    }

}