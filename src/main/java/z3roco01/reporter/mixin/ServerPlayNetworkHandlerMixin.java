package z3roco01.reporter.mixin;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import z3roco01.reporter.Reporter;
import z3roco01.reporter.discord.BotManager;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onChatMessage", at = @At("TAIL"))
    private void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        /*Reporter.LOGGER.info(packet.chatMessage());
        BotManager.sendMessage(packet.chatMessage());*/
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    private void sendChatMessage(SignedMessage message, MessageType.Parameters params, CallbackInfo ci) {
        String discMsg = Reporter.config.messageTemplate;
        discMsg = discMsg.replace("%n", params.name().getString());
        discMsg = discMsg.replace("%m", message.getContent().getLiteralString());
        BotManager.sendMessage(discMsg);
    }
}
