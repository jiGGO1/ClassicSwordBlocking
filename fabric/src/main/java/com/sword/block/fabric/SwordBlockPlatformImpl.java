package com.sword.block.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;

import java.util.function.Supplier;

public class SwordBlockPlatformImpl {

    @Environment(EnvType.CLIENT)
    public static HumanoidModel.ArmPose getSwordBlocking() {
        return HumanoidModel.ArmPose.valueOf("SWORD:BLOCK");
    }

    public static Supplier<DataComponentType<Unit>> getDataComponentType() {
        return () -> SwordBlockFabric.DATA_COMPONENT_TYPE;
    }

}