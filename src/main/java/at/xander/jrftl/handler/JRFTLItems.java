package at.xander.jrftl.handler;

import at.xander.jrftl.JRFTL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JRFTLItems {
	//Registries
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JRFTL.MODID);
	
	// Items
	public final RegistryObject<Item> PreparedFlesh = ITEMS.register("prepared_flesh", () -> new Item(new Item.Properties()));

	// Init Function

	public JRFTLItems(FMLJavaModLoadingContext ctx) {
		ITEMS.register(ctx.getModEventBus());
		ctx.getModEventBus().addListener(this::handleCreativeModeTabPopulation);
		ctx.getModEventBus().addListener(this::handleAdditionalModels);
	}
	
	private void handleAdditionalModels(ModelEvent.RegisterAdditional event) {
		event.register(new ResourceLocation(JRFTL.MODID, "prepared_flesh"));
	}
	
	private void handleCreativeModeTabPopulation(CreativeModeTabEvent.BuildContents event) {
		if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
			if (JRFTL.instance.isHardMode()) {
				event.accept(PreparedFlesh.get());
			}
		}
	}
}
