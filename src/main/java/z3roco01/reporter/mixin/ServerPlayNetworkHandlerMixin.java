package z3roco01.reporter.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import z3roco01.reporter.Reporter;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onChatMessage", at = @At("TAIL"))
    private void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        Reporter.LOGGER.info(packet.chatMessage());
    }
}
