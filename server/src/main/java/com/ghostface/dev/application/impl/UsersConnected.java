package com.ghostface.dev.application.impl;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UsersConnected implements Collection<SimpleUser> {

    private final @NotNull Set<@NotNull SimpleUser> users = ConcurrentHashMap.newKeySet();

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public boolean isEmpty() {
        return users.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return users.contains(o);
    }

    @NotNull
    @Override
    public Iterator<SimpleUser> iterator() {
        return users.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return users.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return users.toArray(a);
    }

    @Override
    public boolean add(SimpleUser simpleUser) {
        return users.add(simpleUser);
    }

    @Override
    public boolean remove(Object o) {
        return users.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return users.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends SimpleUser> c) {
        return users.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return users.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return users.retainAll(c);
    }

    @Override
    public void clear() {
        users.clear();
    }
}
