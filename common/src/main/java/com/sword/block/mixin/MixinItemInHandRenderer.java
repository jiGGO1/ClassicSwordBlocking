package com.sword.block.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sword.block.SwordBlock;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinItemInHandRenderer {

    @Inject(method = "renderArmWithItem",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;"))
    private void renderSwordBlock(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack matrices, MultiBufferSource buffer, int combinedLight, CallbackInfo info) {
        if (stack.getUseAnimation() == SwordBlock.SWORD) {
            boolean flag = (hand == InteractionHand.MAIN_HAND);
            HumanoidArm arm = flag ? player.getMainArm() : player.getMainArm().getOpposite();
            this.applyItemArmTransform(matrices, arm, equippedProgress);
            // https://github.com/Fuzss/swordblockingmechanics/blob/3280e9cb604c58b8538efb3466cf462fd89d2fc3/src/main/java/com/fuzs/swordblockingmechanics/client/element/SwordBlockingExtension.java#L143
            int horizontal = arm == HumanoidArm.RIGHT ? 1 : -1;
            matrices.translate(horizontal * -0.14142136F, 0.08F, 0.14142136F);
            matrices.mulPose(Axis.XP.rotationDegrees(-102.25F));
            matrices.mulPose(Axis.YP.rotationDegrees(horizontal * 13.365F));
            matrices.mulPose(Axis.ZP.rotationDegrees(horizontal * 78.05F));
            /*
            matrices.translate(-0.08F * horizontal, 0.1F, 0.03F * horizontal);
            matrices.mulPose(Axis.ZP.rotationDegrees(80 * horizontal));
            matrices.mulPose(Axis.YP.rotationDegrees(91 * horizontal));
            */
        }
    }

    @Shadow
    abstract void applyItemArmTransform(PoseStack matrices, HumanoidArm arm, float equippedProgress);

}