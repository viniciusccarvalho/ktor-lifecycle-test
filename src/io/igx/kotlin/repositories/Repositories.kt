package io.igx.kotlin.repositories

import io.igx.kotlin.model.Event
import org.jdbi.v3.sqlobject.statement.SqlUpdate

/**
 * @author vinicius
 *
 */

interface EventRepository {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS `events` ( `id` varchar(128) NOT NULL,\n" +
            "  `type` varchar(45) NOT NULL,\n" +
            "  `instanceId` varchar(128) NOT NULL DEFAULT '0',\n" +
            "  `eventTime` datetime NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    fun createTable()

    @SqlUpdate("insert into events (id, type, instanceId, eventTime) values (:event.id, :event.type, :event.instanceId, :event.eventTime) ")
    fun insert(event: Event)
}