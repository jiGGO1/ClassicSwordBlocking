package com.sword.block.fabric;

import com.sword.block.SwordBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

public class SwordBlockFabric extends SwordBlock implements ModInitializer {

    public static final DataComponentType<Unit> DATA_COMPONENT_TYPE = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, MODID),
            UNIT.apply(DataComponentType.builder()).build());

    @Override
    public void onInitialize() {

    }

}