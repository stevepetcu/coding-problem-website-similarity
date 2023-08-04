package com.stefanpetcu.codingproblemwebsitesimilarity.dto

import kotlin.math.abs

data class WebsiteVisit(val website: Char, val userIds: MutableSet<Int>) {
    companion object {
        fun similarityValue(first: WebsiteVisit, second: WebsiteVisit): Pair<Int, Int> {
            return Pair(
                first.userIds.intersect(second.userIds).size,
                abs(first.userIds.size - second.userIds.size)
            )
        }
    }
}
