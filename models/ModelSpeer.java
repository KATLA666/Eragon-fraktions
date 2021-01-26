// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelSpeer extends EntityModel {
	private final ModelRenderer Stiel;
	private final ModelRenderer Knauf;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Schneide;
	private final ModelRenderer bb_main;

	public ModelSpeer() {
		textureWidth = 128;
		textureHeight = 128;

		Stiel = new ModelRenderer(this);
		Stiel.setRotationPoint(0.0F, 10.0F, 0.0F);
		Stiel.cubeList.add(new ModelBox(Stiel, 16, 16, 0.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));
		Stiel.cubeList.add(new ModelBox(Stiel, 12, 12, 1.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));
		Stiel.cubeList.add(new ModelBox(Stiel, 8, 8, 0.0F, -78.0F, -10.0F, 1, 63, 1, 0.0F, false));
		Stiel.cubeList.add(new ModelBox(Stiel, 4, 4, 0.0F, -78.0F, -8.0F, 1, 63, 1, 0.0F, false));
		Stiel.cubeList.add(new ModelBox(Stiel, 0, 0, -1.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));

		Knauf = new ModelRenderer(this);
		Knauf.setRotationPoint(0.0F, 7.0F, -16.0F);
		Knauf.cubeList.add(new ModelBox(Knauf, 34, 12, -1.0F, -80.0F, 6.0F, 3, 1, 3, 0.0F, false));
		Knauf.cubeList.add(new ModelBox(Knauf, 44, 4, -1.0F, -79.0F, 9.0F, 3, 3, 1, 0.0F, false));
		Knauf.cubeList.add(new ModelBox(Knauf, 43, 11, -1.0F, -79.0F, 5.0F, 3, 3, 1, 0.0F, false));
		Knauf.cubeList.add(new ModelBox(Knauf, 33, 25, -1.0F, -76.0F, 6.0F, 3, 1, 3, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.0F, -76.0F, 6.0F);
		Knauf.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 27, 43, 0.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-1.0F, -76.0F, 6.0F);
		Knauf.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 42, 38, 0.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F, false));

		Schneide = new ModelRenderer(this);
		Schneide.setRotationPoint(0.0F, 24.0F, 0.0F);
		Schneide.cubeList.add(new ModelBox(Schneide, 39, 43, 0.0F, -18.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 42, 42, 0.0F, -19.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 34, 0.0F, -20.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 35, 43, 0.0F, -22.0F, -13.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 42, 25, 0.0F, -22.0F, -5.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 41, 21, 0.0F, -22.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 20, 0.0F, -24.0F, -12.0F, 1, 1, 7, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 16, 8, 0.0F, -21.0F, -12.0F, 1, 1, 7, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 40, 0.0F, -25.0F, -5.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 32, 6, 0.0F, -23.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 34, 39, 0.0F, -25.0F, -13.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 41, 17, 0.0F, -25.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 31, 0, 0.0F, -26.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 36, 0.0F, -26.0F, -4.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 32, 35, 0.0F, -26.0F, -14.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 34, 16, 0.0F, -28.0F, -5.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 34, 0.0F, -28.0F, -13.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 32, 9, 0.0F, -29.0F, -4.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 32, 7, 0.0F, -29.0F, -14.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 31, 2, 0.0F, -30.0F, -3.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 31, 0, 0.0F, -30.0F, -15.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 40, 34, 0.0F, -28.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 29, 19, 0.0F, -29.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 8, 0, 0.0F, -27.0F, -12.0F, 1, 1, 7, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 27, 30, 1.0F, -19.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 17, 8, -1.0F, -19.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 40, 1.0F, -20.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 4, 0, -1.0F, -20.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 27, 29, 1.0F, -21.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 17, 0, -1.0F, -21.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 30, 2.0F, -20.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 19, 11, -2.0F, -20.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 39, 30, 2.0F, -21.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 12, 8, -2.0F, -21.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 29, 25, 1.0F, -22.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 20, -1.0F, -22.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 39, 6, 1.0F, -23.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 16, -1.0F, -23.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 28, 1.0F, -24.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 24, 1, -1.0F, -24.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 29, 21, 1.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 23, 21, -1.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 37, 39, 1.0F, -26.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 27, 35, -1.0F, -26.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 27, 13, 1.0F, -27.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 7, -1.0F, -27.0F, -11.0F, 1, 1, 5, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 29, 19, 1.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 24, 2, -1.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 29, 39, 1.0F, -29.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 36, 16, -1.0F, -29.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 27, 28, 2.0F, -23.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 22, -2.0F, -23.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 38, 0, 2.0F, -24.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 34, 29, -2.0F, -24.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 28, 2.0F, -26.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 20, 24, -2.0F, -26.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 36, 20, 2.0F, -27.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 35, 35, -2.0F, -27.0F, -10.0F, 1, 1, 3, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 9, 2.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 23, 25, -2.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 13, 3.0F, -24.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 23, 23, -3.0F, -24.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 16, 3.0F, -27.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 16, 4.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 24, 0, -3.0F, -27.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 25, 7, 3.0F, -21.0F, -9.0F, 1, 1, 1, 0.0F, false));
		Schneide.cubeList.add(new ModelBox(Schneide, 19, 13, -3.0F, -21.0F, -9.0F, 1, 1, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 17, 2, -4.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 17, 0, -5.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 16, 12, -4.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 11, 3, 4.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 4, 5.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Stiel.render(f5);
		Knauf.render(f5);
		Schneide.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}