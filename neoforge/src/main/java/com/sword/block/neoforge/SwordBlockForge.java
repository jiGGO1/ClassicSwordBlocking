package com.sword.block.neoforge;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.proxy.IProxy;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;

@Mod(SwordBlock.MODID)
public class SwordBlockForge {

    public static IProxy proxy = new IProxy() {};

    public SwordBlockForge() {
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = SwordBlockClient.INSTANCE);
        proxy.starting();
    }

}