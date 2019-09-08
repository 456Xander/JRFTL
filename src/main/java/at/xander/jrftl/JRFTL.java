package at.xander.jrftl;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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

	public JRFTL() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::init);
		bus.addListener(this::clientInit);
		instance = this;
		ModLoadingContext.get().registerConfig(Type.COMMON, config.conf);
	}

	private void clientInit(FMLClientSetupEvent e) {

	}

	// @SidedProxy(clientSide = "at.xander.jrftl.proxy.ClientProxy", serverSide =
	// "at.xander.jrftl.proxy.CommonProxy")
	// public static CommonProxy proxy;

	public void init(FMLCommonSetupEvent e) {
		CraftingHelper.register(new ResourceLocation(MODID, "hard_mode"), new ConditionHardMode());
		CraftingHelper.register(new ResourceLocation(MODID, "easy_mode"), new ConditionEasyMode());
	}

	public boolean isHardMode() {
		return config.isHardMode();
	}
}
