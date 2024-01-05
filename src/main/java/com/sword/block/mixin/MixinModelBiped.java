package com.sword.block.mixin;

import com.sword.block.client.SwordBlockClient;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(Side.CLIENT)
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped extends ModelBase {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    @Shadow
    public ModelBiped.ArmPose leftArmPose;

    @Shadow
    public ModelBiped.ArmPose rightArmPose;

    @Inject(at = @At("TAIL"), method = "setRotationAngles(FFFFFFLnet/minecraft/entity/Entity;)V")
    private void renderSwordBlock(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo info) {
        EnumHandSide arm = entity instanceof EntityLivingBase ? ((EntityLivingBase)entity).getPrimaryHand() : EnumHandSide.RIGHT;
        if (this.rightArmPose == SwordBlockClient.SWORD && arm == EnumHandSide.RIGHT && this.leftArmPose == ModelBiped.ArmPose.EMPTY) {
            SwordBlockClient.renderArm(arm, this.bipedRightArm);
        } else if (this.leftArmPose == SwordBlockClient.SWORD && arm == EnumHandSide.LEFT && this.rightArmPose == ModelBiped.ArmPose.EMPTY) {
            SwordBlockClient.renderArm(arm, this.bipedLeftArm);
        }
    }

}