package ru.levin.mixin.player;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.levin.manager.Manager;

@SuppressWarnings("All")
@Mixin(LivingEntityRenderer.class)
public class MixinPlayerEntityRenderer {
    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"), cancellable = true)
    private void renderLabelIfPresent(LivingEntity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, float tickDelta, CallbackInfo ci) {
        if (entity instanceof PlayerEntity && Manager.FUNCTION_MANAGER.nameTags.state && Manager.FUNCTION_MANAGER.nameTags.tags.get("Игроки")) {
            ci.cancel();
        }
    }
}
