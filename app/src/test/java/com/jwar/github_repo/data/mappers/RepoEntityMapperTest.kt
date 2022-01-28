package com.jwar.github_repo.data.mappers

import com.jwar.github_repo.data.local.entities.RepoEntity
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

class RepoEntityMapperTest : TestCase() {

    private val repoEntity = mockk<RepoEntity>(relaxed = true)
    private val repoEntityMapper = RepoEntityMapper()

    fun testMap() {
        with(repoEntity) {
            every { uid } returns 1
            every { name } returns "Kotlin"
            every { fullName } returns "Jetbrains/Kotlin"
            every { htmlUrl } returns "https://github.com/JetBrains/Kotlin"
            every { description } returns "The Kotlin Programming Language"
            every { forksCount } returns 100
            every { watchersCount } returns 100
            every { subscribersCount } returns 100
            every { openIssuesCount } returns 100
        }

        val result = repoEntityMapper.map(repoEntity)

        assertEquals(1, result.id)
        assertEquals("Kotlin", result.name)
        assertEquals("Jetbrains/Kotlin", result.fullName)
        assertEquals("https://github.com/JetBrains/Kotlin", result.htmlUrl)
        assertEquals("The Kotlin Programming Language", result.description)
        assertEquals(100, result.forksCount)
        assertEquals(100, result.subscribersCount)
        assertEquals(100, result.watchersCount)
        assertEquals(100, result.openIssuesCount)
    }
}