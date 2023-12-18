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
   // String flag;
    String Diabetes_Eliminated;
    String Diabetes_Add;
    String Hypothyroidism_Eliminated;
    String Hypothyroidism_Add;
    String Hypertension_Eliminated;
    String Hypertension_Add;
    String PCOS_Eliminated;
    String PCOS_Add;
    String Allergies;
	//Recipe ID,Recipe Name,Recipe Category,Food Category,Ingredients,Preparation Time,Cooking Time,
		//Preparation method,Nutrient values,Morbid condition
		
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
	
	public String getDiabetes_Eliminated() {
		return Diabetes_Eliminated;
	}
	public void setDiabetes_Eliminated(String diabetes_Eliminated) {
		Diabetes_Eliminated = diabetes_Eliminated;
	}
	public String getDiabetes_Add() {
		return Diabetes_Add;
	}
	public void setDiabetes_Add(String diabetes_Add) {
		Diabetes_Add = diabetes_Add;
	}
	public String getHypothyroidism_Eliminated() {
		return Hypothyroidism_Eliminated;
	}
	public void setHypothyroidism_Eliminated(String hypothyroidism_Eliminated) {
		Hypothyroidism_Eliminated = hypothyroidism_Eliminated;
	}
	public String getHypothyroidism_Add() {
		return Hypothyroidism_Add;
	}
	public void setHypothyroidism_Add(String hypothyroidism_Add) {
		Hypothyroidism_Add = hypothyroidism_Add;
	}
	public String getHypertension_Eliminated() {
		return Hypertension_Eliminated;
	}
	public void setHypertension_Eliminated(String hypertension_Eliminated) {
		Hypertension_Eliminated = hypertension_Eliminated;
	}
	public String getHypertension_Add() {
		return Hypertension_Add;
	}
	public void setHypertension_Add(String hypertension_Add) {
		Hypertension_Add = hypertension_Add;
	}
	public String getPCOS_Eliminated() {
		return PCOS_Eliminated;
	}
	public void setPCOS_Eliminated(String pCOS_Eliminated) {
		PCOS_Eliminated = pCOS_Eliminated;
	}
	public String getPCOS_Add() {
		return PCOS_Add;
	}
	public void setPCOS_Add(String pCOS_Add) {
		PCOS_Add = pCOS_Add;
	}
	public String getAllergies() {
		return Allergies;
	}
	public void setAllergies(String allergies) {
		Allergies = allergies;
	}
	
}
