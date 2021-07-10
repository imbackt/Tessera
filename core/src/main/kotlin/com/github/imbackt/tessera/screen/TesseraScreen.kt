package com.github.imbackt.tessera.screen

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.github.imbackt.tessera.Tessera
import ktx.app.KtxScreen

abstract class TesseraScreen(
    protected val game: Tessera,
    protected val batch: Batch = game.batch,
    protected val assetManager: AssetManager = game.assetManager,
    protected val viewport: Viewport = game.viewport,
    protected val stage: Stage = game.stage
) : KtxScreen {
    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }
}