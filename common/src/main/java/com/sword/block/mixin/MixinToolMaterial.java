package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import com.sword.block.SwordProperties;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterial.class)
public class MixinToolMaterial {

    @Inject(method = "applySwordProperties", at = @At(value = "TAIL"))
    private void onApplySwordProperties(Item.Properties properties, float attackDamage, float attackSpeed, CallbackInfoReturnable<Item.Properties> info) {
        if (info.getReturnValue() instanceof SwordProperties props) {
            props.setUseSwordBlocking(true);
        } else {
            info.getReturnValue().component(SwordBlock.SWORD_BLOCK.get(), Unit.INSTANCE);
        }
    }

}