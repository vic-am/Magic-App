package com.accenture.magicapp.Util

class Common {

    object VIEWTYPE {
        val HEADER_MAIN: Int = 0
        val HEADER_TYPE: Int = 1
        val BODY_CARDS: Int = 2
    }

    object CARDS_TYPE {
        val TYPES = listOf(
            "Artifact",
            "Conspiracy",
            "Creature",
            "Enchantment",
            "Instant",
            "Land",
            "Phenomenon",
            "Plane",
            "Planeswalker",
            "Scheme",
            "Sorcery",
            "Tribal",
            "Vanguard"
        )
    }

    object TESTS{
        val HEADER_TEST = "HEADERTEST"
        val TYPE_TEST = "TYPETEST"
    }

    object PAGES {
        val NUM_PAGES = 5
    }

}