package com.sword.block.mixin;

import com.sword.block.client.SwordBlockClient;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class MixinHumanoidModel<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel, HeadedModel {

    @Final
    @Shadow
    public ModelPart rightArm;

    @Final
    @Shadow
    public ModelPart leftArm;

    protected MixinHumanoidModel(ModelPart root) {
        super(root);
    }

    @Inject(method = "poseRightArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderRight(T entity, HumanoidModel.ArmPose pose, CallbackInfo info){
        if (pose == SwordBlockClient.SWORD) {
            SwordBlockClient.renderArm(entity.mainArm, this.rightArm);
            info.cancel();
        }
    }

    @Inject(method = "poseLeftArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderLeft(T entity, HumanoidModel.ArmPose pose, CallbackInfo info){
        if (pose == SwordBlockClient.SWORD) {
            SwordBlockClient.renderArm(entity.mainArm, this.leftArm);
            info.cancel();
        }
    }

    @Inject(method = "setupAttackAnimation", at = @At(value = "HEAD"), cancellable = true)
    private void renderCancel(T entity, float ageInTicks, CallbackInfo info){
        if (entity.mainArm == HumanoidArm.RIGHT && entity.rightArmPose == SwordBlockClient.SWORD) {
            info.cancel();
        } else if (entity.mainArm == HumanoidArm.LEFT && entity.leftArmPose == SwordBlockClient.SWORD) {
            info.cancel();
        }
    }

}