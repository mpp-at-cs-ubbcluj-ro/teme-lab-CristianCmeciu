package ro.mpp2024;

import ro.mpp2024.Repository.DBRepository.InscriereDBRepository;
import ro.mpp2024.Repository.DBRepository.ParticipantDBRepository;
import ro.mpp2024.Repository.DBRepository.ProbaDBRepository;
import ro.mpp2024.Repository.InscriereRepository;
import ro.mpp2024.Repository.ParticipantRepository;
import ro.mpp2024.Repository.ProbaRepository;
import ro.mpp2024.Repository.Repository;
import ro.mpp2024.domain.Inscriere;
import ro.mpp2024.domain.Participant;
import ro.mpp2024.domain.Proba;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        ParticipantRepository participanti = new ParticipantDBRepository(props);
        /*
        ProbaRepository probaRepository = new ProbaDBRepository(props);
        InscriereRepository inscriereRepository = new InscriereDBRepository(props);
        Participant participant = new Participant("nume",18);
        participant.setId(1);
        Inscriere inscriere = new Inscriere(participant,pr);
        inscriereRepository.save(inscriere);
        */
        Proba pr = new Proba(100,"stil");
        pr.setId(1);
        for (var elem:participanti.findParticipantiByProba(pr)) {
            System.out.println(elem.getNume());
        }
    }
}