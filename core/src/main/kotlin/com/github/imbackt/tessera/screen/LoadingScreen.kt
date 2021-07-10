package com.github.imbackt.tessera.screen

import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.github.imbackt.tessera.*
import ktx.actors.*
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actor
import ktx.scene2d.actors
import ktx.scene2d.table

class LoadingScreen(game: Tessera) : TesseraScreen(game) {
    private val label = Label("Loading...", Scene2DSkin.defaultSkin, Labels.Huge()).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private var finishedLoading = false

    override fun show() {
        MusicAssets.values().forEach { assetManager.load(it) }
        SoundAssets.values().forEach { assetManager.load(it) }

        stage.clear()
        stage.actors {
            table {
                actor(label) { cell -> cell.expand().fill() }.onClick {
                    if (finishedLoading) {
                        game.removeScreen<LoadingScreen>()
                        this@LoadingScreen.dispose()

                        if (!game.containsScreen<GameScreen>())
                            game.addScreen(GameScreen(game))
                        //TODO(): Implement Menu, Levels and Highscore Screens

                        game.setScreen<GameScreen>()
                    }
                }
                setFillParent(true)
            }
        }
    }

    override fun render(delta: Float) {
        if (assetManager.update() && !finishedLoading) {
            finishedLoading = true
            label.txt = "Touch to continue"
            label += forever(alpha(0f) + fadeIn(1f) + delay(0.25f) + fadeOut(1f))
        }
        stage.run {
            viewport.apply(true)
            act(delta)
            draw()
        }
    }
}
