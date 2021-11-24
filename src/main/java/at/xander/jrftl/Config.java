package at.xander.jrftl;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = JRFTL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
	private final ForgeConfigSpec.ConfigValue<Boolean> hardModeConfig;
	public final ForgeConfigSpec conf;

	public boolean isLoaded() {
		return conf.isLoaded();
	}

	public boolean isHardMode() {
		return hardModeConfig.get();
	}

	Config() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		builder.push("general");
		hardModeConfig = builder.comment(
				"In HardMode rotten flesh has first to be crafted into prepared flesh by crafting it in a 2x2 grid.",
				"The game has to be restarted for this config to take effect").define("enableHardMode", false);
		builder.pop();
		conf = builder.build();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent configEvent) {
		LogManager.getLogger().debug("Loaded JRFTL config file {}", configEvent.getConfig().getFileName());
	}

}
