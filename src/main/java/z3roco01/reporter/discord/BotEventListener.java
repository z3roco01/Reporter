package z3roco01.reporter.discord;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import z3roco01.reporter.Reporter;

public class BotEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getGuild() == null) return;

        switch(event.getName()) {
            case "server" -> event.reply("yup its a server")
                    .setEphemeral(true)
                    .queue();
            case "setchannel" -> setChannel(event);
        }
    }

    private void setChannel(SlashCommandInteractionEvent event) {
        GuildChannelUnion channel =  event.getOption("channel").getAsChannel();

        if(channel.getType() != ChannelType.TEXT) {
            event.reply("Channel: " + channel.getAsMention() + " is not a text channel")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        // update channel in config and bot manager
        Reporter.LOGGER.info("what");
        Reporter.setDiscordChannel(channel.getId());

        // now reply
        event.reply("Channel is now set to: " + channel.getAsMention())
                .setEphemeral(true)
                .queue();
    }
}
