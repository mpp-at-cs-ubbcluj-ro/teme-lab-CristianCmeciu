package ro.mpp2024.Repository;

import ro.mpp2024.domain.Participant;
import ro.mpp2024.domain.Proba;

import java.util.List;


public interface ParticipantRepository extends Repository<Integer, Participant> {
    Iterable<Participant> findParticipantiByProba(Proba proba);
}
