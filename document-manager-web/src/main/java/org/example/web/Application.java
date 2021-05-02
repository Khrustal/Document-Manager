package org.example.web;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class Application {
    private Server server;

    void start() throws Exception {

        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;

        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[] { connector });

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(CreateDirServlet.class, "/create_dir");
        servletHandler.addServletWithMapping(CreateDocServlet.class, "/create_doc");
        servletHandler.addServletWithMapping(DocTypeServlet.class, "/doctype");
        servletHandler.addServletWithMapping(GetContentsServlet.class, "/get_contents");
        servletHandler.addServletWithMapping(GetDirServlet.class, "/get_dir");
        servletHandler.addServletWithMapping(GetDocServlet.class, "/get_doc");

        server.start();

    }

    void stop() throws Exception {
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.start();
    }
}
