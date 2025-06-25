package com.sword.block.forge;

import com.sword.block.SwordBlock;
import com.sword.block.SwordProperties;
import com.sword.block.client.SwordBlockClient;
import com.sword.block.proxy.IProxy;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.GatherComponentsEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(SwordBlock.MODID)
public class SwordBlockForge extends SwordBlock {

    public static IProxy proxy = new IProxy() {};

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final RegistryObject<DataComponentType<Unit>> DATA_COMPONENT_TYPE = DATA_COMPONENT_TYPES.register(MODID, () -> UNIT.apply(DataComponentType.builder()).build());

    public SwordBlockForge(FMLJavaModLoadingContext context) {
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> proxy = SwordBlockClient.INSTANCE);
        proxy.starting();
        //IEventBus modEventBus = context.getModEventBus();
        //DATA_COMPONENT_TYPES.register(modEventBus);
        //MinecraftForge.EVENT_BUS.register(this);
        var modEventBus = SwordBlockForgeHelper.getModEventBus(context);
        SwordBlockForgeHelper.register(DATA_COMPONENT_TYPES, modEventBus);
        SwordBlockForgeHelper.register(this);
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    @net.minecraftforge.eventbus.api.listener.SubscribeEvent
    public void modifyComponents(final GatherComponentsEvent.Item event) {
        if (event.getOwner() instanceof SwordProperties sword && sword.getUseSwordBlocking()) {
            event.register(SWORD_BLOCK.get(), Unit.INSTANCE);
        }
    }

}