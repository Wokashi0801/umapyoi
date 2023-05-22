package net.tracen.umapyoi.registry.skills;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class LowHealthHealSkill extends UmaSkill {
    private final int level;

    public LowHealthHealSkill(Builder builder, int level) {
        super(builder);
        this.level = level;
    }

    @Override
    public void applySkill(Level level, Player user) {
        boolean lowHealth = (user.getHealth() / user.getMaxHealth()) < 0.6;
        user.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100 * this.getSkillLevel(),
                lowHealth ? this.getSkillLevel() : this.getSkillLevel() - 1));
        user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200 * this.getSkillLevel(),
                lowHealth ? this.getSkillLevel() : this.getSkillLevel() - 1));
        if(lowHealth)
            user.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, this.getSkillLevel() - 1));
    }

    public int getSkillLevel() {
        return level;
    }

}