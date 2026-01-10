package z3roco01.reporter.discord;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getGuild() == null) return;

        switch(event.getName()) {
            case "server" -> event.reply("yup its a server")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
