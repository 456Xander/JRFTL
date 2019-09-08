package at.xander.jrftl;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	private final ForgeConfigSpec.ConfigValue<Boolean> hardModeConfig;
	public final ForgeConfigSpec conf;

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

}
