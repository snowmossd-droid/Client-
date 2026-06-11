package ru.levin.mixin.display;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.levin.manager.Manager;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    @ModifyArg(
        method = "render",
        at = @At("HEAD"),
        index = 1
    )
    private boolean modifyRenderBlockOutline(boolean renderBlockOutline) {
        if (Manager.FUNCTION_MANAGER != null) {
            return !Manager.FUNCTION_MANAGER.blockHighLight.isState();
        }
        return renderBlockOutline;
    }
}
