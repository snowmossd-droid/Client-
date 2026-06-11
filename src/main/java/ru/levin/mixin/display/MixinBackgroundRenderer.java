package ru.levin.mixin.display;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.levin.events.Event;
import ru.levin.events.impl.world.EventFog;
import ru.levin.manager.Manager;

@SuppressWarnings("All")
@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {

    @Inject(method = "getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;", at = @At("HEAD"), cancellable = true)
    private static void onGetFogModifier(Entity entity, float tickDelta, CallbackInfoReturnable<Object> info) {
        if (Manager.FUNCTION_MANAGER.noRender.state && Manager.FUNCTION_MANAGER.noRender.mods.get("Плохие эффекты"))
            info.setReturnValue(null);
    }

    @ModifyArgs(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V"))
    private static void modifyFogStart(Args args) {
        EventFog fogEvent = new EventFog();
        Event.call(fogEvent);
        if (fogEvent.modified) {
            args.set(0, fogEvent.start);
        }
    }

    @ModifyArgs(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogEnd(F)V"))
    private static void modifyFogEnd(Args args) {
        EventFog fogEvent = new EventFog();
        Event.call(fogEvent);
        if (fogEvent.modified) {
            args.set(0, fogEvent.end);
        }
    }
}
