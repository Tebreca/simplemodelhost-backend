package com.tebreca.simplemodelhost.pojo;

import java.io.File;
import java.net.URI;
import java.util.Objects;

public class Model {
    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", file=" + file +
                ", uri=" + uri +
                '}';
    }

    int id;
    File file;
    URI uri;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return id == model.id && Objects.equals(file, model.file) && Objects.equals(uri, model.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file, uri);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Model(int id, File file, URI uri) {
        this.id = id;
        this.file = file;
        this.uri = uri;
    }
}
