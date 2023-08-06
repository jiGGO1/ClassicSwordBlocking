package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemSword.class)
public class MixinItemSword extends Item {

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return SwordBlock.SWORD;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack shield = player.getHeldItem(EnumHand.OFF_HAND);
        boolean flag = shield.getItem().isShield(shield, player);
        if (hand == EnumHand.MAIN_HAND && !flag) {
            player.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        if (hand == EnumHand.OFF_HAND || flag) {
            return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }

}