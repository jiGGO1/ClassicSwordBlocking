package com.sword.block;

import net.minecraft.world.item.UseAnim;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwordBlock {

    public static final String MODID = "sword_block";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final UseAnim SWORD = SwordBlockPlatform.getUseAnim();

}