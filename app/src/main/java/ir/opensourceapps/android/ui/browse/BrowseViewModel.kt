package ir.opensourceapps.android.ui.browse

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ir.opensourceapps.android.data.repository.RepoPagingRepository

class BrowseViewModel(private val repoRepository: RepoPagingRepository) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()
    private val repoResult = Transformations.map(searchQuery) {
        repoRepository.listOfNutritionist(10)
    }
    val list = Transformations.switchMap(repoResult) { it.pagedList }!!
    val networkState = Transformations.switchMap(repoResult) { it.networkState }!!
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }!!

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun searchIt(query: String): Boolean {
        if (searchQuery.value == query) {
            return false
        }
        searchQuery.value = query
        return true
    }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }

    fun currentSearchQuery(): String? = searchQuery.value
}