package br.com.hamamoto;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public abstract class BaseTest {

    private static MongodExecutable MONGO;

    @BeforeAll
    public static void startMongoDatabase() throws IOException {
        var config = buildMongodConfiguration();

        MONGO = MongodStarter.getDefaultInstance().prepare(config);
        try {
            MONGO.start();
            System.out.println("foo.sout");
        } catch (IOException e) {
//            LOGGER.err ("Unable to start the mongo instance", e)/;
        }
    }

    @Test
    public void foo() {

    }

    @AfterAll
    static void afterAll() {
        MONGO.stop();
    }

    private static IMongodConfig buildMongodConfiguration()
            throws IOException {
        final MongodConfigBuilder builder = new MongodConfigBuilder()
                .version(Version.Main.V3_3)
                .net(new Net("127.0.0.1", 27020, Network.localhostIsIPv6()));
        return builder.build();
    }

}
