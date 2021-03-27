// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelHelmetES extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer left_shoe;
	private final ModelRenderer right_shoe;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer waist;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer bb_main;

	public ModelHelmetES() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(32, 32).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.75F, false);
		head.setTextureOffset(28, 0).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, 1.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 7.0F, 0.0F);

		left_shoe = new ModelRenderer(this);
		left_shoe.setRotationPoint(0.0F, 24.0F, 0.0F);

		right_shoe = new ModelRenderer(this);
		right_shoe.setRotationPoint(-4.0F, 24.0F, 0.0F);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(12.0F, 24.0F, 0.0F);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-12.0F, 24.0F, 0.0F);

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, 7.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, 24.0F, 0.0F);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 24.0F, 0.0F);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(14, 14).addBox(-6.0F, -31.0F, -6.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
		bb_main.setTextureOffset(14, 0).addBox(-2.0F, -33.0F, -3.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(5.0F, -31.0F, -6.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		left_shoe.render(matrixStack, buffer, packedLight, packedOverlay);
		right_shoe.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		waist.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}