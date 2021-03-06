package com.cartoonishvillain.alchemistinfusion.Armor;

import com.cartoonishvillain.alchemistinfusion.AlchemistInfusion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Ice extends ArmorItem {
    public Ice(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(AlchemistInfusion.config.ENABLEINFUSEDBOOTS.get()){
        String msg = TextFormatting.BLUE + "Increased forward movement speed while wearing";
        tooltip.add(new StringTextComponent(msg));
        String msg2 = TextFormatting.RED + "You will be constantly be pushed forward while wearing";
        tooltip.add(new StringTextComponent(msg2));} else {String msg = (TextFormatting.GRAY + "No Effect- Disabled in configuration"); tooltip.add(new StringTextComponent(msg));}
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
