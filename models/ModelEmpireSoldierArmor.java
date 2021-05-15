// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelEmpireSoldierArmor extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer cube_r1_r1;
	private final ModelRenderer head_r1;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightleg;
	private final ModelRenderer chest;
	private final ModelRenderer cube_r2;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightboot;
	private final ModelRenderer leftboot;

	public ModelEmpireSoldierArmor() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(17, 20).addBox(-4.4F, -7.0F, -3.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(17, 20).addBox(3.3F, -7.0F, -3.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(18, 21).addBox(-4.6F, -6.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(17, 21).addBox(3.5F, -6.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(18, 22).addBox(-3.0F, -7.0F, 3.3F, 6.0F, 6.0F, 1.0F, 0.0F, false);

		cube_r1_r1 = new ModelRenderer(this);
		cube_r1_r1.setRotationPoint(-1.0F, -4.0F, 7.0F);
		head.addChild(cube_r1_r1);
		setRotationAngle(cube_r1_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1_r1.setTextureOffset(17, 22).addBox(2.5F, -2.0F, -1.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, -8.5F, -2.4F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 0.0F, 1.5708F);
		head_r1.setTextureOffset(8, 0).addBox(-0.4F, -2.5F, -1.2F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		head_r1.setTextureOffset(8, 0).addBox(-0.4F, 1.5F, -1.2F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		head_r1.setTextureOffset(8, 0).addBox(0.1F, -1.5F, -0.8F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		head_r1.setTextureOffset(8, 0).addBox(0.1F, 0.5F, -0.8F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		head_r1.setTextureOffset(8, 0).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 7.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightarm.setTextureOffset(16, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		rightarm.setTextureOffset(58, 17).addBox(-4.0F, -3.0F, -2.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);
		rightarm.setTextureOffset(62, 17).addBox(-2.0F, -3.4F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		rightleg.setTextureOffset(17, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		chest = new ModelRenderer(this);
		chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest.setTextureOffset(24, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		chest.setTextureOffset(50, 53).addBox(-3.0F, 1.0F, -2.3F, 6.0F, 10.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 6.0F, 1.8F);
		chest.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 3.1416F, 0.0F);
		cube_r2.setTextureOffset(50, 53).addBox(-3.0F, -5.0F, -0.5F, 6.0F, 10.0F, 1.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftarm.setTextureOffset(18, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		leftarm.setTextureOffset(58, 17).addBox(-1.0F, -3.0F, -2.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);
		leftarm.setTextureOffset(62, 17).addBox(-1.0F, -3.4F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftleg.setTextureOffset(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightboot = new ModelRenderer(this);
		rightboot.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightboot.setTextureOffset(21, 66).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		leftboot = new ModelRenderer(this);
		leftboot.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftboot.setTextureOffset(0, 65).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
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
		rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		chest.render(matrixStack, buffer, packedLight, packedOverlay);
		leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightboot.render(matrixStack, buffer, packedLight, packedOverlay);
		leftboot.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}