package cn.mcmod.sakura.item.armors;

import java.util.List;

import com.google.common.collect.Lists;

import cn.mcmod.sakura.SakuraMain;
import cn.mcmod.sakura.api.armor.ArmorLoader;
import cn.mcmod.sakura.client.model.ModelHaori;
import cn.mcmod.sakura.item.ItemLoader;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHaori extends ItemArmor {
    public ItemHaori() {
        super(ItemLoader.KIMONO_MATERIAL, 0, EntityEquipmentSlot.CHEST);
        setUnlocalizedName(SakuraMain.MODID + "." + "haori");
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
    	return ItemKimono.getKimonoModel(entityLiving, itemStack, new ModelHaori());
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	NBTTagCompound nbt = RecipesUtil.getInstance().getItemTagCompound(stack);
    	String name = ItemKimono.texture_name.get(nbt,"haori_base");
    	tooltip.add(I18n.format("sakura.haori.texture.name")+":"+I18n.format("item.sakura."+name+".name"));
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    public static final List<String> KimonoIDs = Lists.newArrayList();
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    	if(this.isInCreativeTab(tab)){
	        for(String name : KimonoIDs){
	            ItemStack kimono = ArmorLoader.getInstance().getCustomArmor(name,this);
	            if(!kimono.isEmpty()) items.add(kimono);
	        }
    	}
    	super.getSubItems(tab, items);
    }
    
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    	NBTTagCompound nbt = RecipesUtil.getInstance().getItemTagCompound(stack);
        String name = ItemKimono.texture_name.get(nbt,"haori_base");
    	return SakuraMain.MODID + ":" + "textures/models/armor/"+name+".png";
    }
 
}
