package at.xander.jrftl;

import com.mojang.serialization.Codec;

import net.minecraftforge.common.crafting.conditions.ICondition;

public class ConditionHardMode implements ICondition {
    public static final Codec<ConditionHardMode> CODEC = Codec.unit(ConditionHardMode::new);

	@Override
	public boolean test(IContext context) {
		return JRFTL.instance.isHardMode();
	}

	@Override
	public Codec<? extends ICondition> codec() {
		return CODEC;
	}
	
	@Override
	public String toString() {
		return "jrftl_is_hardmode()";
	}

}
