package br.com.jorge.customizedpagination.repository

class DefaultPaginator<Key, Item> (
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (next: Key) -> Result<List<Item>>, //How capture the next items
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item>{

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) { //Not requesting
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey) //Data returned
        isMakingRequest = false

        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }

        currentKey = getNextKey(items)

        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }

}