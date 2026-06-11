package ru.levin.mixin.display;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.levin.manager.Manager;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    @Inject(method = "isOutlineVisible", at = @At("HEAD"), cancellable = true)
    private void isOutlineVisible(CallbackInfoReturnable<Boolean> cir) {
        if (Manager.FUNCTION_MANAGER != null && Manager.FUNCTION_MANAGER.blockHighLight.isState()) {
            cir.setReturnValue(false);
        }
    }
}
