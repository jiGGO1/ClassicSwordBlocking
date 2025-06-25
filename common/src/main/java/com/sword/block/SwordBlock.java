package com.sword.block;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemUseAnimation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SwordBlock {

    public static final String MODID = "sword_block";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final ItemUseAnimation SWORD = ItemUseAnimation.valueOf("SWORD:BLOCK");

    public static final UnaryOperator<DataComponentType.Builder<Unit>> UNIT = (builder) ->
            builder.persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC);

    public static final Supplier<DataComponentType<Unit>> SWORD_BLOCK = SwordBlockPlatform.getDataComponentType();

}