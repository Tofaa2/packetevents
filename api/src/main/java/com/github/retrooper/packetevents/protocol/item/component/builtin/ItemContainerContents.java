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

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;

import java.util.List;

public class ItemContainerContents {

    private List<ItemStack> items;

    public ItemContainerContents(List<ItemStack> items) {
        this.items = items;
    }

    public static ItemContainerContents read(PacketWrapper<?> wrapper) {
        List<ItemStack> items = wrapper.readList(PacketWrapper::readItemStack);
        return new ItemContainerContents(items);
    }

    public static void write(PacketWrapper<?> wrapper, ItemContainerContents contents) {
        wrapper.writeList(contents.items, PacketWrapper::writeItemStack);
    }

    public void addItem(ItemStack itemStack) {
        this.items.add(itemStack);
    }

    public List<ItemStack> getItems() {
        return this.items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }
}
