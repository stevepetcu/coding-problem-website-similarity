package com.stefanpetcu.codingproblemwebsitesimilarity.service

import com.stefanpetcu.codingproblemwebsitesimilarity.dto.WebsiteVisit
import com.stefanpetcu.codingproblemwebsitesimilarity.dto.WebsiteVisit.Companion.similarityValue
import java.util.Optional

class WebsiteSimilarityCalculator {
    fun pairsOfSimilarWebsitesFrom(websites: List<Pair<Char, Int>>, numberOfPairs: Int): List<Pair<Char, Char>> {
        if (numberOfPairs < 1 || websites.size < 2) {
            return listOf()
        }

        val websiteVisits: MutableList<WebsiteVisit> = mutableListOf()

        websites.forEach { ws ->
            Optional.ofNullable(websiteVisits.find { wv -> wv.website == ws.first })
                .ifPresentOrElse({ optWv ->
                    // websiteVisits already contains an entry for this website.
                    optWv.userIds.add(ws.second)
                }, {
                    // websiteVisits does not already contain an entry for this website.
                    websiteVisits.add(WebsiteVisit(ws.first, mutableSetOf(ws.second)))
                })
        }

        if (websiteVisits.size < 2) {
            return listOf()
        } else if (websiteVisits.size == 2) {
            return listOf(Pair(websiteVisits.first().website, websiteVisits.last().website))
        }

        // ~ O(n^2) way:
        val results: MutableList<Pair<Pair<Char, Char>, Pair<Int, Int>>> = mutableListOf()

        for (i in 0 until websiteVisits.size - 1) {
            for (j in (i + 1) until websiteVisits.size) {
                results.add(
                    Pair(
                        Pair(websiteVisits[i].website, websiteVisits[j].website),
                        similarityValue(websiteVisits[i], websiteVisits[j])
                    )
                )
            }
        }

        // Sort by the similarity value, then by the difference in the number of different users that visited.
        results.sortWith(
            compareByDescending<Pair<Pair<Char, Char>, Pair<Int, Int>>> { it.second.first }
                .thenComparing(compareBy { it.second.second })
        )

        return results.take(numberOfPairs).map { it.first }
    }
}
