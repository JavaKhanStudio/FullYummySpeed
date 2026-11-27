package com.mygdx.game.desktop;

import org.lwjgl.glfw.GLFW;

public class Platform_Desktop 
{
	/**
	 * GLFW picks native Wayland when it can; the LWJGL2 launchers always ran on X11, and
	 * tools/offscreen-shot.sh shoots an X11 window. Use XWayland whenever an X display exists.
	 * Must run before anything initialises GLFW.
	 */
	public static void preferX11OnLinux()
	{
		if(System.getProperty("os.name").toLowerCase().contains("linux") && System.getenv("DISPLAY") != null)
			GLFW.glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_X11);
	}
}
