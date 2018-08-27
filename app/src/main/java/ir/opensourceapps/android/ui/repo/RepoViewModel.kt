package ir.opensourceapps.android.ui.repo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ir.opensourceapps.android.data.repository.RepoRepository
import ir.opensourceapps.android.model.Repo

class RepoViewModel(repoRepository: RepoRepository) : ViewModel() {
    private var readmeTrigger = MutableLiveData<Repo>()
    var readmeObserver = Transformations.switchMap(
            readmeTrigger
    ) { repoRepository.readme(it.owner.login, it.name) }

    fun getReadme(repo: Repo) {
        readmeTrigger.value = repo
    }
}