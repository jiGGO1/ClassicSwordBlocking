package com.sword.block.neoforge;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class SwordBlockPlatformImpl {

    @OnlyIn(Dist.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

    public static Supplier<DataComponentType<Unit>> getDataComponentType() {
        return () -> SwordBlockNeoForge.DATA_COMPONENT_TYPE.get();
    }

}