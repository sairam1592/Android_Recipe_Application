package com.example.myapplication.arunproject.domain.model

import com.example.myapplication.arunproject.data.model.Recipe

data class RecipeDummyModel(
    val recipes: List<Recipe>
) {
    companion object {
        val dummyData = RecipeDummyModel(
            recipes = listOf(
                Recipe(
                    calories = "516 kcal",
                    carbos = "47 g",
                    description = "Enjoy a delicious dinner with crispy fish goujons, sweet potato wedges, and minted snap peas.",
                    difficulty = 0,
                    fats = "8 g",
                    headline = "with Sweet Potato Wedges and Minted Snap Peas",
                    id = "533143aaff604d567f8b4571",
                    image = "https://via.placeholder.com/500x300",
                    name = "Crispy Fish Goujons",
                    proteins = "43 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT35M"
                ),
                Recipe(
                    calories = "450 kcal",
                    carbos = "40 g",
                    description = "Savor the classic combination of grilled chicken with a tangy lemon herb dressing.",
                    difficulty = 1,
                    fats = "10 g",
                    headline = "with Lemon Herb Dressing",
                    id = "533143aaff604d567f8b4572",
                    image = "https://via.placeholder.com/500x300",
                    name = "Grilled Chicken Salad",
                    proteins = "35 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT25M"
                ),
                Recipe(
                    calories = "380 kcal",
                    carbos = "35 g",
                    description = "Indulge in a rich and creamy mushroom risotto, perfect for a cozy night in.",
                    difficulty = 2,
                    fats = "12 g",
                    headline = "with Parmesan Cheese",
                    id = "533143aaff604d567f8b4573",
                    image = "https://via.placeholder.com/500x300",
                    name = "Mushroom Risotto",
                    proteins = "15 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT45M"
                ),
                Recipe(
                    calories = "500 kcal",
                    carbos = "55 g",
                    description = "Experience the vibrant flavors of a classic Mexican taco salad with a spicy twist.",
                    difficulty = 1,
                    fats = "18 g",
                    headline = "with Spicy Dressing",
                    id = "533143aaff604d567f8b4574",
                    image = "https://via.placeholder.com/500x300",
                    name = "Mexican Taco Salad",
                    proteins = "25 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT30M"
                ),
                Recipe(
                    calories = "420 kcal",
                    carbos = "45 g",
                    description = "Delight in the simplicity of a classic Margherita pizza with fresh basil and mozzarella.",
                    difficulty = 0,
                    fats = "15 g",
                    headline = "with Fresh Basil",
                    id = "533143aaff604d567f8b4575",
                    image = "https://via.placeholder.com/500x300",
                    name = "Margherita Pizza",
                    proteins = "20 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT20M"
                ),
                Recipe(
                    calories = "600 kcal",
                    carbos = "60 g",
                    description = "Satisfy your sweet tooth with a decadent chocolate lava cake oozing with molten chocolate.",
                    difficulty = 3,
                    fats = "25 g",
                    headline = "with Molten Chocolate",
                    id = "533143aaff604d567f8b4576",
                    image = "https://via.placeholder.com/500x300",
                    name = "Chocolate Lava Cake",
                    proteins = "8 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT50M"
                ),
                Recipe(
                    calories = "310 kcal",
                    carbos = "30 g",
                    description = "Refresh your palate with a light and zesty lemon sorbet, perfect for a summer day.",
                    difficulty = 1,
                    fats = "0 g",
                    headline = "with Fresh Lemon Zest",
                    id = "533143aaff604d567f8b4577",
                    image = "https://via.placeholder.com/500x300",
                    name = "Lemon Sorbet",
                    proteins = "2 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT15M"
                ),
                Recipe(
                    calories = "550 kcal",
                    carbos = "50 g",
                    description = "Indulge in a succulent beef burger topped with melted cheese and crispy bacon.",
                    difficulty = 2,
                    fats = "30 g",
                    headline = "with Bacon and Cheese",
                    id = "533143aaff604d567f8b4578",
                    image = "https://via.placeholder.com/500x300",
                    name = "Beef Burger",
                    proteins = "35 g",
                    thumb = "https://via.placeholder.com/150x150",
                    time = "PT40M"
                )
            )
        )
    }
}