# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
classDiagram
    class IEmployee {
        <<interface>>
        +getName() String
        +getId() String
        +getPayRate() double
        +getEmployeeType() String
        +getYTDEarnings() double
        +getYTDTaxesPaid() double
        +getPretaxDeductions() double
        +runPayroll (double hoursWored) IPayStub
        +toCSV() String
    }
    
    class IPayStub {
        <<interface>>
        +getPay() double
        +getTaxesPaid() double
        +toCSV() String
    }
 
    
    class ITimeCard {
        <<interface>>
        +getHoursWorked() double
        +getEmployeeID() String
    }
    
    
    class Builder {
        +static IEmployee buildEmployeeFromCSV(String csv)
        +static ITimeCard buildTimeCardFromCSV(String csv)
    }
    
    class EmployeeType {
        <<enumeration>>
        Hourly
        Salary
    }

    class HourlyEmployee {
        +calculatePay(List<ITimeCard> timeCards)
    }

    class SalaryEmployee {
        +calculatePay(List<ITimeCard> timeCards)
    }
    
    
    class FileUtil {
        +static String EMPLOYEE_HEADER 
        +static String PAY_STUB_HEADER
        +static List<String> readFileToList(String file)
        +static void writeFile(String outFile, List<String> lines)
        +static void writeFile(String outFile, List<String> lines, boolean backup)
    }
    
    class PayrollGenerator {
        +main(String[] args)
    }


    PayrollGenerator ..> Builder 
    PayrollGenerator ..> FileUtil 
    Builder ..> EmployeeType 
    Builder ..> HourlyEmployee 
    Builder ..> SalaryEmployee

   

    
    

```


## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. Test that the `TimeCard` class properly throws an `IllegalArgumentException` when negative hours are provided. 
4. Test that the `HourlyEmployee` class properly calculates gross pay in `calculateGrossPay()` -> The method should return 1000.0 for 40 hours worked and 1375.0 for 50 hours worked including overtime.
5. Test that the `SalaryEmployee` class properly calculates payroll with `runPayroll()`. -> the `runPayroll()` method should properly calculate the gross pay based on the employee's salary. 
6. Test that the `PayStub` class can correctly store and retrieve pay and taxes paid details. 



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.

```mermaid
classDiagram
    class IEmployee {
        <<interface>>
        +getName() String
        +getId() String
        +getPayRate() double
        +getEmployeeType() String
        +getYTDEarnings() double
        +getYTDTaxesPaid() double
        +getPretaxDeductions() double
        +runPayroll (double hoursWored) IPayStub
        +toCSV() String
    }
    
    class IPayStub {
        <<interface>>
        +getPay() double
        +getTaxesPaid() double
        +toCSV() String
    }
    
    class PayStub {
        -Pay double
        -TaxesPaid double
        +PayStub
        +getPay() double
        +getTaxesPaid() double
        +toCSV() String
    }
    
    class ITimeCard {
        <<interface>>
        +getHoursWorked() double
        +getEmployeeID() String
    }
    
    class TimeCard {
        -HoursWored double
        -EmployeeID String
        +TimeCard
        +getHoursWorked() double
        +getEmployeeID() String
    }
    
    class Builder {
        +static buildEmployeeFromCSV(csv: String) IEmployee
        +static buildTimeCardFromCSV(csv: String) ITimeCard
    }
    
    class EmployeeType {
        <<enumeration>>
        Hourly
        Salary
    }

    class HourlyEmployee {
        -static final OVERTIME_RATE: double = 1.5
        -static final REGULAR_HOURS: double = 40.0
        +HourlyEmployee
    }

    class SalaryEmployee {
        -static final PAYMENT_PERIOD: int = 24
        +SalaryEmployee
    }
    
    class Employee {
        <<abstract>>
        -name String
        -id String
        -payRate double
        -ytdEarnings BigDecimal
        -ytdTaxesPaid BigDecimal
        -pretaxDeductions double
        -type EmployeeType
        +getName() String
        +getID() String
        +getPayRate() double
        +getYTDEarnings() double
        +getYTDTaxesPaid() double
        +getPretaxDeductions() double
        +getEmployeeType() String
        +runPayroll(hoursWorked: double) IPayStub
        +toCSV() String
    }
    
    class FileUtil {
        +static String EMPLOYEE_HEADER 
        +static String PAY_STUB_HEADER
        +static List<String> readFileToList(String file)
        +static void writeFile(String outFile, List<String> lines)
        +static void writeFile(String outFile, List<String> lines, boolean backup)
    }
    
    class PayrollGenerator {
        +main(String[] args)
    }


    PayrollGenerator ..> Builder 
    PayrollGenerator ..> FileUtil 
    Builder ..> EmployeeType 
    Builder ..> HourlyEmployee 
    Builder ..> SalaryEmployee 
    Builder ..> TimeCard

    Employee ..|> IEmployee : implements
    HourlyEmployee --|> Employee : extends
    SalaryEmployee --|> Employee : extends

    TimeCard ..|> ITimeCard : implements
    PayStub ..|> IPayStub : implements

    PayStub --> IEmployee : references
    Employee --> ITimeCard 
    
    

```



## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

The new diagram shows much more detail about what each class can actually do. For example, the Employee class now clearly lists all its methods and data. The HourlyEmployee and SalaryEmployee classes got more specific too, we can now see overtime rate and payment period which I didn't include in my initial diagram. Another big change is how we handle money. The Employee class now uses BigDecimal for tracking earnings and taxes instead of regular doubles. I also cleaned up the design by being more explicit about how things connect. The new diagram shows clear relationships like how PayStub implements IPayStub, and how Employee connects to TimeCard.
The old design was like having a rough sketch, and it just showed basic connections between classes. The newer one is more detailed and practical. While making these changes, I learned that the reason why we switched to BigDecimal is because we want and need taxes to be more precise and so many other number cannot just use a regular calculator either. If I were doing this again, I would spend more time planning how everything should work together before starting coding. The most difficult and important thing is to figure out how to simplify things without losing important features. 
