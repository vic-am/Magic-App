package com.accenture.magicapp.Util

class Common {

    object VIEWTYPE {
        val HEADER_MAIN: Int = 0
        val HEADER_TYPE: Int = 1
        val BODY_CARDS: Int = 2
    }

    object SETSNAMES {
        val NAMES = listOf(
            "Tenth Edition",
            "Unlimited Edition",
            "Double Masters",
            "Revised Edition",
            "Fourth Edition Foreign Black Border",
            "Fourth Edition",
            "Fifth Dawn",
            "Fifth Edition",
            "Classic Sixth Edition",
            "Seventh Edition",
            "Eighth Edition",
            "Ninth Edition",
            "Masters 25",
            "Aether Revolt",
            "Jumpstart Arena Exclusives"
        )
        val CODES = listOf(
            "10E",
            "2ED",
            "2XM",
            "3ED",
            "4BB",
            "4ED",
            "5DN",
            "5ED",
            "6ED",
            "7ED",
            "8ED",
            "9ED",
            "A25",
            "AER",
            "AJMP"
        )

        fun returnSetName(code: String): String {
            return when (code) {
                "10E" -> "Tenth Edition"
                "2ED" -> "Unlimited Edition"
                "2XM" -> "Double Masters"
                "3ED" -> "Revised Edition"
                "4BB" -> "Fourth Edition Foreign Black Border"
                "4ED" -> "Fourth Edition"
                "5DN" -> "Fifth Dawn"
                "5ED" -> "Fifth Edition"
                "6ED" -> "Classic Sixth Edition"
                "7ED" -> "Seventh Edition"
                else -> code
            }
        }
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

    object ITEMVIEWIDENTIFY {
        val HEADERIDENTIFY = "HEADERVIEW"
        val TYPEIDENTIFY = "TYPEVIEW"
    }
}