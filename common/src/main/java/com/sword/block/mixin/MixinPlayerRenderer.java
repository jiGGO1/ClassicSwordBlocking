package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class MixinPlayerRenderer extends LivingEntityRenderer<AbstractClientPlayer, PlayerRenderState, PlayerModel> {

    public MixinPlayerRenderer(EntityRendererProvider.Context context, PlayerModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "getArmPose(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;", shift = At.Shift.AFTER),
            cancellable = true)
    private static void renderSwordMain(Player player, ItemStack stack, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> info){
        ItemUseAnimation animation = stack.getUseAnimation();
        if (animation == SwordBlock.SWORD && animation == stack.getUseAnimation()) {
            info.setReturnValue(SwordBlockClient.SWORD);
        }
    }

}
