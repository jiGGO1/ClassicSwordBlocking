package com.sword.block.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sword.block.SwordBlock;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author ji_GGO
 * @date 2021/09/07
 */
@Mixin(FirstPersonRenderer.class)
public abstract class MixinFirstPersonRenderer {

    @Inject(method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V",
        at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/UseAction;"))
    private void renderSwordBlock(AbstractClientPlayerEntity player, float partialTicks, float pitch, Hand hand, float swingProgress, ItemStack stack, float equippedProgress, MatrixStack matrixStack, IRenderTypeBuffer buffer, int p_228405_10_, CallbackInfo call){
        if (stack.getUseAction() == SwordBlock.SWORD) {
            boolean flag = (hand == Hand.MAIN_HAND);
            HandSide enumhandside = flag ? player.getPrimaryHand() : player.getPrimaryHand().opposite();
            this.transformSideFirstPerson(matrixStack, enumhandside, equippedProgress);
            matrixStack.translate(-0.08F,0.1F,0.03F);
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(80));
            matrixStack.rotate(Vector3f.YP.rotationDegrees(90));
            //matrixStack.rotate(Vector3f.XP.rotationDegrees(1));
        }
    }

    @Shadow
    abstract void transformSideFirstPerson(MatrixStack p_228406_1_, HandSide p_228406_2_, float p_228406_3_);

}