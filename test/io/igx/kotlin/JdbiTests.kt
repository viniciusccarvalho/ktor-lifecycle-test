package io.igx.kotlin

import io.igx.kotlin.model.Event
import io.igx.kotlin.model.EventType
import io.igx.kotlin.modules.common
import io.igx.kotlin.repositories.EventRepository
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withExtension
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.*

/**
 * @author vinicius
 *
 */
class JdbiTests : KoinTest {


    @Test
    fun testInsertEvent(){
        startKoin {
            modules(common)
        }

        val  jdbi = get<Jdbi>()

//        jdbi.withExtension(EventRepository::class.java){ repo ->  repo.insert(Event(UUID.randomUUID().toString(), EventType.START, 1)) }

        val repo = jdbi.onDemand<EventRepository>()
        repo.insert(Event(UUID.randomUUID().toString(), EventType.START, "localhost"))
    }

}