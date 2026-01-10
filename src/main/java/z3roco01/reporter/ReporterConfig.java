package z3roco01.reporter;

import z3roco01.composed.annotation.Comment;
import z3roco01.composed.annotation.ConfigProperty;

public class ReporterConfig {
    @Comment(comment = "The token gotten from the discord developer portal")
    @ConfigProperty
    public String token = "DO NOT PUBLISH";
}
