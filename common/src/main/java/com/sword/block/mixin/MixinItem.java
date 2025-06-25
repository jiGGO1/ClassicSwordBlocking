package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import com.sword.block.SwordProperties;
import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItem implements SwordProperties {

    @Unique
    private boolean useSwordBlocking = false;

    @PlatformOnly({"forge","neoforge"})
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void onInit(Item.Properties properties, CallbackInfo info) {
        if (properties instanceof SwordProperties props) {
            this.setUseSwordBlocking(props.getUseSwordBlocking());
        }
    }

    @Inject(method = "getUseDuration", at = @At(value = "HEAD"), cancellable = true)
    private void onGetUseDuration(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Integer> info) {
       if (stack.has(SwordBlock.SWORD_BLOCK.get())) {
           info.setReturnValue(72000);
       }
    }

    @Inject(method = "getUseAnimation", at = @At(value = "HEAD"), cancellable = true)
    private void onGetUseAnimation(ItemStack stack, CallbackInfoReturnable<ItemUseAnimation> info) {
        if (stack.has(SwordBlock.SWORD_BLOCK.get())) {
            info.setReturnValue(SwordBlock.SWORD);
        }
    }

    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    private void onUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> info) {
        if (player.getItemInHand(hand).has(SwordBlock.SWORD_BLOCK.get())) {
            var result = this.onuUseSword(level, player, hand);
            if (result != null) info.setReturnValue(result);
        }
    }

    public InteractionResult onuUseSword(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(InteractionHand.OFF_HAND);
        boolean flag = stack.getItem().getUseAnimation(stack) != ItemUseAnimation.NONE;
        if (hand == InteractionHand.MAIN_HAND && !flag) {
            player.startUsingItem(hand);
            return InteractionResult.SUCCESS;
        }
        if (hand == InteractionHand.OFF_HAND || flag) {
            return InteractionResult.FAIL;
        }
        return null;
    }

    @Override
    public boolean getUseSwordBlocking() {
        return this.useSwordBlocking;
    }

    @Override
    public void setUseSwordBlocking(boolean flag) {
        this.useSwordBlocking = flag;
    }

}