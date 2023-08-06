package com.sword.block.forge;

import com.sword.block.SwordBlock;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SwordBlockPlatformImpl {

    @OnlyIn(Dist.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        if (forName("net.minecraftforge.client.IArmPoseTransformer")) {
            return HumanoidModel.ArmPose.create("SWORD:BLOCK", true,
                    (HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) -> {});
        } else {
            return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
        }
    }

    private static boolean forName(String name) {
        try {
            return null != Class.forName(name);
        } catch (ClassNotFoundException ex) {
            SwordBlock.LOGGER.info("Not Found net.minecraftforge.client.IArmPoseTransformer");
            return false;
        }
    }

}