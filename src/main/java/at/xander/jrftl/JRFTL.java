package at.xander.jrftl;

import at.xander.jrftl.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = JRFTL.MODID, name = "Just another rotten Flesh to Leather Mod", version = "1.1")
public class JRFTL {
	public static final String MODID = "jrftl";

	public Item PreparedFlesh;
	private boolean hardMode;

	@Instance
	public static JRFTL instance;

	@SidedProxy(clientSide = "at.xander.jrftl.proxy.ClientProxy", serverSide = "at.xander.jrftl.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// Config
		Configuration conf = new Configuration(e.getSuggestedConfigurationFile());

		Property prop = conf.get("General", "HardMode", false,
				"In HardMode you have to craft 4 rotten flesh to one prepared flesh before smelting");

		hardMode = prop.getBoolean();
		conf.save();

		PreparedFlesh = new Item().setUnlocalizedName("prepared_flesh")
				.setCreativeTab(hardMode ? CreativeTabs.MISC : null).setRegistryName("prepared_flesh");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		GameRegistry.addSmelting(hardMode ? PreparedFlesh : Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 0.2f);
		proxy.registerTexture(PreparedFlesh);
		GameRegistry.register(PreparedFlesh);
		if (hardMode) {
			GameRegistry.addShapedRecipe(new ItemStack(PreparedFlesh), "##", "##", '#', Items.ROTTEN_FLESH);
		}

	}

	public boolean isHardMode() {
		return hardMode;
	}
}
