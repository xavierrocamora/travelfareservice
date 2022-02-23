import { TravelFare } from "./travelFare";
import { Driver } from "./driver";

export interface CheapestFareDTO {
    id: String,
    cheapestCost: number,
    travelFareData: TravelFare,
    cheapestDriver: Driver
}