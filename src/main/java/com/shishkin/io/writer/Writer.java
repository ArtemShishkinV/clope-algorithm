package com.shishkin.io.writer;

import com.shishkin.app.models.Cluster;

import java.util.List;

public interface Writer {
    void write(List<Cluster> clusters);
}
