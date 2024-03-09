package at.xander.jrftl.handler;

import com.mojang.serialization.Codec;

import at.xander.jrftl.ConditionHardMode;
import at.xander.jrftl.JRFTL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JRFTLItems {
	//Registries
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JRFTL.MODID);
	private final DeferredRegister<Codec<? extends ICondition>> CONDITION_SERIALIZERS = DeferredRegister.create(ForgeRegistries.CONDITION_SERIALIZERS, JRFTL.MODID);
	
	// Items
	public final RegistryObject<Item> PreparedFlesh = ITEMS.register("prepared_flesh", () -> new Item(new Item.Properties()));
	
	// Conditions
	public final RegistryObject<Codec<ConditionHardMode>> HardMode = CONDITION_SERIALIZERS.register("hard_mode", () -> ConditionHardMode.CODEC);
	
	// Init Function

	public JRFTLItems(FMLJavaModLoadingContext ctx) {
		ITEMS.register(ctx.getModEventBus());
		CONDITION_SERIALIZERS.register(ctx.getModEventBus());
		ctx.getModEventBus().addListener(this::handleCreativeModeTabPopulation);
		ctx.getModEventBus().addListener(this::handleAdditionalModels);

	}
	
	private void handleAdditionalModels(ModelEvent.RegisterAdditional event) {
		event.register(new ResourceLocation(JRFTL.MODID, "prepared_flesh"));
	}
	
	private void handleCreativeModeTabPopulation(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			if (JRFTL.instance.isHardMode()) {
				event.accept(PreparedFlesh.get());
			}
		}
	}
}
