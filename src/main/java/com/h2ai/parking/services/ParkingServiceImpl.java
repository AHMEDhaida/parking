package com.h2ai.parking.services;

import com.h2ai.parking.dao.ParkingAPIDAO;
import com.h2ai.parking.dao.entity.RecordEntity;
import com.h2ai.parking.dao.entity.ReponseParkingAPIEntity;
import com.h2ai.parking.models.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingAPIDAO parkingAPIDAO;
    @Override
    public List<Parking> getListeParkings() {
        ReponseParkingAPIEntity reponse = parkingAPIDAO.getListeParkings();
        return transformEntityToModel(reponse);
    }

    private List<Parking> transformEntityToModel(ReponseParkingAPIEntity reponse) {
        List<Parking> resultat = new ArrayList<Parking>();
        for(RecordEntity recordEntity : reponse.getRecords()){
            Parking parking = new Parking();
            parking.setNom(recordEntity.getFields().getGrp_nom());
            parking.setStatut(getGrp_statut(recordEntity));
            parking.setNbPlacesDispo(recordEntity.getFields().getDisponibilite());
            parking.setNbPlacesTotal(recordEntity.getFields().getGrp_exploitation());
            parking.setHeureMaj(getGrp_horodatage(recordEntity));
            resultat.add(parking);

        }

        return resultat;
    }

    private String getGrp_horodatage(RecordEntity recordEntity) {
        OffsetDateTime dataMaj = OffsetDateTime.parse(recordEntity.getFields().getGrp_horodatage());
        //OffsetDateTime offsetDateTime1 = dataMaj.withOffsetSameInstant(ZoneOffset.of("+01:00"));
        return dataMaj.getHour()+"h"+dataMaj.getMinute();
    }

    private String getGrp_statut(RecordEntity recordEntity) {
        switch (recordEntity.getFields().getGrp_statut()){
            case "1": {return "FERME";}
            case "2": {return "ABONNES";}
            case "5": {return "OUVERT";}
        }
        return "Données non disponibles";
    }
}
