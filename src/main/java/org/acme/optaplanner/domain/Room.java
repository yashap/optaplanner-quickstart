package org.acme.optaplanner.domain;

/**
 * The Room class represents a location where lessons are taught, for example, Room A or Room B. For simplicity’s sake,
 * all rooms are without capacity limits and they can accommodate all lessons.
 *
 * Because no Room instances change during solving, a Room is called a problem fact. Problem facts require no
 * OptaPlanner specific annotations.
 */
public class Room {
    private String name;

    public Room() {}

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                '}';
    }
}
