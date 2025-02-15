package com.sword.block.neoforge.client;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.neoforge.SwordBlockForge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = SwordBlock.MODID, dist = Dist.CLIENT)
public class SwordBlockForgeClient {

    public SwordBlockForgeClient() {
        SwordBlockForge.proxy = SwordBlockClient.INSTANCE;
        SwordBlockForge.proxy.starting();
    }

}