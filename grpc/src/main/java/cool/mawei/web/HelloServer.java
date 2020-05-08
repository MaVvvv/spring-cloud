package cool.mawei.web;

import cool.mawei.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 16:41
 */
public class HelloServer {

    private static final Logger log = LoggerFactory.getLogger(HelloServer.class);

    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(8899).addService(new HelloServiceImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.debug("关闭jvm...");
            this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HelloServer helloServer = new HelloServer();
        helloServer.start();
        helloServer.awaitTermination();
    }
}
