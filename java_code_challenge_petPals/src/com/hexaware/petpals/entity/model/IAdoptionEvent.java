package com.hexaware.petpals.entity.model;

import java.util.ArrayList;
import java.util.List;

public class IAdoptionEvent {
    private List<IAdoptable> participants;

    public IAdoptionEvent() {
        this.participants = new ArrayList<>();
    }

    public void hostEvent() {
        System.out.println("Hosting adoption event with " + participants.size() + " participants.");
    }

    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
    }
}