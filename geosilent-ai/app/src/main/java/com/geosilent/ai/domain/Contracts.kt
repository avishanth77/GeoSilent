package com.geosilent.ai.domain

import kotlinx.coroutines.flow.Flow

interface RuleRepository { fun observeRules(): Flow<List<Rule>>; suspend fun save(rule: Rule): Long; suspend fun delete(id: Long); suspend fun setEnabled(id: Long, enabled: Boolean) }
interface SoundProfileController { suspend fun apply(action: SoundAction): Result<Unit>; suspend fun captureCurrentProfile(): String; suspend fun restore(profile: String): Result<Unit> }
interface GeofenceManager { suspend fun register(rule: Rule): Result<Unit>; suspend fun unregister(rule: Rule): Result<Unit> }
interface ScheduleManager { suspend fun schedule(rule: Rule): Result<Unit>; suspend fun cancel(rule: Rule): Result<Unit> }
interface ExecutionLogger { suspend fun record(log: ExecutionLog) }
