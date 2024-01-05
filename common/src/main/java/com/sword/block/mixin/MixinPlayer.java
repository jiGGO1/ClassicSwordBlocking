package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {

    public MixinPlayer(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @ModifyVariable(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z"), ordinal = 0)
    private float onSwordBlocking(float damage, DamageSource source) {
        if (!this.isInvulnerable(source)) {
            if (!source.isBypassArmor() && this.blocking() && damage > 0) {
                damage = (1F + damage) * 0.5F;
            }
        }
        return damage;
    }

    @Unique
    private boolean blocking() {
        if (this.isUsingItem() && !this.useItem.isEmpty()) {
            Item item = this.useItem.getItem();
            return item.getUseAnimation(this.useItem) == SwordBlock.SWORD;
        }
        return false;
    }

    @Unique
    private boolean isInvulnerable(DamageSource source) {
        return this.isInvulnerable() && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer();
    }

}