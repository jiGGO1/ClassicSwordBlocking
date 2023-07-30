package com.sword.block.fabric.client;

import com.sword.block.client.SwordBlockClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SwordBlockFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SwordBlockClient.init();
    }

}