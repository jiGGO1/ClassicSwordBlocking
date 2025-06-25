package com.sword.block.neoforge;

import com.sword.block.SwordBlock;
import com.sword.block.SwordProperties;
import com.sword.block.proxy.IProxy;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(SwordBlock.MODID)
public class SwordBlockNeoForge extends SwordBlock {

    public static IProxy proxy = new IProxy() {};

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> DATA_COMPONENT_TYPE = DATA_COMPONENT_TYPES.register(MODID, () -> UNIT.apply(DataComponentType.builder()).build());

    public SwordBlockNeoForge(IEventBus modEventBus) {
        DATA_COMPONENT_TYPES.register(modEventBus);
        modEventBus.addListener(this::modifyComponents);
    }

    public void modifyComponents(final ModifyDefaultComponentsEvent event) {
        event.modifyMatching(item -> item instanceof SwordProperties sword && sword.getUseSwordBlocking(), builder -> {
            builder.set(SwordBlock.SWORD_BLOCK.get(), Unit.INSTANCE);
        });
    }

}