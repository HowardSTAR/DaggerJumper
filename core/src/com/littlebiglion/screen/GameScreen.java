package com.littlebiglion.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.littlebiglion.base.BaseScreen;
import com.littlebiglion.sprite.AnimatePlayer;
import com.littlebiglion.sprite.BackGround;
import com.littlebiglion.sprite.Dagger;
import com.littlebiglion.sprite.DaggerBack;
import com.littlebiglion.sprite.Dimond;
import com.littlebiglion.sprite.Fire;
import com.littlebiglion.sprite.Heart;
import com.littlebiglion.sprite.HeartOff;
import com.littlebiglion.sprite.Player;


public class GameScreen extends BaseScreen {

    private TextureAtlas atlas, atlasfire, atlasPlayer, playerJump;
    private float elapsedTime = 0f;

    public static int hp = 3;

    private BackGround bg;
    private Game game;
    private Player player;
    private AnimatePlayer anime;
    private Dagger dagger;
    private DaggerBack daggerBack;
    private Heart[] hearts;
    private HeartOff[] heartOff;
    private Dimond dimond;

    public static float score = 0; // количество очков
    public static float scoreMul = 1 ; // коэф увеличения очков
    public static int delX = 0;
    public static int s = 0;

    private Fire fire;

    public GameScreen(Game game) {
        this.game = game;
    }

    public GameScreen() {

    }

    @Override
    public void show() {
        super.show();

        atlas = new TextureAtlas("spriteAll/mySprite.atlas");
        playerJump = new TextureAtlas("playerAll/myPlayer.atlas");
        atlasPlayer = new TextureAtlas(Gdx.files.internal("takeAll/myTake.atlas"));
        atlasfire = new TextureAtlas(Gdx.files.internal("spriteFire/myFire.atlas"));

        bg = new BackGround(atlas);

        dagger = new Dagger(atlas, game);
        daggerBack = new DaggerBack(atlas, game);

        hearts = new Heart[]{
                    new Heart(atlas),
                    new Heart(atlas),
                    new Heart(atlas)
        };
        heartOff = new HeartOff[]{
                    new HeartOff(atlas),
                    new HeartOff(atlas),
                    new HeartOff(atlas)
        };


        dimond = new Dimond(atlas);

        fire = new Fire(atlasfire);

        anime = new AnimatePlayer(atlasPlayer, game);

        player = new Player(atlas, game);

        delX = 0;
        Player.y = 7;
        scoreMul = 1;
        score = 0;
        s = 0;

    }

    /**
     *
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;

    }

    /**
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        camera.update();

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch);

        fire.showBatsh(batch); // DONT TOUCH!!!!!! THIS IS MAGIC!!!!11!

       // dagger.draw(batch);
        player.draw(batch);
      //  anime.draw(batch);
        daggerBack.draw(batch);

        dimond.draw(batch);

        Hearts();

        font.draw(batch, "SCORE:  " + (int)score, 450, 100);

        music.stop();

        batch.end();
    }


    public void Hearts(){
        int x = 0;
        switch (hp){
            case 3:
                for(Heart heart : hearts){
                    heart.draw(batch, 430 + x);
                    x += 70;
                }

                break;

            case 2:
                for (int i = 0; i < 2; i++){
                    hearts[i].draw(batch, 430 + x);
                    x += 70;
                }
                heartOff[2].draw(batch, 430 + x);
                break;

            case 1:
                hearts[0].draw(batch, 430 + x);

                for(int i = 0; i < 2; i++) {
                    heartOff[i].draw(batch, 490 + x);
                    x+=70;
                }
                break;
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        fire.dispose();
        player.dispose();
        dagger.dispose();
        daggerBack.dispose();
        hearts[0].dispose();
        hearts[1].dispose();
        hearts[2].dispose();
        heartOff[0].dispose();
        heartOff[1].dispose();
        heartOff[2].dispose();
        batch.dispose();
        atlasPlayer.dispose();
        atlas.dispose();
        anime.dispose();
        dimond.dispose();
    }

}
