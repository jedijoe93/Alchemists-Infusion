package com.cartoonishvillain.alchemistinfusion.Blocks.Infusions;

import com.cartoonishvillain.alchemistinfusion.Blocks.Potion.PotionBlockItemBase;
import com.cartoonishvillain.alchemistinfusion.Crafting.InfusionRecipe;
import com.cartoonishvillain.alchemistinfusion.Crafting.RecipeProcessor;
import com.cartoonishvillain.alchemistinfusion.Items.KeyItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;

public class PrimedPavement extends Block {
    public PrimedPavement() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).harvestLevel(0).setRequiresTool());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){

        if(!worldIn.isRemote() && handIn == Hand.MAIN_HAND) {
            ArrayList<ArrayList<ItemStack>> recipe = null;
            ArrayList<ItemEntity> itemEntities = RecipeProcessor.Scan(pos, worldIn);
            Item keyItem = null;
            for (ItemEntity itemEntity : itemEntities) {
                if (itemEntity.getItem().getItem() instanceof KeyItem || itemEntity.getItem().getItem() instanceof PotionBlockItemBase)
                    keyItem = itemEntity.getItem().getItem();
                if (keyItem != null) break;
            }

            if (keyItem != null) {
                recipe = InfusionRecipe.ParseTier1Recipe(keyItem);
                if(recipe.size() > 0)
                RecipeProcessor.AttemptRecipe(recipe, itemEntities, pos, worldIn, null, player);
                else{player.sendStatusMessage(new StringTextComponent("No recipes found. Are you using the right tier? Do you have the ingredients? (Currently: Tier 1)"), false);
                    ServerWorld serverWorld = (ServerWorld) worldIn;
                    serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.5, 0.5, 0.5, 0);
                    serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.1, 0.5, 0.1, 0);
                    serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.1, 0.5, 0.9, 0);
                    serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.9, 0.5, 0.9, 0);
                    serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.9, 0.5, 0.1, 0);
                    worldIn.playSound(null, pos, new SoundEvent(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE.getRegistryName()), SoundCategory.BLOCKS, 100f, 1.5f);}
            }
            else{player.sendStatusMessage(new StringTextComponent("No recipes found. Are you using the right tier? Do you have the ingredients? (Currently: Tier 1)"), false);
                ServerWorld serverWorld = (ServerWorld) worldIn;
                serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.5, 0.5, 0.5, 0);
                serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.1, 0.5, 0.1, 0);
                serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.1, 0.5, 0.9, 0);
                serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.9, 0.5, 0.9, 0);
                serverWorld.spawnParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0.9, 0.5, 0.1, 0);
                worldIn.playSound(null, pos, new SoundEvent(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE.getRegistryName()), SoundCategory.BLOCKS, 100f, 1.5f);}
        }
        return ActionResultType.SUCCESS;
    }
}
