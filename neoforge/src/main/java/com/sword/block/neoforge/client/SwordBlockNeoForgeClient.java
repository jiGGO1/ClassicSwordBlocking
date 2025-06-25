package com.sword.block.neoforge.client;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.neoforge.SwordBlockNeoForge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = SwordBlock.MODID, dist = Dist.CLIENT)
public class SwordBlockNeoForgeClient {

    public SwordBlockNeoForgeClient() {
        SwordBlockNeoForge.proxy = SwordBlockClient.INSTANCE;
        SwordBlockNeoForge.proxy.starting();
    }

}