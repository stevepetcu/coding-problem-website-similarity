package com.stefanpetcu.codingproblemwebsitesimilarity.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.function.Executable

class WebsiteSimilarityCalculatorTest {
    private val service = WebsiteSimilarityCalculator()

    @Test
    fun pairsOfSimilarWebsitesFrom_willReturnExpectedResult_givenTheInput() {
        assertAll(
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(), 0
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1)), 0
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1), Pair('b', 2)), 0
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(), 1
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1)), 1
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1), Pair('a', 2)), 1
                    ).size
                )
            },
            Executable {
                assertEquals(
                    0, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1), Pair('a', 2)), 2
                    ).size
                )
            },
            Executable {
                assertEquals(
                    1, service.pairsOfSimilarWebsitesFrom(
                        listOf(Pair('a', 1), Pair('b', 2)), 2
                    ).size
                )
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 2),
                        Pair('b', 1), Pair('b', 2),
                        Pair('c', 1), Pair('c', 2),
                    ), 1
                )
                assertEquals(1, result.size)
                assertEquals(listOf(Pair('a', 'b')), result)
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 2),
                        Pair('b', 1), Pair('b', 2),
                        Pair('c', 1), Pair('c', 2),
                    ), 2
                )
                assertEquals(2, result.size)
                assertEquals(listOf(Pair('a', 'b'), Pair('a', 'c')), result)
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 2),
                        Pair('b', 1), Pair('b', 3),
                        Pair('c', 1), Pair('c', 2),
                    ), 2
                )
                assertEquals(2, result.size)
                assertEquals(listOf(Pair('a', 'c'), Pair('a', 'b')), result)
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 2),
                        Pair('b', 1), Pair('b', 3),
                        Pair('c', 1), Pair('c', 2),
                    ), 1
                )
                assertEquals(1, result.size)
                assertEquals(listOf(Pair('a', 'c')), result)
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 3), Pair('a', 5),
                        Pair('b', 2), Pair('b', 6),
                        Pair('c', 1), Pair('c', 2), Pair('c', 3), Pair('c', 4), Pair('c', 5),
                        Pair('d', 4), Pair('d', 5), Pair('d', 6), Pair('d', 7),
                        Pair('e', 1), Pair('e', 3), Pair('e', 5), Pair('e', 6)
                    ), 1
                )
                assertEquals(1, result.size)
                assertEquals(listOf(Pair('a', 'e')), result)
            },
            Executable {
                val result = service.pairsOfSimilarWebsitesFrom(
                    listOf(
                        Pair('a', 1), Pair('a', 3), Pair('a', 5),
                        Pair('b', 2), Pair('b', 6),
                        Pair('c', 1), Pair('c', 2), Pair('c', 3), Pair('c', 4), Pair('c', 5),
                        Pair('d', 4), Pair('d', 5), Pair('d', 6), Pair('d', 7),
                        Pair('e', 1), Pair('e', 3), Pair('e', 5), Pair('e', 6)
                    ), 3
                )
                assertEquals(3, result.size)
                assertEquals(listOf(Pair('a', 'e'), Pair('c', 'e'), Pair('a', 'c')), result)
            },
        )
    }
}
