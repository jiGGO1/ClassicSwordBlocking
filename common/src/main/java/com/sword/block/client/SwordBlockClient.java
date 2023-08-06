package com.sword.block.client;

import com.sword.block.SwordBlock;
import com.sword.block.SwordBlockPlatform;
import com.sword.block.proxy.IProxy;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;

public class SwordBlockClient implements IProxy {

    public static final Class<?>[][] TYPES = {{HumanoidModel.ArmPose.class, boolean.class}};
    public static final HumanoidModel.ArmPose SWORD = SwordBlockPlatform.getSwordBlocking();
    public static final SwordBlockClient INSTANCE = new SwordBlockClient();

    @Override
    public void starting() {
        SwordBlock.LOGGER.info(SWORD);
    }

    public static void init() {
        SwordBlockClient.INSTANCE.starting();
    }

    public static void renderArm(HumanoidArm type, ModelPart arm) {
        arm.xRot = arm.xRot * 0.5F - 0.9424778F;
        arm.yRot = (type == HumanoidArm.RIGHT ? 1 : -1) * -0.5235988F;
    }

}