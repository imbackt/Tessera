package com.github.imbackt.tessera.screen

import com.github.imbackt.tessera.Labels
import com.github.imbackt.tessera.Tessera
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table

class GameScreen(game: Tessera) : TesseraScreen(game) {
    override fun show() {
        stage.clear()
        stage.actors {
            table {
                defaults().expand()
                label("Game Screen", skin = Scene2DSkin.defaultSkin, style = Labels.Huge())
                setFillParent(true)
            }
        }
    }

    override fun render(delta: Float) {
        stage.run {
            viewport.apply(true)
            act(delta)
            draw()
        }
    }
}