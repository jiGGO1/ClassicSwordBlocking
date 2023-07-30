package com.sword.block.mixin;

import com.sword.block.client.SwordBlockClient;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class MixinHumanoidModel<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {

    @Final
    @Shadow
    public ModelPart rightArm;

    @Final
    @Shadow
    public ModelPart leftArm;

    @Shadow
    public HumanoidModel.ArmPose rightArmPose;

    @Shadow
    public HumanoidModel.ArmPose leftArmPose;

    @Inject(method = "poseRightArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderRight(T entity, CallbackInfo info){
        if (this.rightArmPose == SwordBlockClient.SWORD) {
            SwordBlockClient.renderArm(entity.getMainArm(), this.rightArm);
            info.cancel();
        }
    }

    @Inject(method = "poseLeftArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderLeft(T entity, CallbackInfo info){
        if (this.leftArmPose == SwordBlockClient.SWORD) {
            SwordBlockClient.renderArm(entity.getMainArm(), this.leftArm);
            info.cancel();
        }
    }

    @Inject(method = "setupAttackAnimation", at = @At(value = "HEAD"), cancellable = true)
    private void renderCancel(T entity, float ageInTicks, CallbackInfo info){
        if (entity.getMainArm() == HumanoidArm.RIGHT && this.rightArmPose == SwordBlockClient.SWORD) {
            info.cancel();
        } else if (entity.getMainArm() == HumanoidArm.LEFT && this.leftArmPose == SwordBlockClient.SWORD) {
            info.cancel();
        }
    }

}