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
}
