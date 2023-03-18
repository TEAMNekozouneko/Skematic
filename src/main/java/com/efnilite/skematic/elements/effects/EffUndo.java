package com.efnilite.skematic.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.efnilite.skematic.utils.FaweUtils;
import com.efnilite.skematic.utils.WeUtils;
import com.sk89q.worldedit.EditSession;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Undo")
@Description("Undo the last edit of a player.")
@Examples("undo the last change of player")
@Since("1.0")
public class EffUndo extends Effect {

    private Expression<Player> player;

    static {
        Skript.registerEffect(EffUndo.class, "undo [the] last [(fawe|fastasyncworldedit)] (action|change) of %player%");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        player = (Expression<Player>) expressions[0];

        return true;
    }

    @Override
    protected void execute(Event e) {
        Player player = this.player.getSingle(e);

        if (player == null) {
            return;
        }

        EditSession session = WeUtils.getEditSession(player);
        session.undo(session);
        session.flushQueue();
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "undo last change of " + player.toString(e, debug);
    }
}
