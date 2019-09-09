package at.xander.jrftl;

import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ConditionHardMode implements ICondition {

	private static final ResourceLocation loc = new ResourceLocation(JRFTL.MODID, "hard_mode");

	@Override
	public ResourceLocation getID() {
		return null;
	}

	@Override
	public boolean test() {
		return JRFTL.instance.isHardMode();
	}

	public static class Serializer implements IConditionSerializer<ConditionHardMode> {

		public static Serializer instance = new Serializer();
		
		@Override
		public void write(JsonObject json, ConditionHardMode value) {

		}

		@Override
		public ConditionHardMode read(JsonObject json) {
			return new ConditionHardMode();
		}

		@Override
		public ResourceLocation getID() {
			return ConditionHardMode.loc;
		}

	}

}
