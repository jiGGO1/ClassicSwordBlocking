package com.sword.block.forge;

import com.sword.block.SwordBlockPlatform;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.forge.util.EnumHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SwordBlockPlatformImpl {

    public static UseAnim getUseAnim() {
        return SwordBlockPlatform.isCompatible() ? EnumHelper.addAction("SWORD:BLOCK") : UseAnim.valueOf("SWORD:BLOCK");
    }

    @OnlyIn(Dist.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return SwordBlockPlatform.isCompatible() ? EnumHelper.addEnum(SwordBlockClient.TYPES, HumanoidModel.ArmPose.class, "SWORD:BLOCK", true) : HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

    public static boolean isCompatible() {
        return System.getProperty("java.specification.version").equals("1.8");
    }

}