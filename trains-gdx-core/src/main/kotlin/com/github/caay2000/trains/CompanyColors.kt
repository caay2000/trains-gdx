package com.github.caay2000.trains

import com.badlogic.gdx.graphics.Color

enum class CompanyColors(val color: Color) {

    BLUE(Color.BLUE),
    BLACK(Color.BLACK),
    GREEN(Color.GREEN),
    ORANGE(Color.ORANGE);

    companion object {

        fun valueOf(intValue: Int): CompanyColors {
            for (color in CompanyColors.values()) {
                if (color.ordinal == intValue - 1) {
                    return color
                }
            }
            throw NotImplementedError()
        }
    }
}