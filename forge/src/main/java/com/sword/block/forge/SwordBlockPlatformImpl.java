package com.sword.block.forge;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class SwordBlockPlatformImpl {

    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.create("SWORD:BLOCK", true,
                (HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) -> {});
    }

}