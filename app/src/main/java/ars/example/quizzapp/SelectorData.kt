package ars.example.quizzapp

object SelectorData {

    private val hashDifficultyLevels =hashMapOf("Easy" to "easy","Medium" to "medium", "Hard" to "hard")
    private val hashCategory = hashMapOf<String,String>("Music" to "music","Sport and leisure" to "sport_and_leisure" ,
        "Film and Tv" to "film_and_tv", "Arts and Literature" to "arts_and_literature" ,"History" to "history" , "Society and Culture" to "society_and_culture" ,"Science" to "science"
        ,"Geography" to "geography", "Food and Drink" to "food_and_drink" ,"General Knowledge" to "general_knowledge")

    fun getHashCategories()= hashCategory
    fun getHashDifficultyLevels() = hashDifficultyLevels
}