package com.geosilent.ai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geosilent.ai.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class RulesViewModel @Inject constructor(private val repo: RuleRepository): ViewModel() {
    val rules = repo.observeRules().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    fun setEnabled(id: Long, value: Boolean) = viewModelScope.launch { repo.setEnabled(id,value) }
    fun addSample() = viewModelScope.launch { repo.save(Rule(name="New location rule", triggerType=TriggerType.LOCATION_ENTER, triggerConfig="", action=SoundAction.SILENT)) }
}
