package com.sword.block.mixin;

import com.sword.block.proxy.ClientProxy;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author ji_GGO
 * @date 2021/09/07
 */
@Mixin(BipedModel.class)
public abstract class MixinBipedModel<T extends LivingEntity> extends AgeableModel<T> {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public BipedModel.ArmPose rightArmPose;

    @Inject(method = "func_241654_b_", at = @At(value = "HEAD"), cancellable = true)
    private void renderRight(T model, CallbackInfo call){
        if (this.rightArmPose == ClientProxy.SWORD) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424778F;
            this.bipedRightArm.rotateAngleY = -0.5235988F;
            call.cancel();
        }
    }

    @Inject(method = "func_230486_a_", at = @At(value = "HEAD"), cancellable = true)
    private void renderCancel(T model, float p_230486_2_, CallbackInfo call){
        if (this.rightArmPose == ClientProxy.SWORD) {
            call.cancel();
        }
    }

}