package at.xander.jrftl;

import at.xander.jrftl.handler.JRFTLItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JRFTL.MODID)
public class JRFTL {
	public static final String MODID = "jrftl";

	public Item PreparedFlesh;

	public static JRFTL instance;

	private final Config config = new Config();

	public final JRFTLItems items;

	public JRFTL() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::init);
		bus.addListener(this::clientInit);
		instance = this;
		ModLoadingContext.get().registerConfig(Type.COMMON, config.conf);
		// Register Item Registries at the end
		items = new JRFTLItems(FMLJavaModLoadingContext.get());
	}

	private void clientInit(FMLClientSetupEvent e) {

	}

	public void init(FMLCommonSetupEvent e) {
		CraftingHelper.register(ConditionHardMode.Serializer.instance);
	}

	public boolean isHardMode() {
		if (!config.isLoaded()) {
			System.out.println("Warning config not loaded");
		}
		return config.isHardMode();
	}

}
