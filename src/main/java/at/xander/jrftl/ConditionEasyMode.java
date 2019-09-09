package at.xander.jrftl;

import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;


public class ConditionEasyMode implements IConditionSerializer<ICondition>{

	@Override
	public void write(JsonObject json, ICondition value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICondition read(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getID() {
		// TODO Auto-generated method stub
		return null;
	}


}
