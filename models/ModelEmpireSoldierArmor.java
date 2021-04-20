// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelEmpireSoldierArmor extends EntityModel<Entity> {
	private final ModelRenderer field_178736_x;
	private final ModelRenderer field_178723_h;
	private final ModelRenderer field_178721_j;
	private final ModelRenderer field_78116_c;
	private final ModelRenderer field_78115_e;
	private final ModelRenderer field_178724_i;
	private final ModelRenderer field_178722_k;
	private final ModelRenderer head;

	public ModelEmpireSoldierArmor() {
		textureWidth = 128;
		textureHeight = 128;

		field_178736_x = new ModelRenderer(this);
		field_178736_x.setRotationPoint(0.0F, 0.0F, 0.0F);
		field_178736_x.setTextureOffset(24, 0).addBox(-3.0F, 18.0F, -1.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);

		field_178723_h = new ModelRenderer(this);
		field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);

		field_178721_j = new ModelRenderer(this);
		field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.0F);

		field_78116_c = new ModelRenderer(this);
		field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);

		field_78115_e = new ModelRenderer(this);
		field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);

		field_178724_i = new ModelRenderer(this);
		field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);

		field_178722_k = new ModelRenderer(this);
		field_178722_k.setRotationPoint(1.9F, 12.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(75, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		field_178736_x.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178723_h.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178721_j.render(matrixStack, buffer, packedLight, packedOverlay);
		field_78116_c.render(matrixStack, buffer, packedLight, packedOverlay);
		field_78115_e.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178724_i.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178722_k.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}