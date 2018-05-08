package com.fiap.carautomation.utils;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import java.io.IOException;

public class GoogleApiUtils {

    public static DistanceMatrix getDistanceAndTime(String origin,String destin) throws InterruptedException, ApiException, IOException {

        GeoApiContext apiContext = new GeoApiContext.Builder()
                .apiKey("")
                .build();

        String endereco1 = "Avenida general teixeira lott,263,SP";
        String endereco2 = "Avenida Paulista,SP";
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(apiContext);
        DistanceMatrix result = req.origins(endereco1)
                .destinations(endereco2)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .language("en-US")
                .await();



        return result;
    }
}
