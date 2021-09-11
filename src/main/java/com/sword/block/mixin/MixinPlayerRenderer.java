package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import com.sword.block.proxy.ClientProxy;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author ji_GGO
 * @date 2021/08/13
 */
@OnlyIn(Dist.CLIENT)
@Mixin(PlayerRenderer.class)
public abstract class MixinPlayerRenderer extends LivingRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public MixinPlayerRenderer(EntityRendererManager manager, PlayerModel<AbstractClientPlayerEntity> model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Inject(method = "func_241741_a_(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/renderer/entity/model/BipedModel$ArmPose;",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/UseAction;"),
            locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void renderSwordMain(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedModel.ArmPose> call,
                                        ItemStack itemstack, UseAction useaction){
        if (useaction == SwordBlock.SWORD && useaction == itemstack.getUseAction()) {
            call.setReturnValue(ClientProxy.SWORD);
        }
    }

}