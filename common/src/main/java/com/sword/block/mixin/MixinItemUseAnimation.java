package com.sword.block.mixin;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

@Mixin(ItemUseAnimation.class)
@Unique
public class MixinItemUseAnimation {

    @Shadow
    @Final
    @Mutable
    private static ItemUseAnimation[] $VALUES;

    @Shadow
    @Final
    @Mutable
    private static IntFunction<ItemUseAnimation> BY_ID;

    @Shadow
    @Final
    @Mutable
    public static Codec<ItemUseAnimation> CODEC;

    @Shadow
    @Final
    @Mutable
    public static StreamCodec<ByteBuf, ItemUseAnimation> STREAM_CODEC;

    private static final ItemUseAnimation SWORD = create("SWORD:BLOCK", "sword_block");

    public MixinItemUseAnimation(String enumName, int enumId, int id, String name) {

    }

    @Unique
    private static ItemUseAnimation create(String animName, String name) {
        ArrayList<ItemUseAnimation> animations = new ArrayList<>(Arrays.asList(MixinItemUseAnimation.$VALUES));
        int id = animations.get(animations.size() - 1).ordinal() + 1;
        ItemUseAnimation animation = (ItemUseAnimation)(Object)new MixinItemUseAnimation(animName, id, id, name);
        animations.add(animation);
        MixinItemUseAnimation.$VALUES = animations.toArray(new ItemUseAnimation[0]);
        MixinItemUseAnimation.BY_ID = ByIdMap.continuous(ItemUseAnimation::getId, ItemUseAnimation.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        MixinItemUseAnimation.CODEC = StringRepresentable.fromEnum(ItemUseAnimation::values);
        MixinItemUseAnimation.STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ItemUseAnimation::getId);
        return animation;
    }

}