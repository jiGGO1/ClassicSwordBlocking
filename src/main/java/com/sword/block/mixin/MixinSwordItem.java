package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author ji_GGO
 * @date 2021/08/13
 */
@Mixin(SwordItem.class)
public abstract class MixinSwordItem extends TieredItem {

    public MixinSwordItem(IItemTier iItemTier, Properties properties) {
        super(iItemTier, properties);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return SwordBlock.SWORD;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack shield = player.getHeldItem(Hand.OFF_HAND);
        boolean flag = shield.getItem().isShield(shield, player);
        if (hand == Hand.MAIN_HAND && !flag) {
            player.setActiveHand(hand);
            return ActionResult.resultSuccess(player.getHeldItem(hand));
        }
        if (hand == Hand.OFF_HAND || flag) {
            return ActionResult.resultFail(player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }

}