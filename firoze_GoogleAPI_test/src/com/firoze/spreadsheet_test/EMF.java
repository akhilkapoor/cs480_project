package com.firoze.spreadsheet_test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("testjpa");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
