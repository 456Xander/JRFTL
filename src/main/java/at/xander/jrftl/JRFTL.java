package at.xander.jrftl;

import java.util.List;

import at.xander.jrftl.handler.RegisterHandler;
import at.xander.jrftl.proxy.CommonProxy;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JRFTL.MODID)
public class JRFTL {
	public static final String MODID = "jrftl";

	public Item PreparedFlesh;
	private boolean hardMode;

	public final static JRFTL instance = new JRFTL();

	public JRFTL() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::preInit);
		bus.addListener(this::clientInit);

	}

	private void clientInit(FMLClientSetupEvent e) {

	}

	// @SidedProxy(clientSide = "at.xander.jrftl.proxy.ClientProxy", serverSide =
	// "at.xander.jrftl.proxy.CommonProxy")
	// public static CommonProxy proxy;

	public void preInit(FMLCommonSetupEvent e) {
		// Config
		// Configuration conf = new Configuration(e.getSuggestedConfigurationFile());
		//
		// Property prop = conf.get("General", "HardMode", false,
		// "In HardMode you have to craft 4 rotten flesh to one prepared flesh before
		// smelting");
		//
		// hardMode = prop.getBoolean();
		// conf.save();
		//
		// PreparedFlesh = new Item().setUnlocalizedName("prepared_flesh")
		// .setCreativeTab(hardMode ? CreativeTabs.MISC :
		// null).setRegistryName("prepared_flesh");
		//
		// MinecraftForge.EVENT_BUS.register(RegisterHandler.instance);
	}

	// public void init( e) {
	// GameRegistry.addSmelting(hardMode ? PreparedFlesh : Items.ROTTEN_FLESH, new
	// ItemStack(Items.LEATHER), 0.2f);
	// proxy.registerTexture(PreparedFlesh);
	// }

	public void registerItems(List<Item> itemsToRegister) {
		itemsToRegister.add(PreparedFlesh);
	}

	public boolean isHardMode() {
		return hardMode;
	}
}
