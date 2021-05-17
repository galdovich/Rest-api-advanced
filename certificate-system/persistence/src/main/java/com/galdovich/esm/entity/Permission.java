package com.galdovich.esm.entity;

/**
 * Class {@code Permission}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public enum Permission {
    AUTHORITY_READ("authority:read"),
    AUTHORITY_WRITE("authority:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
