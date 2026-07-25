package com.geosilent.ai.data

import com.geosilent.ai.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryRuleRepository @Inject constructor() : RuleRepository {
    private val rules = MutableStateFlow(listOf(Rule(1, "Office location", true, TriggerType.LOCATION_ENTER, "37.421998,-122.084000|150m", SoundAction.SILENT, 10), Rule(2, "Weekdays, 9 AM–5 PM", true, TriggerType.SCHEDULE, "MON-FRI|09:00-17:00", SoundAction.VIBRATE, 5)))
    override fun observeRules(): Flow<List<Rule>> = rules.asStateFlow()
    override suspend fun save(rule: Rule): Long { val id = if (rule.id == 0L) (rules.value.maxOfOrNull { it.id } ?: 0) + 1 else rule.id; rules.value = rules.value.filterNot { it.id == id } + rule.copy(id = id); return id }
    override suspend fun delete(id: Long) { rules.value = rules.value.filterNot { it.id == id } }
    override suspend fun setEnabled(id: Long, enabled: Boolean) { rules.value = rules.value.map { if (it.id == id) it.copy(enabled = enabled) else it } }
}
