package com.sword.block;

import com.sword.block.proxy.ClientProxy;
import com.sword.block.proxy.IProxy;
import com.sword.block.util.EnumHelper;
import net.minecraft.item.UseAction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SwordBlock.MODID)
public class SwordBlock {

    public static final String MODID = "sword_block";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final UseAction SWORD = EnumHelper.addAction("SWORD:BLOCK");

    public static IProxy proxy = new IProxy() {};

    public SwordBlock() {
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = new ClientProxy());
        proxy.starting();
    }

}