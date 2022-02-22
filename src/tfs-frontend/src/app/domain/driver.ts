export enum VehicleType {
  Car = "Car",
  Bike = "Bike",
  Van = "Van",
  Other = "Other"
}

export interface Driver {
    id: number,
    name: string,
    surname: string,
    email: string,
    vehicleType: VehicleType,
    baseFarePrice: number,
    baseFareDistance: number
}