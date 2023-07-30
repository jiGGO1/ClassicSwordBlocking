package com.sword.block.mixin;

import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(UseAnim.class)
@Unique
public class MixinUseAnim {

    @Shadow
    @Final
    @Mutable
    private static UseAnim[] $VALUES;

    private static final UseAnim SWORD = useAnim$addAnim("SWORD:BLOCK");

    @Invoker("<init>")
    public static UseAnim useAnim$invokeInit(String name, int id) {
        throw new AssertionError();
    }

    private static UseAnim useAnim$addAnim(String animName) {
        ArrayList<UseAnim> animations = new ArrayList<>(Arrays.asList(MixinUseAnim.$VALUES));
        UseAnim animation = useAnim$invokeInit(animName, animations.get(animations.size() - 1).ordinal() + 1);
        animations.add(animation);
        MixinUseAnim.$VALUES = animations.toArray(new UseAnim[0]);
        return animation;
    }

}