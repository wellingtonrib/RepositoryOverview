package com.jwar.github_repo.data.mappers

import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.util.readJsonFile
import junit.framework.TestCase

class RepoModelMapperTest : TestCase() {

    private val repoModel = readJsonFile<RepoModel>("mock_repo.json")
    private val repoModelMapper = RepoModelMapper()

    fun testMap() {
        val result = repoModelMapper.map(repoModel)

        assertEquals(1, result.uid)
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