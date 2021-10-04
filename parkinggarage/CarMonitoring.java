public class CarMonitoring
{

    /*
     Make a class that has a method that keeps track of many cars there are total.
     Make another method that selects a floor and changes how many there are total.
     */

    public class floor
    {
        int NumSpots;
        int NumCars;
        int NumEmpty;

        public floor(int NumCars, int NumSpots)
        {
            this.NumSpots = NumSpots;
            this.NumCars = NumCars;
            this.NumEmpty = NumSpots-NumCars;
        }

        private void AddCar()
        {
            if(NumCars+1 <= NumSpots)
            {
                this.NumCars += 1;
                this.NumEmpty -= 1;
            }
        }

        private void RemoveCar()
        {
            if(NumCars-1 >= 0)
            {
                this.NumCars -= 1;
                this.NumEmpty += 1;
            }

        }

        private void ChangeSize(int NewSize)
        {
            if(NewSize - this.NumCars >= 0)
            {
                this.NumSpots = NewSize;
                this.NumEmpty = this.NumSpots - this.NumCars;
            }
        }
    }

    int NumFloors;
    floor[] Floors;

    public CarMonitoring(int NumFloors, int SpotsPerFloor) {
        this.NumFloors = NumFloors;
        Floors = new floor[NumFloors];
        for(int i = 0; i < NumFloors; i++)
        {
            Floors[i] = new floor(0, SpotsPerFloor);
        }
    }

    private void AddToFloor(int Level)
    {
        this.Floors[Level-1].AddCar();
    }

    private void RemoveFromFloor(int Level)
    {
        this.Floors[Level - 1].RemoveCar();
    }

    private void ChangeFloorSize(int Level, int NumSize)
    {
        this.Floors[Level-1].ChangeSize(NumSize);
    }

    private int NumFilled(int Level)
    {
        return this.Floors[Level-1].NumCars;
    }

    private int NumLeft(int Level)
    {
        return this.Floors[Level-1].NumEmpty;
    }

    public static void main(String[] args)
    {
        System.out.println("j");
        CarMonitoring Garage = new CarMonitoring(10, 50);
        for(int i = 0; i < 37; i++)
        {
            Garage.AddToFloor(7);
        }
        System.out.println(Garage.NumFilled(7));
        System.out.println(Garage.NumLeft(7));
        for(int j = 0; j < 14; j++)
        {
            Garage.RemoveFromFloor(7);
        }
        System.out.println(Garage.NumFilled(7));
        System.out.println(Garage.NumLeft(7));

        System.out.println("");
        Garage.ChangeFloorSize(4, 29);
        System.out.println(Garage.NumFilled(4));
        System.out.println(Garage.NumLeft(4));

        System.out.println("");
        Garage.ChangeFloorSize(7, 48);
        System.out.println(Garage.NumFilled(7));
        System.out.println(Garage.NumLeft(7));
    }
}
