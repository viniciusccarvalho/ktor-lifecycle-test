package io.igx.kotlin.services

import io.igx.kotlin.model.Event
import io.igx.kotlin.model.EventType
import io.igx.kotlin.repositories.EventRepository
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.kotlin.onDemand
import java.util.*

/**
 * @author vinicius
 *
 */

class LifecycleManager(private val jdbi: Jdbi) {
    val instanceId = System.getenv("INSTANCE_ID") ?: "localhost"
    private val repo = jdbi.onDemand<EventRepository>()
    init {
        repo.createTable()
        repo.insert(Event(UUID.randomUUID().toString(), EventType.START, instanceId))
    }

    fun stop(){
        repo.insert(Event(UUID.randomUUID().toString(), EventType.STOP, instanceId))
    }

}