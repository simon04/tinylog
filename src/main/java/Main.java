import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(8080);
        final ServletContextHandler handler = new ServletContextHandler();
        final ServletHolder servletHolder = new ServletHolder(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                try (PrintWriter writer = resp.getWriter()) {
                    for (int i = 0; i < 1_000_000; i++) {
                        writer.print(Math.random());
                        writer.print(" ");
                    }
                }
            }
        });
        handler.addServlet(servletHolder, "/");
        server.setHandler(handler);
        server.start();
    }
}
