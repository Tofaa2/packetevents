/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2024 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.protocol.item.component.builtin;

import com.github.retrooper.packetevents.protocol.mapper.GenericMappedEntity;
import com.github.retrooper.packetevents.protocol.mapper.MappedEntitySet;
import com.github.retrooper.packetevents.protocol.world.states.type.StateType;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemTool {

    private List<Rule> rules;
    private float defaultMiningSpeed;
    private int damagePerBlock;

    public ItemTool(List<Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        this.rules = rules;
        this.defaultMiningSpeed = defaultMiningSpeed;
        this.damagePerBlock = damagePerBlock;
    }

    public static ItemTool read(PacketWrapper<?> wrapper) {
        List<Rule> rules = wrapper.readList(Rule::read);
        float defaultMiningSpeed = wrapper.readFloat();
        int damagePerBlock = wrapper.readVarInt();
        return new ItemTool(rules, defaultMiningSpeed, damagePerBlock);
    }

    public static void write(PacketWrapper<?> wrapper, ItemTool tool) {
        wrapper.writeList(tool.rules, Rule::write);
        wrapper.writeFloat(tool.defaultMiningSpeed);
        wrapper.writeVarInt(tool.damagePerBlock);
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public List<Rule> getRules() {
        return this.rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public float getDefaultMiningSpeed() {
        return this.defaultMiningSpeed;
    }

    public void setDefaultMiningSpeed(float defaultMiningSpeed) {
        this.defaultMiningSpeed = defaultMiningSpeed;
    }

    public int getDamagePerBlock() {
        return this.damagePerBlock;
    }

    public void setDamagePerBlock(int damagePerBlock) {
        this.damagePerBlock = damagePerBlock;
    }

    public static class Rule {

        private MappedEntitySet<StateType> blocks;
        private @Nullable Float speed;
        private @Nullable Boolean correctForDrops;

        public Rule(MappedEntitySet<StateType> blocks, @Nullable Float speed, @Nullable Boolean correctForDrops) {
            this.blocks = blocks;
            this.speed = speed;
            this.correctForDrops = correctForDrops;
        }

        public static Rule read(PacketWrapper<?> wrapper) { // TODO - implement registry for StateTypes
            MappedEntitySet<StateType> blocks = MappedEntitySet.read(wrapper, GenericMappedEntity::getById);
            Float speed = wrapper.readOptional(PacketWrapper::readFloat);
            Boolean correctForDrops = wrapper.readOptional(PacketWrapper::readBoolean);
            return new Rule(blocks, speed, correctForDrops);
        }

        public static void write(PacketWrapper<?> wrapper, Rule rule) {
            MappedEntitySet.write(wrapper, rule.blocks);
            wrapper.writeOptional(rule.speed, PacketWrapper::writeFloat);
            wrapper.writeOptional(rule.correctForDrops, PacketWrapper::writeBoolean);
        }

        public MappedEntitySet<StateType> getBlocks() {
            return this.blocks;
        }

        public void setBlocks(MappedEntitySet<StateType> blocks) {
            this.blocks = blocks;
        }

        public @Nullable Float getSpeed() {
            return this.speed;
        }

        public void setSpeed(@Nullable Float speed) {
            this.speed = speed;
        }

        public @Nullable Boolean getCorrectForDrops() {
            return this.correctForDrops;
        }

        public void setCorrectForDrops(@Nullable Boolean correctForDrops) {
            this.correctForDrops = correctForDrops;
        }
    }
}
