package com.airtribe.meditrack.interfaces;

import java.util.List;

public interface Searchable<T, C> {
    List<T> search(C criteria);

    default boolean containsIgnoreCase(String value, String searchTerm) {
        return value != null
                && searchTerm != null
                && value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
