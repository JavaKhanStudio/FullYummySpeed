package jks.yummy.game.action;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import jks.tools2d.parallax.TextureRegionParallaxLayer;

public class Index_BuildBackGround 
{
	public static List<TextureRegionParallaxLayer> createLayers_Day(String atlasPath,float worldWidth, float worldHeight, float cloudSpeed) 
	{
		TextureAtlas atlas = new TextureAtlas(atlasPath);

		TextureRegion cloudsRegion = atlas.findRegion("Clouds");
		TextureRegionParallaxLayer cloudsLayerA = new TextureRegionParallaxLayer(cloudsRegion, worldWidth, new Vector2(.01f,.01f), true);
		cloudsLayerA.setPadBottom(worldHeight*.53f);
		cloudsLayerA.setSpeed(cloudSpeed);
		
		Array<AtlasRegion> mountainsRegion = atlas.findRegions("Mountains");
		
		TextureRegionParallaxLayer mountainsLayerA = new TextureRegionParallaxLayer(mountainsRegion.get(0), worldWidth, new Vector2(.01f,.01f), true);
		mountainsLayerA.setPadBottom(worldHeight*.38f);
		TextureRegionParallaxLayer mountainsLayerB = new TextureRegionParallaxLayer(mountainsRegion.get(1), worldWidth, new Vector2(.02f,.02f), true);
		mountainsLayerB.setPadBottom(worldHeight*.35f);
		
        Array<AtlasRegion> treesRegion = atlas.findRegions("Trees");
		TextureRegionParallaxLayer treesLayerA = new TextureRegionParallaxLayer(treesRegion.get(0), worldWidth*.7275f, new Vector2(.05f,.05f), true);
		treesLayerA.setPadBottom(worldHeight*.38f);
		TextureRegionParallaxLayer treesLayerB = new TextureRegionParallaxLayer(treesRegion.get(1), worldWidth*.7275f, new Vector2(.08f,.08f), true);
		treesLayerB.setPadBottom(worldHeight*.30f);
		TextureRegionParallaxLayer treesLayerC = new TextureRegionParallaxLayer(treesRegion.get(2), worldWidth*.7275f, new Vector2(.1f,.1f), true);
		treesLayerC.setPadBottom(worldHeight*.26f);
		
		
        TextureAtlas atlas2 = new TextureAtlas("day/Green.atlas");
		
		TextureRegion roadRegion = atlas2.findRegion("Green_Road");
		TextureRegionParallaxLayer roadLayer = new TextureRegionParallaxLayer(roadRegion, worldWidth, new Vector2(.4f,.4f), true);
		
		TextureRegion roadBackRegionA = atlas2.findRegions("Green_Grass").first();
		TextureRegionParallaxLayer roadBackLayerA = new TextureRegionParallaxLayer(roadBackRegionA, worldWidth, new Vector2(.3f,.3f), true);
		roadBackLayerA.setPadBottom(worldHeight*.16f);
		
		TextureRegion roadBackRegionB = atlas2.findRegion("Green_Back");
		TextureRegionParallaxLayer roadBackLayerB = new TextureRegionParallaxLayer(roadBackRegionB, worldWidth, new Vector2(.35f,.35f), true);
		roadBackLayerB.setPadBottom(worldHeight*.15f);
		
		
		return Arrays.asList(cloudsLayerA,mountainsLayerA,mountainsLayerB,treesLayerA,treesLayerB,treesLayerC,roadBackLayerA,roadBackLayerB,roadLayer) ;
	}
}
