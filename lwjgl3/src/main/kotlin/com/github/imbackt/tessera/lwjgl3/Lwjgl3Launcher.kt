package com.github.imbackt.tessera.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.github.imbackt.tessera.Tessera

fun main() {
    Lwjgl3Application(Tessera(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Tessera")
        setWindowSizeLimits(504, 896, -1, -1)
    })
}