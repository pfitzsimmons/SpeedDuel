package net.pfitz.webspeed.http;

import java.util.EnumSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

class JettyRunner {
	////mvn exec:java -Dexec.mainClass="net.pfitz.webspeed.http.JettyRunner" 
    public static void main(String args[]) {

        System.out.println("Initializing server...");
        final ServletContextHandler context =
          new ServletContextHandler(ServletContextHandler.NO_SECURITY);
        context.setContextPath("/");
        context.setResourceBase("webapp");

        context.setClassLoader(
            Thread.currentThread().getContextClassLoader()
        );
        context.addServlet(DefaultServlet.class, "/");	
        final ServletHolder jsp =
          context.addServlet(HelloServlet.class, "/");

        
        Server server = new Server(9091);
        SocketConnector connector = new SocketConnector();
        connector.setPort(9091);
        
        //server.setConnectors(new SocketConnector[]{connector});
        server.setHandler(context);
        //LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        //ExecutorThreadPool pool = new ExecutorThreadPool(50, 200, 10000, TimeUnit.MILLISECONDS, queue);
        //server.setThreadPool(pool);        
        
        
        
        System.out.println("Starting server...");
        try {
            server.start();
        } catch(Exception e) {
            System.out.println("Failed to start server!");
            return;
        }

        System.out.println("Server running...");
        while(true) {
            try {
                server.join();
            } catch(InterruptedException e) {
                System.out.println("Server interrupted!");
            }
        }
    }
}
