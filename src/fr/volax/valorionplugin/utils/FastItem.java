package fr.volax.valorionplugin.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class FastItem {
    private ItemStack is;

    public FastItem(Material m) {
        this(m, 1);
    }

    public FastItem(ItemStack is) {
        this.is = is;
    }

    public FastItem(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    public FastItem(Material m, int amount, short meta){
        is = new ItemStack(m, amount, meta);
    }

    public FastItem clone() {
        return new FastItem(is);
    }

    public FastItem setDurability(short dur) {
        is.setDurability(dur);
        return this;
    }

    public FastItem setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public FastItem addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public FastItem removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }

    public FastItem setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public FastItem addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }
       // public FastItem addFlag(ItemFlag itemsflags){
       //     ItemMeta im = is.getItemMeta();
       //     im.addItemFlags(itemsflags);
        //    is.setItemMeta(im);
       //     return this;
      //  }
    public FastItem setInfinityDurability() {
        is.setDurability(Short.MAX_VALUE);
        return this;
    }

    public FastItem setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    @SuppressWarnings("deprecation")
    public FastItem setWoolColor(DyeColor color) {
        if (!is.getType().equals(Material.WOOL))
            return this;
        this.is.setDurability(color.getData());
        return this;
    }

    public FastItem setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemStack toItemStack() {
        return is;
    }
}
