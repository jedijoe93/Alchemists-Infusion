package com.jedijoe.alchemistinfusion.Blocks.MultiEffect;

import com.jedijoe.alchemistinfusion.Blocks.SingleEffect.SingleInfusedBlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MobilityStone extends SingleInfusedBlockBase {

    public MobilityStone() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).harvestLevel(0).setRequiresTool());
    }

    @Override
    public void onEntityWalk(World world, BlockPos blockpos, Entity entity) {
        ApplyEffect(Effects.SPEED, 2, 3, entity);
        ApplyEffect(Effects.JUMP_BOOST, 2, 4, entity);
    }

}