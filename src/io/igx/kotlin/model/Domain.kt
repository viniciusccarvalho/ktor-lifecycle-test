package io.igx.kotlin.model

import java.time.LocalDateTime

/**
 * @author vinicius
 *
 */

data class Event(val id: String, val type: EventType, val instanceId: String, val eventTime: LocalDateTime = LocalDateTime.now())

enum class EventType{
    START, STOP
}