package com.sword.block.client;

import com.sword.block.proxy.ClientProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SwordBlockClient {

    public static final ModelBiped.ArmPose SWORD = SwordBlockClient.getSwordBlocking();

    private static ModelBiped.ArmPose getSwordBlocking() {
        return ClientProxy.SWORD;
    }

    public static void renderArm(EnumHandSide type, ModelRenderer arm) {
        arm.rotateAngleX = arm.rotateAngleX * 0.5F - 0.9424778F;
        arm.rotateAngleY = (type == EnumHandSide.RIGHT ? 1 : -1) * -0.5235988F;
    }

}