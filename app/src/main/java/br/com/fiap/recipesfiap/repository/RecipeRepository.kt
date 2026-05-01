package br.com.fiap.recipesfiap.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.fiap.recipesfiap.factory.RetrofitClient
import br.com.fiap.recipesfiap.model.Category
import br.com.fiap.recipesfiap.model.DifficultLevel
import br.com.fiap.recipesfiap.model.Ingredient
import br.com.fiap.recipesfiap.model.PreparationMethod
import br.com.fiap.recipesfiap.model.Recipe
import br.com.fiap.recipesfiap.model.RecipeRequest
import br.com.fiap.recipesfiap.model.User
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import java.time.LocalDate

@Composable
fun getLatestRecipes(): List<Recipe> {
    var latestRecipes by remember {
        mutableStateOf(listOf<Recipe>())
    }

    val callLatestRecipes = RetrofitClient.getRecipeService().getLatestRecipes()

    callLatestRecipes.enqueue(object : Callback<List<Recipe>> {
        override fun onResponse(
            p0: Call<List<Recipe>?>,
            response: Response<List<Recipe>?>
        ) {
            latestRecipes = response.body() ?: emptyList()
        }

        override fun onFailure(
            p0: Call<List<Recipe>?>,
            p1: Throwable
        ) {
            println(p1.message)
        }

    })
    return latestRecipes
}

// TRECHO DE CÓDIGO OMITIDO
suspend fun saveRecipe(recipeRequest: RecipeRequest): RecipeRequest {
    val newRecipe = RetrofitClient.getRecipeService().saveRecipe(recipeRequest)
    return newRecipe
}

suspend fun saveRecipeIngredients(
    recipeId: Int,
    ingredients: List<Ingredient>
): List<Ingredient> {
    println("Estou aqui...")
    println(recipeId)
    println(Gson().toJson(ingredients))
    val newIngredients = RetrofitClient
        .getRecipeService()
        .saveRecipeIngredients(
            recipeId = recipeId,
            ingredients = ingredients
        )
    return newIngredients
}

suspend fun savePreparationMethods(
    recipeId: Int,
    preparationMethods: List<PreparationMethod>
): List<PreparationMethod> {
    val newPreparationMethods = RetrofitClient
        .getRecipeService()
        .savePreparationMethods(
            recipeId = recipeId,
            preparationMethods = preparationMethods
        )
    return newPreparationMethods
}

suspend fun uploadImage(recipeId: Int, file: File){
    val image = MultipartBody.Part
        .createFormData(
            name = "file",
            filename = file.name,
            body = file.asRequestBody()
        )

    RetrofitClient.getRecipeService().uploadImage(recipeId, image)

}

// TRECHO DE CÓDIGO OMITIDO

fun getAllRecipes() = listOf<Recipe>(
    Recipe(
        id = 1,
        category = Category(id = 6000, name = "Desserts"),
        user = User(id = 100, name = "Ana Maria"),
        difficultLevel = DifficultLevel.BEGINNER,
        name = "Bolo de Cenoura",
        description = "Moist, spiced, carrot-filled cake with tangy cream cheese frosting.",
        cookingTime = 60,
        createdAt = LocalDate.now(),
        image = "/images/bolo_de_cenoura.jpg"
    ),
    Recipe(
        id = 2,
        category = Category(id = 5000, name = "Vegetables"),
        user = User(id = 200, name = "Pedro Augusto"),
        difficultLevel = DifficultLevel.INTERMEDIATE,
        name = "Salada de Palmito",
        description = "Refreshing heart of palm salad, light, savory, and subtly sweet.",
        cookingTime = 10,
        createdAt = LocalDate.now(),
        image = "/images/salada_de_palmito.png"
    ),
    Recipe(
        id = 3,
        category = Category(id = 4000, name = "Bakery"),
        user = User(id = 300, name = "Patricia Oliveira"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Pão de calabresa",
        description = "Spicy sausage and cheese bread: soft, savory, delicious.",
        cookingTime = 10,
        createdAt = LocalDate.now(),
        image = "/images/pao_calabresa.jpeg"
    ),
    Recipe(
        id = 4,
        category = Category(id = 5000, name = "Vegetables"),
        user = User(id = 400, name = "Mariana Dias"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Sopa de Legumes",
        description = "Hearty vegetable soup: warm, nourishing, fresh, wholesome goodness.",
        cookingTime = 45,
        createdAt = LocalDate.now(),
        image = "/images/sopa_legumes.jpg"
    ),
    Recipe(
        id = 5,
        category = Category(id = 2000, name = "Beef"),
        user = User(id = 500, name = "Carlos Almeida"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Feijoada",
        description = "Rich, smoky, hearty, bean and meat stew.",
        cookingTime = 120,
        createdAt = LocalDate.now(),
        image = "/images/feijoada.jpg"
    )
)

@Composable
fun getRecipesByCategory(id: Int): List<Recipe> {
    var recipes by remember {
        mutableStateOf(listOf<Recipe>())
    }

    println("-----> $id")
    val callRecipesByCategory = RetrofitClient.getRecipeService().getRecipesByCategoryId(id)

    callRecipesByCategory.enqueue(object : Callback<List<Recipe>> {
        override fun onResponse(
            p0: Call<List<Recipe>?>,
            response: Response<List<Recipe>?>
        ) {
            recipes = response.body() ?: emptyList()
        }

        override fun onFailure(
            p0: Call<List<Recipe>?>,
            p1: Throwable
        ) {
            println(p1.message)
        }

    })
    return recipes
}

//fun getRecipeById(id: Int) = getAllRecipes()
//    .filter {
//        it.id == id
//    }




















