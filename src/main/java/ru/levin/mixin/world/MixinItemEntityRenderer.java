package ru.levin.mixin.world;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.levin.manager.IMinecraft;
import ru.levin.manager.Manager;
import ru.levin.modules.render.ItemPhysic;

import static ru.levin.manager.IMinecraft.mc;

@Mixin(ItemEntityRenderer.class)
public abstract class MixinItemEntityRenderer implements IMinecraft {

    @Unique private boolean isOnGround = false;
    @Unique private float capturedAge = 0;

    @Inject(method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    private void captureState(ItemEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        isOnGround = entity.isOnGround();
        capturedAge = entity.age + tickDelta;
    }

    @Inject(
        method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0),
        cancellable = true
    )
    private void onPreRender(ItemEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        ItemPhysic itemPhysic = Manager.FUNCTION_MANAGER.itemPhysic;
        if (!itemPhysic.state) return;

        if (itemPhysic.mode.is("Обычная")) {
            matrices.push();
            float rotation = capturedAge * 3f;
            if (isOnGround) {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            } else {
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));
            }
            matrices.pop();
        } else if (itemPhysic.mode.is("2D")) {
            matrices.push();
            matrices.translate(0.0F, 0.10F, 0.0F);
            matrices.multiply(mc.getEntityRenderDispatcher().getRotation());
            matrices.scale(1.1F, 1.1F, 0.0F);
            matrices.pop();
        }
    }
}
