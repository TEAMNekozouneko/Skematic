package com.efnilite.skematic.utils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.entity.Player;

public final class WeUtils {

    private WeUtils() {}

    public static LocalSession getLocalSession(Player p) {
        return p.getSession();
    }

    public static LocalSession getLocalSession(org.bukkit.entity.Player p) {
        return BukkitAdapter.adapt(p).getSession();
    }

    public static EditSession getEditSession(Player p) {
        return p.getSession().createEditSession(p);
    }

    public static EditSession getEditSession(org.bukkit.entity.Player p) {
        return getEditSession(BukkitAdapter.adapt(p));
    }

}
