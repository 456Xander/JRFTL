package at.xander.jrftl.handler;

import at.xander.jrftl.JRFTL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JRFTLItems {
	//Registries
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JRFTL.MODID);
	
	// Items
	public final RegistryObject<Item> PreparedFlesh = ITEMS.register("prepared_flesh", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	// Init Function

	public JRFTLItems(FMLJavaModLoadingContext ctx) {
		ITEMS.register(ctx.getModEventBus());
	}
}
