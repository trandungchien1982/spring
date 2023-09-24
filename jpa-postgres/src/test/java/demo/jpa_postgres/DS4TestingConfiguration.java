package demo.jpa_postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import javax.sql.DataSource;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.V9_5_15;

/***
 * Configuration chỉ dành cho Testing mà thôi
 * Ta sẽ tạo 1 EmbbedPosgreSQL DB Server dành cho Integration Test
 */
@TestConfiguration
class DS4TestingConfiguration {

	static Logger log = LoggerFactory.getLogger(DS4TestingConfiguration.class);

	static private EmbeddedPostgres stubEmbbedPostgresServer = null;

	@Primary
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource postgresDataSource() {
		log.info("[TEST] Create dataSource for Testing only: Start Embbed PostgreSQL Server ...");
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(startEmbbedPostreSQL());
		return dataSourceBuilder.build();
	}

	private String startEmbbedPostreSQL() {
		try {
			// starting Postgres
			final EmbeddedPostgres postgres = new EmbeddedPostgres(V9_5_15);
			// predefined data directory
			// final EmbeddedPostgres postgres = new EmbeddedPostgres(V9_6, "/path/to/predefined/data/directory");
			final String url = postgres.start(
					"localhost", 9432, "dbName", "dbUserName", "dbPassword");

			stubEmbbedPostgresServer = postgres;
			return url;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void stopEmbbedPostgresSQL() {
		log.warn("[TEST] stopEmbbedPostgresSQL ... ");
		try {
			if (stubEmbbedPostgresServer != null) {
				log.warn("[TEST] Embbed DB Server stop() ... ");
				stubEmbbedPostgresServer.stop();
				log.warn("[TEST] Embbed DB Server close() ... ");
				stubEmbbedPostgresServer.close();
				log.warn("[TEST] Embbed DB Server finish() ... ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
