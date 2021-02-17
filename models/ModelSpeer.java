// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelSpeer extends EntityModel<Entity> {
	private final ModelRenderer Stiel;
	private final ModelRenderer Stiel_r1;
	private final ModelRenderer Knauf;
	private final ModelRenderer Knauf_r1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r2_r2;
	private final ModelRenderer Schneide;
	private final ModelRenderer Schneide_r1;
	private final ModelRenderer bb_main;
	private final ModelRenderer bb_main_r1;

	public ModelSpeer() {
		textureWidth = 128;
		textureHeight = 128;

		Stiel = new ModelRenderer(this);
		Stiel.setRotationPoint(0.0F, 10.0F, 0.0F);

		Stiel_r1 = new ModelRenderer(this);
		Stiel_r1.setRotationPoint(0.0F, -18.0F, -9.0F);
		Stiel.addChild(Stiel_r1);
		setRotationAngle(Stiel_r1, 0.0F, 0.0F, -3.1416F);
		Stiel_r1.setTextureOffset(0, 0).addBox(-1.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
		Stiel_r1.setTextureOffset(4, 4).addBox(0.0F, -74.0F, 1.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
		Stiel_r1.setTextureOffset(8, 8).addBox(0.0F, -74.0F, -1.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
		Stiel_r1.setTextureOffset(12, 12).addBox(1.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
		Stiel_r1.setTextureOffset(16, 16).addBox(0.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);

		Knauf = new ModelRenderer(this);
		Knauf.setRotationPoint(0.0F, 7.0F, -16.0F);

		Knauf_r1 = new ModelRenderer(this);
		Knauf_r1.setRotationPoint(0.0F, -15.0F, 7.0F);
		Knauf.addChild(Knauf_r1);
		setRotationAngle(Knauf_r1, 0.0F, 0.0F, -3.1416F);
		Knauf_r1.setTextureOffset(33, 25).addBox(-1.0F, -75.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		Knauf_r1.setTextureOffset(43, 11).addBox(-1.0F, -78.0F, -2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		Knauf_r1.setTextureOffset(44, 4).addBox(-1.0F, -78.0F, 2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		Knauf_r1.setTextureOffset(34, 12).addBox(-1.0F, -79.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.0F, -76.0F, 6.0F);
		Knauf.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);

		cube_r2_r1 = new ModelRenderer(this);
		cube_r2_r1.setRotationPoint(-3.0F, 7.0F, 1.0F);
		cube_r1.addChild(cube_r2_r1);
		setRotationAngle(cube_r2_r1, 0.0F, 0.0F, -3.1416F);
		cube_r2_r1.setTextureOffset(27, 43).addBox(-6.0F, -132.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r2_r1.setTextureOffset(27, 43).addBox(-6.0F, -132.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-1.0F, -76.0F, 6.0F);
		Knauf.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);

		cube_r2_r2 = new ModelRenderer(this);
		cube_r2_r2.setRotationPoint(1.0F, 7.0F, 1.0F);
		cube_r2.addChild(cube_r2_r2);
		setRotationAngle(cube_r2_r2, 0.0F, 0.0F, -3.1416F);
		cube_r2_r2.setTextureOffset(42, 38).addBox(-1.0F, -132.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		Schneide = new ModelRenderer(this);
		Schneide.setRotationPoint(0.0F, 24.0F, 0.0F);

		Schneide_r1 = new ModelRenderer(this);
		Schneide_r1.setRotationPoint(0.0F, -32.0F, -9.0F);
		Schneide.addChild(Schneide_r1);
		setRotationAngle(Schneide_r1, 0.0F, 0.0F, -3.1416F);
		Schneide_r1.setTextureOffset(19, 13).addBox(-3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 7).addBox(3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(24, 0).addBox(-3.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 16).addBox(4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 16).addBox(3.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(23, 23).addBox(-3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 13).addBox(3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(23, 25).addBox(-2.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 9).addBox(2.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(35, 35).addBox(-2.0F, -9.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(36, 20).addBox(2.0F, -9.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 24).addBox(-2.0F, -8.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 28).addBox(2.0F, -8.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(34, 29).addBox(-2.0F, -6.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(38, 0).addBox(2.0F, -6.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 22).addBox(-2.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(27, 28).addBox(2.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(36, 16).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(29, 39).addBox(1.0F, -11.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(24, 2).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(29, 19).addBox(1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 7).addBox(-1.0F, -9.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(27, 13).addBox(1.0F, -9.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(27, 35).addBox(-1.0F, -8.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(37, 39).addBox(1.0F, -8.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(23, 21).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(29, 21).addBox(1.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(24, 1).addBox(-1.0F, -6.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 28).addBox(1.0F, -6.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 16).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(39, 6).addBox(1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 20).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(29, 25).addBox(1.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(12, 8).addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(39, 30).addBox(2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(19, 11).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 30).addBox(2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(17, 0).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(27, 29).addBox(1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(4, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 40).addBox(1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(17, 8).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(27, 30).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(8, 0).addBox(0.0F, -9.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(29, 19).addBox(0.0F, -11.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(40, 34).addBox(0.0F, -10.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(31, 0).addBox(0.0F, -12.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(31, 2).addBox(0.0F, -12.0F, 6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(32, 7).addBox(0.0F, -11.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(32, 9).addBox(0.0F, -11.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 34).addBox(0.0F, -10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(34, 16).addBox(0.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(32, 35).addBox(0.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 36).addBox(0.0F, -8.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(31, 0).addBox(0.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(41, 17).addBox(0.0F, -7.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(34, 39).addBox(0.0F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(32, 6).addBox(0.0F, -5.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(25, 40).addBox(0.0F, -7.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(16, 8).addBox(0.0F, -3.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 20).addBox(0.0F, -6.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(41, 21).addBox(0.0F, -4.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(42, 25).addBox(0.0F, -4.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(35, 43).addBox(0.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(20, 34).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(42, 42).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Schneide_r1.setTextureOffset(39, 43).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		bb_main_r1 = new ModelRenderer(this);
		bb_main_r1.setRotationPoint(0.0F, -32.0F, -9.0F);
		bb_main.addChild(bb_main_r1);
		setRotationAngle(bb_main_r1, 0.0F, 0.0F, -3.1416F);
		bb_main_r1.setTextureOffset(8, 4).addBox(5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main_r1.setTextureOffset(11, 3).addBox(4.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main_r1.setTextureOffset(16, 12).addBox(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main_r1.setTextureOffset(17, 0).addBox(-5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main_r1.setTextureOffset(17, 2).addBox(-4.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Stiel.render(matrixStack, buffer, packedLight, packedOverlay);
		Knauf.render(matrixStack, buffer, packedLight, packedOverlay);
		Schneide.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}