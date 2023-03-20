/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.listener;

import com.dma.controllers.MainController;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {
    static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // initialized log4j
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("/") + File.separator + log4jConfigFile;

        System.setProperty("PATH", context.getRealPath("/"));

        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("... Listener destroyed.");
    }
}
