package io.igx.kotlin.modules

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.igx.kotlin.services.LifecycleManager
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.koin.dsl.module
import org.koin.dsl.onClose
import javax.sql.DataSource

/**
 * @author vinicius
 *
 */
val MYSQL_HOST = System.getenv("MYSQL_HOST") ?: "localhost"
val MYSQL_PASSWORD = System.getenv("MYSQL_PASSWORD") ?: ""

val common = module {

    single(createdAtStart = true) { val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://$MYSQL_HOST:3306/kotlin?useSSL=false"
        config.username = "root"
        config.password = MYSQL_PASSWORD
        HikariDataSource(config) as DataSource
    }

    single(createdAtStart = true) {

        Jdbi.create(get<DataSource>())
            .installPlugin(SqlObjectPlugin())
            .installPlugin(KotlinPlugin())
            .installPlugin(KotlinSqlObjectPlugin())
    }

    single(createdAtStart = true) { LifecycleManager(get()) }.onClose {
        it?.stop()
    }
}