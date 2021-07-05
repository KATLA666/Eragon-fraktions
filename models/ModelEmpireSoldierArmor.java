// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelEmpireSoldierArmor extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer cube_r1_r1;
	private final ModelRenderer head_r1;
	private final ModelRenderer rightarm;
	private final ModelRenderer Saum;
	private final ModelRenderer cube_r1;
	private final ModelRenderer rightleg;
	private final ModelRenderer chest;
	private final ModelRenderer cube_r2_r1;
	private final ModelRenderer leftarm;
	private final ModelRenderer Saum2;
	private final ModelRenderer cube_r2;
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
		rightarm.setTextureOffset(0, 76).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		rightarm.setTextureOffset(58, 17).addBox(-4.0F, -3.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
		rightarm.setTextureOffset(62, 14).addBox(-2.0F, -3.7F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		rightarm.setTextureOffset(62, 17).addBox(-3.0F, -3.3F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		Saum = new ModelRenderer(this);
		Saum.setRotationPoint(-7.0F, 0.0F, 0.0F);
		rightarm.addChild(Saum);
		Saum.setTextureOffset(18, 90).addBox(4.0F, 6.0F, -2.2F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Saum.setTextureOffset(18, 90).addBox(4.0F, 6.0F, 1.2F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Saum.setTextureOffset(19, 90).addBox(7.2F, 6.0F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum.setTextureOffset(19, 90).addBox(7.2F, 6.0F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum.setTextureOffset(19, 90).addBox(3.8F, 6.0F, 1.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum.setTextureOffset(19, 90).addBox(3.8F, 6.0F, -2.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(10.0F, 6.5F, 1.7F);
		Saum.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.setTextureOffset(18, 90).addBox(-0.3F, -0.5F, -6.2F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r1.setTextureOffset(18, 90).addBox(-0.3F, -0.5F, -2.8F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		rightleg.setTextureOffset(0, 99).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		chest = new ModelRenderer(this);
		chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest.setTextureOffset(24, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		chest.setTextureOffset(50, 53).addBox(-3.0F, 1.0F, -2.3F, 6.0F, 10.0F, 1.0F, 0.0F, false);

		cube_r2_r1 = new ModelRenderer(this);
		cube_r2_r1.setRotationPoint(0.0F, 6.0F, 1.8F);
		chest.addChild(cube_r2_r1);
		setRotationAngle(cube_r2_r1, 0.0F, 3.1416F, 0.0F);
		cube_r2_r1.setTextureOffset(50, 53).addBox(-3.0F, -5.0F, -0.5F, 6.0F, 10.0F, 1.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftarm.setTextureOffset(0, 76).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		leftarm.setTextureOffset(58, 17).addBox(-1.0F, -3.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
		leftarm.setTextureOffset(62, 14).addBox(-1.0F, -3.7F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		leftarm.setTextureOffset(62, 17).addBox(-1.0F, -3.3F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		Saum2 = new ModelRenderer(this);
		Saum2.setRotationPoint(1.0444F, 8.2222F, -0.5111F);
		leftarm.addChild(Saum2);
		Saum2.setTextureOffset(18, 90).addBox(-2.0444F, -2.2222F, -1.6889F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Saum2.setTextureOffset(18, 90).addBox(-2.0444F, -2.2222F, 1.7111F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Saum2.setTextureOffset(19, 90).addBox(1.1556F, -2.2222F, -1.6889F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum2.setTextureOffset(19, 90).addBox(1.1556F, -2.2222F, 1.7111F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum2.setTextureOffset(19, 90).addBox(-2.2444F, -2.2222F, 1.7111F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Saum2.setTextureOffset(19, 90).addBox(-2.2444F, -2.2222F, -1.6889F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(3.9556F, -1.7222F, 2.2111F);
		Saum2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
		cube_r2.setTextureOffset(18, 90).addBox(-0.3F, -0.5F, -6.2F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r2.setTextureOffset(18, 90).addBox(-0.3F, -0.5F, -2.8F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftleg.setTextureOffset(0, 99).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightboot = new ModelRenderer(this);
		rightboot.setRotationPoint(-5.0F, 24.0F, 0.0F);
		rightboot.setTextureOffset(21, 66).addBox(3.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

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