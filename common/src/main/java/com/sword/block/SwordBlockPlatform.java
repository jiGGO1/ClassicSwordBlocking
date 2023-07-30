package com.sword.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.HumanoidModel;

public class SwordBlockPlatform {

    @ExpectPlatform
    public static HumanoidModel.ArmPose getSwordBlocking() {
        throw new AssertionError();
    }

}