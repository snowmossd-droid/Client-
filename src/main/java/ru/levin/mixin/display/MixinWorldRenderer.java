package ru.levin.mixin.display;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import ru.levin.manager.Manager;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    @ModifyVariable(method = "render", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private boolean modifyRenderBlockOutline(boolean renderBlockOutline) {
        if (Manager.FUNCTION_MANAGER != null && Manager.FUNCTION_MANAGER.blockHighLight != null && Manager.FUNCTION_MANAGER.blockHighLight.isState()) {
            return false;
        }
        return renderBlockOutline;
    }
}
