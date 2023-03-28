package com.tebreca.simplemodelhost.util;

import com.tebreca.simplemodelhost.pojo.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FileHelper {

    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    public static void initIp() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            logger.error("Error whilst trying to grab ip!", e);
        }
    }

    public static String ip;
    private static int id = 0;

    public static Model[] scan(File folder) {
        if (folder.isFile()) {
            return new Model[0];
        }
        return Arrays.stream(folder.listFiles()).map(FileHelper::from).toArray(Model[]::new);
    }

    private static Model from(File file) {
        Model model;
        try {
            model = new Model(id++, file, new URI("http://" + ip + ":8080/models/" + legalize(file.getName())));
        } catch (URISyntaxException e) {
            model = new Model(-1, null, null);
            logger.error("Whoops", e);
        }
        return model;
    }

    private static String legalize(String name) {
        return name.replace(" ", "%20");
    }


}
