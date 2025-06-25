package com.sword.block.forge;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class SwordBlockPlatformImpl {

    @OnlyIn(Dist.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.create("SWORD:BLOCK", true,
                (HumanoidModel<?> model, HumanoidRenderState entity, HumanoidArm arm) -> {});
    }

    public static Supplier<DataComponentType<Unit>> getDataComponentType() {
        return () -> SwordBlockForge.DATA_COMPONENT_TYPE.get();
    }

}