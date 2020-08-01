package com.accenture.magicapp.Util

class Common {

    object VIEWTYPE {
        val HEADER_MAIN: Int = 0
        val HEADER_TYPE: Int = 1
        val BODY_CARDS: Int = 2
    }

    object CARDSTYPES {
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

    object ITEMVIEWIDENTIFY{
        val HEADERIDENTIFY = "HEADERVIEW"
        val TYPEIDENTIFY = "TYPEVIEW"
    }
}