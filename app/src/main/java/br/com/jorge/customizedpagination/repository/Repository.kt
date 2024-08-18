package br.com.jorge.customizedpagination.repository

import androidx.compose.material3.ListItem
import kotlinx.coroutines.delay

class Repository {

    private val remoteDataSource = (0..100).map{ index  ->
        ListItem(
            title = "Item $index",
            description = "Description $index"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>>{
        delay(2000L)
        val startingIndex = page * pageSize //each page has a fixed size and index
        return if (startingIndex + pageSize <= remoteDataSource.size){
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else {
            Result.success(emptyList())
        }
    }
}