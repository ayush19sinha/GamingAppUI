package my.android.gamingappui.data

import my.android.gamingappui.R

data class Game(val title: String,
                val img: Int,
                val smallTitle: String? =null,
                val subTitle: String? = null,
                val desc: String? = null,
                val gameDetailImg: Int? = null,
                val gameId: Int)



val gameList = listOf(
    Game("Horizon Forbidden West",
        R.drawable.game1_new,
        "Horizon",
        "Forbidden West",
        "Join Aloy as she braves the Forbidden West – a " +
                "majestic but dangerous frontier that conceals mysterious new threats.",
        R.drawable.game1_detail,
        gameId = 1
    ),

    Game("God of War Ragnarok", R.drawable.game2,
        "God Of War",
        "Ragnarok","Join Kratos and Atreus to explore the " +
                "mythic Nine Realms in search of answers and allies in God of War Ragnarök",
        R.drawable.game2,
        gameId = 2),

    Game("Elden Ring",
        R.drawable.elden_ring,
        "Elden Ring", gameId = 3),

    Game("Destiny the Witch Queen", R.drawable.game4_new,
        "Destiny",
        "The Witch Queen", gameId = 4),

    Game("The Legend of Zelda",
        R.drawable.game5, gameId = 5),

    Game("Dying Light 2 Stay Human",
        R.drawable.game6,
        "Dying Light 2",
        "Stay Human", gameId = 6),
    )


