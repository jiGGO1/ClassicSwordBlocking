package com.sword.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;

import java.util.function.Supplier;

public class SwordBlockPlatform {

    @ExpectPlatform
    public static HumanoidModel.ArmPose getSwordBlocking() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<DataComponentType<Unit>> getDataComponentType() {
        throw new AssertionError();
    }

}