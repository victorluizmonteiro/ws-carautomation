package com.fiap.carautomation.utils;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class GoogleApiUtils {

        private static final String APP_KEY = "AIzaSyA9u2sbcSVyqUMhZ6GJquIZDT-p2HI-AMA";

    public static DistanceMatrix getDistanceAndTime(String origin,String destin) throws InterruptedException, ApiException, IOException {

        GeoApiContext apiContext = new GeoApiContext.Builder()
                .apiKey(APP_KEY)
                .build();


        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(apiContext);
        DistanceMatrix result = req.origins(origin)
                .destinations(destin)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .language("en-US")
                .await();



        return result;
    }
}
