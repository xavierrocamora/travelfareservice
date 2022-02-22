package com.tap.travelfareservice.repository;

import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.domain.TravelFareData;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@Repository("IO-CSV")
public class TravelFareDataAccessService implements TravelFareRepository{
    private String fileToRead = "travelFareData.csv";
    private String fileToWrite = "cheapestFares.csv";

    @Override
    public TravelFareData getTravelFareData() {
        List<TravelFareData> list = readCSV();

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean saveCheapestTravelFare(TravelFare travelFare) {

        return canWriteCSV(travelFare, true);
    }

    private List<TravelFareData> readCSV() {
        List<TravelFareData> travelFareDataList = new ArrayList<>();
        String distanceTraveled, traveledUnits, costPerDistanceTraveled;
        BufferedReader reader = null;
        try {
            InputStream resource = new ClassPathResource(fileToRead).getInputStream();
            reader = new BufferedReader(new InputStreamReader(resource));

            String line;
            // Skip first line, the headers
            reader.readLine();

            // Read line by line until reaching the end of the file
            while ((line = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ",");

                distanceTraveled = str.nextToken();
                traveledUnits = str.nextToken();
                costPerDistanceTraveled = str.nextToken();

                TravelFareData travelFareData = new TravelFareData(
                        Double.parseDouble(distanceTraveled),
                        Double.parseDouble(traveledUnits),
                        Double.parseDouble(costPerDistanceTraveled)
                );
                travelFareDataList.add(travelFareData);
            }
            //Close the file
            reader.close();

        } catch (FileNotFoundException ex) {
            log.info("File not found.");
            ex.printStackTrace();
        } catch (IOException ex) {
            log.info("Reading error.");
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                log.info("Error closing the file.");
                ex.printStackTrace();
            }
        }
        return travelFareDataList;
    }

    private boolean canWriteCSV(TravelFare fareToSave, boolean isAppend) {
        // Set up
        boolean isWritten = false;
        File f = new File(fileToWrite);
        boolean hasHeader = false;
        if (f.exists() && isAppend) {
            if (!f.delete()) {
                log.info("File " + fileToWrite + "could not be deleted." );
            }
            hasHeader = true;
        }
        if (!f.exists()) {
            hasHeader = true;
        }

        // Format data to write
        List<String> list = new ArrayList<>();
        if (hasHeader) {
            String header = "id,cheapestCost,fareDistance,fareUnit,fareCostUnit,driverId,driverName,driverSurname," +
                    "driverEmail,driverVehicleType,driverBaseFareCost,driverBaseFareDistance";
            list.add(header);
        }
        list.add(convertToCSVFormat(fareToSave));

        // Write file
        FileWriter writerStream = null;
        BufferedWriter writer = null;
        try {
            writerStream = new FileWriter(f, isAppend);
            writer = new BufferedWriter(writerStream);
            for (String line : list) {
                writer.write(line);
                writer.newLine();
            }

            isWritten = true;
        } catch (IOException ex) {
            log.info("Writing Error.");
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (writerStream != null) {
                    writerStream.close();
                }
            } catch (IOException ex) {
                log.info("Error closing the file.");
                ex.printStackTrace();
            }
        }
        return isWritten;
    }

    private String convertToCSVFormat(TravelFare travelFare) {
        String line = "";

        line += travelFare.getId() + ",";
        line += travelFare.getCheapestCost() + ",";
        line += travelFare.getTravelFareData().getDistanceTraveled() + ",";
        line += travelFare.getTravelFareData().getTraveledUnit() + ",";
        line += travelFare.getTravelFareData().getCostPerDistanceTraveled() + ",";
        line += travelFare.getCheapestDriver().getId() + ",";
        line += travelFare.getCheapestDriver().getName() + ",";
        line += travelFare.getCheapestDriver().getSurname() + ",";
        line += travelFare.getCheapestDriver().getEmail() + ",";
        line += travelFare.getCheapestDriver().getVehicleType() + ",";
        line += travelFare.getCheapestDriver().getBaseFarePrice() + ",";
        line += travelFare.getCheapestDriver().getBaseFareDistance();

        return line;
    }

    public TravelFareDataAccessService(String fileToRead, String fileToWrite) {
        this.fileToRead = fileToRead;
        this.fileToWrite = fileToWrite;
    }
}
