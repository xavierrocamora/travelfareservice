export enum VehicleType {
  Taxi = "Taxi",
  Bus = "Bus",
  Train = "Train",
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