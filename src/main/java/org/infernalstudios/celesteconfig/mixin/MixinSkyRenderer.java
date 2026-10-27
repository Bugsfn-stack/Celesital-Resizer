package org.infernalstudios.celesteconfig.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import org.infernalstudios.celesteconfig.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Since 1.21.5 the sun and moon are drawn by net.minecraft.client.renderer.SkyRenderer from
 * pre-built quad buffers, transformed only by the PoseStack. We therefore push the stack and scale
 * the X/Z axes (the plane of the quad) on entry to renderSun/renderMoon, and pop it on exit.
 * Methods are matched by name so the changing parameter lists (e.g. MoonPhase) don't matter.
 */
@Mixin(net.minecraft.client.renderer.SkyRenderer.class)
public class MixinSkyRenderer {

    @Unique
    private PoseStack celesteconfig$sunStack;

    @Unique
    private PoseStack celesteconfig$moonStack;

    @ModifyVariable(method = "renderSun", at = @At("HEAD"), argsOnly = true)
    private PoseStack celesteconfig$scaleSun(PoseStack poseStack) {
        poseStack.pushPose();
        poseStack.scale((float) Constants.sunWidth, 1.0F, (float) Constants.sunHeight);
        celesteconfig$sunStack = poseStack;
        return poseStack;
    }

    @Inject(method = "renderSun", at = @At("RETURN"))
    private void celesteconfig$endSun(CallbackInfo ci) {
        if (celesteconfig$sunStack != null) {
            celesteconfig$sunStack.popPose();
            celesteconfig$sunStack = null;
        }
    }

    @ModifyVariable(method = "renderMoon", at = @At("HEAD"), argsOnly = true)
    private PoseStack celesteconfig$scaleMoon(PoseStack poseStack) {
        poseStack.pushPose();
        poseStack.scale((float) Constants.moonWidth, 1.0F, (float) Constants.moonHeight);
        celesteconfig$moonStack = poseStack;
        return poseStack;
    }

    @Inject(method = "renderMoon", at = @At("RETURN"))
    private void celesteconfig$endMoon(CallbackInfo ci) {
        if (celesteconfig$moonStack != null) {
            celesteconfig$moonStack.popPose();
            celesteconfig$moonStack = null;
        }
    }
}
