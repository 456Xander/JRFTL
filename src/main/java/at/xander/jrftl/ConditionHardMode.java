package at.xander.jrftl;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionSerializer;

public class ConditionHardMode implements IConditionSerializer {

	@Override
	public BooleanSupplier parse(JsonObject json) {
		return JRFTL.instance::isHardMode;
	}

}
