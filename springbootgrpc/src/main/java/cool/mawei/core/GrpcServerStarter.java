package cool.mawei.core;

import cool.mawei.service.BookRpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-13 15:19
 */
//@Component
public class GrpcServerStarter {

    private Server server;

    //@PostConstruct
    public void grpcMain() throws IOException, InterruptedException {
        System.out.println("GrpcServerStarter..........");
        this.start();
        this.awaitTermination();
    }

    private void start() throws IOException {
        server = ServerBuilder.forPort(8899).addService(new BookRpcServiceImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
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
}
