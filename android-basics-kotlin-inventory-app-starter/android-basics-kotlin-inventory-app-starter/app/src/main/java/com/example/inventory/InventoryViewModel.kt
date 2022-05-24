package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemTitle: String,
        itemSeries: String,
        itemIssue: String,
        itemCost: String
    ): Item {
        return Item(
            id = itemId,
            itemTitle = itemTitle,
            itemSeries = itemSeries.toInt(),
            itemIssue = itemIssue.toInt(),
            itemCost = itemCost.toDouble()
        )
    }

    fun updateItem(
        itemId: Int,
        itemTitle: String,
        itemSeries: String,
        itemIssue: String,
        itemCost: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemTitle, itemSeries, itemIssue, itemCost)
        updateItem(updatedItem)
    }

    fun deleteItem(item: Item){
        viewModelScope.launch{
            itemDao.delete(item)
        }
    }

    fun retrieveItem(id: Int): LiveData<Item>{
        return itemDao.getItem(id).asLiveData()
    }

    private fun updateItem(item: Item){
        viewModelScope.launch {
            itemDao.update(item)
        }
    }
/*
    fun sellItem(item: Item){
        if(item.quantityInStock >0){
            val newItem = item.copy(quantityInStock = item.quantityInStock -1)
            updateItem(newItem)
        }
    }

 */

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    fun isEntryValid(itemTitle: String, itemSeries: String, itemIssue: String, itemCost: String): Boolean {
        if (itemTitle.isBlank() || itemSeries.isBlank() || itemIssue.isBlank() || itemCost.isBlank()) {
            return false
        }
        return true
    }

    private fun insertItem(item: Item){
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(itemTitle: String, itemSeries: String, itemIssue: String, itemCost: String): Item{
        return Item(
            itemTitle = itemTitle,
            itemSeries = itemSeries.toInt(),
            itemIssue = itemIssue.toInt(),
            itemCost = itemCost.toDouble()
        )
    }

    fun addNewItem(itemTitle: String, itemSeries: String, itemIssue: String, itemCost: String){
        val newItem = getNewItemEntry(itemTitle,itemSeries, itemIssue,itemCost)
        insertItem(newItem)
    }

}

private fun Double.isBlank(): Boolean {
    return this < 0
}

private fun Int.isBlank(): Boolean {
    return this < 0

}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}