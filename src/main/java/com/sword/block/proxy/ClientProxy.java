package com.sword.block.proxy;

import com.sword.block.SwordBlock;
import com.sword.block.util.EnumHelper;
import net.minecraft.client.renderer.entity.model.BipedModel;

/**
 * @author ji_GGO
 * @date 2021/08/13
 */
public class ClientProxy implements IProxy {

    private static final Class<?>[][] types = {{BipedModel.ArmPose.class, boolean.class}};

    public static final BipedModel.ArmPose SWORD = EnumHelper.addEnum(types, BipedModel.ArmPose.class, "SWORD:BLOCK", true);;

    @Override
    public void starting() {
        SwordBlock.LOGGER.info(SWORD);
    }

}