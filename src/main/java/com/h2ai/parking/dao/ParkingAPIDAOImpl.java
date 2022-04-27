package com.h2ai.parking.dao;

import com.h2ai.parking.dao.entity.ReponseParkingAPIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class ParkingAPIDAOImpl implements ParkingAPIDAO {
    public static final String URL_API_OPEN_DATA = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&rows=30&facet=grp_nom&facet=grp_statut";
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ReponseParkingAPIEntity getListeParkings() {
        return restTemplate.getForEntity(URL_API_OPEN_DATA,ReponseParkingAPIEntity.class).getBody();
    }
}
