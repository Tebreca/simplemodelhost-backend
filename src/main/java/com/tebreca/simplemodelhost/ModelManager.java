package com.tebreca.simplemodelhost;

import com.tebreca.simplemodelhost.pojo.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelManager {

    public static final ModelManager INSTANCE = new ModelManager();


    List<Model> models = new ArrayList<>();


    public void add(Model... model) {
        Collections.addAll(models, model);
        check();
    }


    private void check() {
        models.removeIf(model -> model.getId() == -1);
        for (int i = 0; i < models.size(); i++) {
            AtomicInteger check = new AtomicInteger();
            int finalI = i;
            models.stream().filter(model -> model.getId() == finalI).forEach((m) -> {
                if (check.getAndIncrement() > 0) {
                    models.remove(m);
                }
            });
        }

    }


    public Model[] getAll() {
        return models.toArray(Model[]::new);
    }

    public void clear() {
        models.clear();
    }
}
