package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.MedicalEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore<T extends MedicalEntity> {
    private final Map<String, T> records;

    public DataStore() {
        this.records = new HashMap<>();
    }

    public void save(T record) {
        if (!Validator.isPresent(record)) {
            throw new IllegalArgumentException("Record is required.");
        }
        records.put(record.getId(), record);
    }

    public T findById(String id) {
        return records.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(records.values());
    }

    public boolean deleteById(String id) {
        return records.remove(id) != null;
    }

    public int size() {
        return records.size();
    }
}
