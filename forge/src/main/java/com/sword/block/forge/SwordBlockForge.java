package com.sword.block.forge;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.proxy.IProxy;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(SwordBlock.MODID)
public class SwordBlockForge {

    public static IProxy proxy = new IProxy() {};

    public SwordBlockForge() {
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = SwordBlockClient.INSTANCE);
        proxy.starting();
    }

}