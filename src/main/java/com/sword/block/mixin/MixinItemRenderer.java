package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(Side.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItemUseAction()Lnet/minecraft/item/EnumAction;"), method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V")
    private void renderSwordBlock(AbstractClientPlayer player, float partialTicks, float pitch, EnumHand hand, float swingProgress, ItemStack stack, float equippedProgress, CallbackInfo info) {
        if (stack.getItemUseAction() == SwordBlock.SWORD) {
            boolean flag = (hand == EnumHand.MAIN_HAND);
            EnumHandSide arm = flag ? player.getPrimaryHand() : player.getPrimaryHand().opposite();
            this.transformSideFirstPerson(arm, equippedProgress);
            int horizontal = arm == EnumHandSide.RIGHT ? 1 : -1;
            GlStateManager.translate(horizontal * -0.14142136F, 0.08F, 0.14142136F);
            GlStateManager.rotate(-102.25F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(horizontal * 13.365F, 0.0F, 1.0F,0.0F);
            GlStateManager.rotate(horizontal * 78.05F, 0.0F, 0.0F, 1.0F);
            /*
            GlStateManager.translate(-0.1F, 0.02F, 0.08F);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
            */
        }
    }

    @Shadow
    abstract void transformSideFirstPerson(EnumHandSide hand, float equippedProgress);

}