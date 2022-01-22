/*
 * Copyright (c) 2021 juancarloscp52
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package me.juancarloscp52.spyglass_improvements.mixin;

import me.juancarloscp52.spyglass_improvements.client.SpyglassConfigurationScreen;
import me.juancarloscp52.spyglass_improvements.client.SpyglassImprovementsClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void insertEntropySettingsButton(CallbackInfo ci) {
        if(!SpyglassImprovementsClient.getInstance().settings.hideSettingsButton)
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 21 - 6, 310, 20, new TranslatableText("options.spyglass-improvements.title"), button -> {
                    this.client.setScreen(new SpyglassConfigurationScreen(this));
            }));
    }

}