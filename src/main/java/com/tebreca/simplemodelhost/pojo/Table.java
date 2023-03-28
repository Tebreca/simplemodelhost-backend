package com.tebreca.simplemodelhost.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class Table {

    private int id;

    private boolean enabled;

    private boolean frozen;

    private Logger logger;

    private final RestTemplate restTemplate = new RestTemplate();

    public Table(int id, boolean enabled, boolean frozen, String address) {
        this.id = id;
        this.enabled = enabled;
        this.frozen = frozen;
        this.address = address;
        logger = LoggerFactory.getLogger("Table " + id);
    }

    String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (id != table.id) return false;
        if (enabled != table.enabled) return false;
        if (frozen != table.frozen) return false;
        return Objects.equals(address, table.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (frozen ? 1 : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public int getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean start() {
        String ignored = restTemplate.getForObject("http://" + address + "/start", String.class);
        logger.debug(ignored);
        return false;
    }

    public boolean freeze() {
        String ignored = restTemplate.getForObject("http://" + address + "/freeze", String.class);
        logger.debug(ignored);
        return false;
    }

    public boolean unfreeze() {
        String ignored = restTemplate.getForObject("http://" + address + "/unfreeze", String.class);
        logger.debug(ignored);
        return false;
    }

    public boolean open(Model model) {
        String ignored = restTemplate.getForObject("http://" + address + "/open?ip=" + model.uri.toString(), String.class);
        logger.debug(ignored);
        return false;
    }

    public boolean stop() {
        String ignored = restTemplate.getForObject("http://" + address + "/stop", String.class);
        logger.debug(ignored);
        return false;
    }

    public Status status() {
        //TODO
        return new Status(false, false);
    }

    public boolean off() {
        String ignored = restTemplate.getForObject("http://" + address + "/off", String.class);
        logger.debug(ignored);
        return false;
    }

    public static class Status {
        boolean on;

        public Status(boolean on, boolean frozen) {
            this.on = on;
            this.frozen = frozen;
        }

        public boolean isOn() {
            return on;
        }

        public void setOn(boolean on) {
            this.on = on;
        }

        public boolean isFrozen() {
            return frozen;
        }

        public void setFrozen(boolean frozen) {
            this.frozen = frozen;
        }

        boolean frozen;
    }

}
