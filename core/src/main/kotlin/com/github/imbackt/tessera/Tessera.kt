package com.github.imbackt.tessera

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.github.imbackt.tessera.Drawables.*
import com.github.imbackt.tessera.screen.LoadingScreen
import com.github.imbackt.tessera.screen.TesseraScreen
import ktx.app.KtxGame
import ktx.freetype.generateFont
import ktx.freetype.registerFreeTypeFontLoaders
import ktx.scene2d.Scene2DSkin
import ktx.style.*

class Tessera : KtxGame<TesseraScreen>() {
    val batch: Batch by lazy { SpriteBatch() }
    val viewport: Viewport = FitViewport(10f, 20f)
    val assetManager = AssetManager().apply { registerFreeTypeFontLoaders() }
    val stage: Stage by lazy { Stage(FitViewport(576f, 1024f), batch) }


    override fun create() {
        Scene2DSkin.defaultSkin = createSkin()
        Gdx.input.inputProcessor = stage

        addScreen(LoadingScreen(this))
        setScreen<LoadingScreen>()
        super.create()
    }

    private fun createSkin(): Skin {
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("ui/8-bit.ttf"))
        val defaultFont = fontGenerator.generateFont { size = 32 }
        val hugeFont = fontGenerator.generateFont { size = 64 }
        fontGenerator.dispose()

        return skin(TextureAtlas(Gdx.files.internal("ui/UI.atlas"))) {
            // label styles
            label {
                font = defaultFont
            }
            label(Labels.Dark()) {
                font = defaultFont
                fontColor = Color.BLACK
            }
            label(Labels.Huge()) {
                font = hugeFont
            }
            label(Labels.BrightBgd()) {
                font = defaultFont
                fontColor = Color.BLACK
                background = it[Btn]
            }
            // button styles
            textButton {
                font = defaultFont
                fontColor = Color.BLACK
                downFontColor = Color.WHITE
                up = it[Btn]
                down = it[Btn]
                disabled = it[BtnDark]
            }
            textButton(Buttons.Dark()) {
                font = defaultFont
                fontColor = Color.BLACK
                downFontColor = Color.WHITE
                up = it[BtnDark]
                down = it[BtnDark]
            }
            // image button styles
            imageButton(Buttons.Banner()) {
                imageUp = it[Banner]
                imageDown = it[Banner]
            }
            imageButton(Buttons.Music()) {
                imageUp = it[BtnMusicOn]
                imageDown = it[BtnMusicOn]
                imageChecked = it[BtnMusicOff]
            }
            imageButton(Buttons.Arrow()) {
                imageUp = it[BtnArrow]
                imageDown = it[BtnArrowPressed]
            }
            imageButton(Buttons.RotateLeft()) {
                imageUp = it[BtnRotateLeft]
                imageDown = it[BtnRotateLeftPressed]
            }
            imageButton(Buttons.RotateRight()) {
                imageUp = it[BtnRotateRight]
                imageDown = it[BtnRotateRightPressed]
            }
            // window styles
            window {
                titleFont = hugeFont
                background = it[Gutter]
            }
        }
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
        stage.dispose()
        super.dispose()
    }
}