package z3roco01.reporter;

import z3roco01.composed.annotation.Comment;
import z3roco01.composed.annotation.ConfigProperty;

public class ReporterConfig {
    @Comment(comment = "The token gotten from the discord developer portal")
    @ConfigProperty
    public String token = "DO NOT PUBLISH";

    @Comment(comment = "The id of the Discord channel that will have messages sent to, can be set by the discord command /setchannel")
    @ConfigProperty
    public String channelId = "0";

    @Comment(comment = "The template for chat messages in discord, %n will be replaced with senders name, and %m with the contents")
    @ConfigProperty
    public String messageTemplate = "<%n> %m";

    @ConfigProperty
    public String startingMessage = ":green_circle: Server started!";

    @ConfigProperty
    public String stoppingMessage = ":red_circle: Server stopping...";
}
