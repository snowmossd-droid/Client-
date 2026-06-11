package ru.levin.util.render.providers;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

public final class ResourceProvider {
	public static final Identifier TEXTURE_SHADER_KEY = getShaderIdentifier("texture");
	public static final Identifier RECTANGLE_SHADER_KEY = getShaderIdentifier("rectangle");
	public static final Identifier BLUR_SHADER_KEY = getShaderIdentifier("blur");
	public static final Identifier RECTANGLE_BORDER_SHADER_KEY = getShaderIdentifier("border");
	public static final Identifier GLASS_SHADER_KEY = getGlass("data");

	public static final Identifier firefly = Identifier.of("exosware", "images/particles/firefly.png");
	public static final Identifier bloom = Identifier.of("exosware", "images/particles/bloom.png");
	public static final Identifier snowflake = Identifier.of("exosware", "images/particles/snowflake.png");
	public static final Identifier dollar = Identifier.of("exosware", "images/particles/dollar.png");
	public static final Identifier heart = Identifier.of("exosware", "images/particles/heart.png");
	public static final Identifier star = Identifier.of("exosware", "images/particles/star.png");
	public static final Identifier spark = Identifier.of("exosware", "images/particles/spark.png");
	public static final Identifier crown = Identifier.of("exosware", "images/particles/crown.png");
	public static final Identifier lightning = Identifier.of("exosware", "images/particles/lightning.png");
	public static final Identifier line = Identifier.of("exosware", "images/particles/line.png");
	public static final Identifier point = Identifier.of("exosware", "images/particles/point.png");
	public static final Identifier rhombus = Identifier.of("exosware", "images/particles/rhombus.png");

	public static final Identifier marker = Identifier.of("exosware", "images/targetesp/target.png");
	public static final Identifier marker2 = Identifier.of("exosware", "images/targetesp/target2.png");

	public static final Identifier CUSTOM_CAPE = Identifier.of("exosware", "cape/cape.png");
	public static final Identifier CUSTOM_ELYTRA = Identifier.of("exosware", "cape/elytra.png");

	public static final Identifier container = Identifier.of("exosware", "images/hud/container.png");

	public static final Identifier color_image = Identifier.of("exosware", "images/gui/pick.png");

	private static Identifier getGlass(String name) {
		return Identifier.of("exosware", "core/glass/" + name);
	}
	private static Identifier getShaderIdentifier(String name) {
		return Identifier.of("exosware", "core/" + name);
	}
}
