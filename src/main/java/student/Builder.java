package student;

/** 
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {

    private Builder() {
    }

    /**
     * Builds an employee object from a CSV string.
     * <p>
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     *
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        if (csv == null || csv.isEmpty()) {
            throw new IllegalArgumentException("CSV cannot be null or empty");
        }

        String[] parts = csv.split(",");
        if (parts.length != 7) {
            return null;
        }

        try {
            String type = parts[0];
            String name = parts[1];
            String id = parts[2];
            double payRate = Double.parseDouble(parts[3]);
            double pretaxDeductions = Double.parseDouble(parts[4]);
            double ytdEarnings = Double.parseDouble(parts[5]);
            double ytdTaxesPaid = Double.parseDouble(parts[6]);

            if (type.equalsIgnoreCase("Hourly")) {
                return new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            } else if (type.equalsIgnoreCase("Salary")) {
                return new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            }
        } catch (Exception e) {
            return null;
        }
        return null;  //neither hourly nor salary
    }


    /**
     * Converts a TimeCard from a CSV String.
     *
     * @param csv csv string
     * @return a TimeCard object
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        if (csv == null || csv.isEmpty()) {
            throw new IllegalArgumentException("CSV cannot be null or empty");
        }

        String[] parts = csv.split(",");
        if (parts.length != 2) {
            return null;   //CSV format is incorrect
        }

        try{
            String employeeId = parts[0];
            double hours = Double.parseDouble(parts[1]);
            return new TimeCard(hours, employeeId);

        } catch (Exception e) {
            return null;
        }
    }
}


