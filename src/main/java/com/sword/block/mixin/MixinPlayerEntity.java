package com.sword.block.mixin;

import com.sword.block.SwordBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * @author ji_GGO
 * @date 2021/08/13
 */
@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    public MixinPlayerEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "damageEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isInvulnerableTo(Lnet/minecraft/util/DamageSource;)Z"), ordinal = 0)
    private float onSwordBlock(float damage, DamageSource source){
        if (!this.isInvulnerable(source)) {
            if (!source.isUnblockable() && this.isBlocking() && damage > 0) {
                damage = (1F + damage) * 0.5F;
            }
        }
        return damage;
    }

    private boolean isBlocking() {
        if (this.isHandActive() && !this.activeItemStack.isEmpty()) {
            Item item = this.activeItemStack.getItem();
            return item.getUseAction(this.activeItemStack) == SwordBlock.SWORD;
        }
        return false;
    }

    private boolean isInvulnerable(DamageSource source) {
        return this.isInvulnerable() && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer();
    }

}