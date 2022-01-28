package com.jwar.github_repo.core.shared

interface Mapper<in T, out R> {
    fun map(origin: T): R
}