package com.sword.block.mixin;

import com.sword.block.SwordProperties;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.Properties.class)
public class MixinItemProperties implements SwordProperties {

    @Unique
    private boolean useSwordBlocking = false;

    @Override
    public boolean getUseSwordBlocking() {
        return this.useSwordBlocking;
    }

    @Override
    public void setUseSwordBlocking(boolean flag) {
        this.useSwordBlocking = flag;
    }

}