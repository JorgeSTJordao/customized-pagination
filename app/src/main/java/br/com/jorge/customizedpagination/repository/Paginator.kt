package br.com.jorge.customizedpagination.repository

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}