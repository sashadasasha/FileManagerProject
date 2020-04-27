package org.alekskorn.manager.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInfo {
    private String name;
    private long length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public FileInfo(Path path) {
        this.name = path.getFileName().toString();
        try {
            if (Files.isDirectory(path)) {
                this.length = -1L;
            } else {
                this.length = Files.size(path);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }



}
