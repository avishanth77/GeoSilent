package com.geosilent.ai.domain

enum class TriggerType { LOCATION_ENTER, LOCATION_EXIT, SCHEDULE, WIFI, CHARGING, CALENDAR }
enum class SoundAction { SILENT, VIBRATE, RING, DND_REQUEST, NOTIFICATION, RESTORE_PREVIOUS }
enum class RestoreBehavior { ON_EXIT, AFTER_SCHEDULE, NEVER }

data class Rule(val id: Long = 0, val name: String, val enabled: Boolean = true, val triggerType: TriggerType, val triggerConfig: String, val action: SoundAction, val priority: Int = 0, val recurrence: String? = null, val restoreBehavior: RestoreBehavior = RestoreBehavior.ON_EXIT)
data class ExecutionLog(val id: Long = 0, val ruleId: Long, val triggerTime: Long, val action: SoundAction, val success: Boolean, val errorReason: String? = null)
