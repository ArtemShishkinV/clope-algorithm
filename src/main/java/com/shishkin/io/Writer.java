package com.shishkin.io;

import com.shishkin.models.Cluster;

import java.util.List;

public interface Writer {
    void write(List<Cluster> clusters);
}
