package com.sword.block.mixin;

import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.*;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(UseAnim.class)
@Unique
public class MixinUseAnim {

    @Shadow
    @Final
    @Mutable
    private static UseAnim[] $VALUES;

    private static final UseAnim SWORD = create("SWORD:BLOCK");

    public MixinUseAnim(String name, int id) {

    }

    @Unique
    private static UseAnim create(String animName) {
        ArrayList<UseAnim> animations = new ArrayList<>(Arrays.asList(MixinUseAnim.$VALUES));
        UseAnim animation = (UseAnim)(Object)new MixinUseAnim(animName, animations.get(animations.size() - 1).ordinal() + 1);
        animations.add(animation);
        MixinUseAnim.$VALUES = animations.toArray(new UseAnim[0]);
        return animation;
    }

}