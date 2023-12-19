package com.TarlaDalal.model;

public class Recipes {
	String recipeID;
	String recipeName;
	String recipeCategory;
	String foodCategory;
	String ingredients;
	String prepTime;
	String cookTime;
	String method;
	String nutrient;
	String morbid;
	String url;
	//String Flag;

	public String getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}

	public Recipes() {
		super();
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getRecipeCategory() {
		return recipeCategory;
	}

	public void setRecipeCategory(String recipeCategory) {
		this.recipeCategory = recipeCategory;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getNutrient() {
		return nutrient;
	}

	public void setNutrient(String nutrient) {
		this.nutrient = nutrient;
	}

	public String getMorbid() {
		return morbid;
	}

	public void setMorbid(String morbid) {
		this.morbid = morbid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/*public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	} */




}
