package com.shishkin.io.writer;

import com.shishkin.app.models.Cluster;

import java.util.List;

public class ConsoleWriter implements Writer {
    @Override
    public void write(List<Cluster> clusters) {
        clusters.forEach(System.out::println);
    }
}
