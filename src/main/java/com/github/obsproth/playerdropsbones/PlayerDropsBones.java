package com.github.obsproth.playerdropsbones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("playerdropsbones")
public class PlayerDropsBones
{
    private static final Logger LOGGER = LogManager.getLogger();

    public PlayerDropsBones() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	LOGGER.info("setup");
    }


    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
    	if (event.getEntityLiving().getEntityWorld().isRemote) {
    		return;
    	}
    	if (!(event.getEntity() instanceof PlayerEntity)) {
    		return;
    	}
    	PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    	ItemStack itemStack = new ItemStack(Items.BONE);
    	itemStack.setDisplayName(new StringTextComponent(player.getDisplayName().getString() + "ÇÃçú"));
    	player.getEntityWorld().addEntity(new ItemEntity(player.getEntityWorld(), player.getPosX(), player.getPosY(), player.getPosZ(), itemStack));
    }

}
