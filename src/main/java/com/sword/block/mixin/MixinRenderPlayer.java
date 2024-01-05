package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import com.sword.block.client.SwordBlockClient;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@SideOnly(Side.CLIENT)
@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer extends RenderLivingBase<AbstractClientPlayer> {

    @Unique
    private ModelBiped.ArmPose sword;

    public MixinRenderPlayer(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Inject(at = {@At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItemUseAction()Lnet/minecraft/item/EnumAction;")}, method = "setModelVisibilities(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", locals = LocalCapture.CAPTURE_FAILSOFT)
    private void renderSwordMain(AbstractClientPlayer player, CallbackInfo info, ModelPlayer modelplayer, ItemStack itemstack, ItemStack itemstack1, ModelBiped.ArmPose modelbiped$armpose, ModelBiped.ArmPose modelbiped$armpose1, EnumAction enumaction) {
        if (enumaction == SwordBlock.SWORD && enumaction == itemstack.getItemUseAction()) {
            this.sword = SwordBlockClient.SWORD;
        }
    }

    @Inject(at = @At("TAIL"), method = "setModelVisibilities(Lnet/minecraft/client/entity/AbstractClientPlayer;)V")
    private void renderSwordBlock(AbstractClientPlayer player, CallbackInfo info) {
        boolean flag = (player.getPrimaryHand() == EnumHandSide.RIGHT);
        ModelPlayer modelplayer = this.getMainModel();
        if (this.sword != null) {
            if (flag) {
                modelplayer.rightArmPose = this.sword;
            } else {
                modelplayer.leftArmPose = this.sword;
            }
        }
        this.clear();
    }

    @Shadow
    public abstract ModelPlayer getMainModel();

    @Unique
    private void clear() {
        this.sword = null;
    }

}