package ro.enoor.rpg.world;

import java.util.Random;

import ro.enoor.rpg.entity.Player;
import ro.enoor.rpg.level.Level;
import ro.enoor.rpg.level.tile.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {
	private World boxWorld;
	private Level level;
	private Player player;
	private Random random;
	
	public GameWorld() {
		level = new Level(this, 50, 50);
		boxWorld = new World(Vector2.Zero, false);
		
		random = new Random();
		int x, y;
		do {
			x = random.nextInt(level.getWidth());
			y = random.nextInt(level.getHeight());
		} while(Tile.isSolid(level.map[y][x]));
		player = new Player(level, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);

		level.initLevel();
	}
	
	public void update() {
		float delta = Gdx.graphics.getDeltaTime() / 0.016f;
		
		input();

		player.update(delta);
		level.update(delta);
	}
	
	public void input() {
		Input input = Gdx.input;

		if(input.isKeyPressed(Keys.W)) player.move(2);
		else if(input.isKeyPressed(Keys.S)) player.move(0);
		else if(input.isKeyPressed(Keys.A)) player.move(1);
		else if(input.isKeyPressed(Keys.D)) player.move(3);
		
		if(input.isKeyPressed(Keys.CONTROL_RIGHT)) player.attack();
		if(input.isKeyPressed(Keys.SHIFT_RIGHT)) player.setSpeed(Player.R_SPEED);
		else player.setSpeed(Player.N_SPEED);
	}
	
	public Level getLevel() { return level; }
	public Player getPlayer() { return player; }
	public World getBoxWorld() { return boxWorld; }
}
