package com.github.imbackt.tessera

import ktx.app.KtxGame
import ktx.app.KtxScreen

class Tessera : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(GameScreen())
        setScreen<GameScreen>()
    }
}