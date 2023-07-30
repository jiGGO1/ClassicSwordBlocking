package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem extends TieredItem {

    public MixinSwordItem(Tier tier, Properties props) {
        super(tier, props);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return SwordBlock.SWORD;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack shield = player.getItemInHand(InteractionHand.OFF_HAND);
        boolean flag = shield.getItem() instanceof ShieldItem;
        if (hand == InteractionHand.MAIN_HAND && !flag) {
            player.startUsingItem(hand);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        if (hand == InteractionHand.OFF_HAND || flag) {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
    }

}